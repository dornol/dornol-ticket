package dev.dornol.ticket.admin.api.app.repository.performance

import com.querydsl.core.types.dsl.StringPath
import com.querydsl.jpa.impl.JPAQueryFactory
import dev.dornol.ticket.admin.api.app.dto.performance.request.PerformanceSearchDto
import dev.dornol.ticket.admin.api.app.dto.performance.request.PerformanceSearchField
import dev.dornol.ticket.admin.api.app.dto.performance.response.PerformanceListDto
import dev.dornol.ticket.admin.api.app.dto.performance.response.QPerformanceListDto
import dev.dornol.ticket.admin.api.util.sort
import dev.dornol.ticket.admin.api.util.textSearch
import dev.dornol.ticket.admin.api.util.toOrderBy
import dev.dornol.ticket.domain.entity.performance.QPerformanceEntity.performanceEntity as performance
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.support.PageableExecutionUtils
import org.springframework.stereotype.Repository

@Repository
class PerformanceRepositoryImpl(
    private val query: JPAQueryFactory
) : PerformanceQueryRepository {

    val mapper: Map<PerformanceSearchField, List<StringPath>> = mapOf(
        PerformanceSearchField.NAME to listOf(performance.name)
    )

    override fun search(search: PerformanceSearchDto, pageable: Pageable): Page<PerformanceListDto> {
        val condition = performanceListCondition(search)

        val listQuery = query
            .select(QPerformanceListDto(
                performance.id,
                performance.name,
                performance.type
            ))
            .from(performance)
            .where(*condition)
            .orderBy(*sort(pageable.sort), performance.id.desc())
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())

        val countQuery = query
            .select(performance.count())
            .from(performance)
            .where(*condition)

        return PageableExecutionUtils.getPage(listQuery.fetch(), pageable) { countQuery.fetchOne() ?: 0 }
    }

    private fun performanceListCondition(search: PerformanceSearchDto) = arrayOf(
        performance.deleted.isFalse,
        search.textSearch(mapper),
    )

    private fun sort(sort: Sort) = sort.toOrderBy {
        when (it.property) {
            "name" -> performance.name.sort(it)
            "type" -> performance.type.sort(it)
            else -> null
        }
    }
}