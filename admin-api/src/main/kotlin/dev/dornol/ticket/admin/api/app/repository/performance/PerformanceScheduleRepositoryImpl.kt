package dev.dornol.ticket.admin.api.app.repository.performance

import com.querydsl.core.types.dsl.StringPath
import com.querydsl.jpa.impl.JPAQueryFactory
import dev.dornol.ticket.admin.api.app.dto.performance.request.PerformanceScheduleSearchDto
import dev.dornol.ticket.admin.api.app.dto.performance.request.PerformanceScheduleSearchField
import dev.dornol.ticket.admin.api.app.dto.performance.response.PerformanceScheduleListDto
import dev.dornol.ticket.admin.api.app.dto.performance.response.QPerformanceListDto
import dev.dornol.ticket.admin.api.app.dto.performance.response.QPerformanceScheduleListDto
import dev.dornol.ticket.admin.api.app.dto.site.response.QSiteListDto
import dev.dornol.ticket.admin.api.util.sort
import dev.dornol.ticket.admin.api.util.textSearch
import dev.dornol.ticket.admin.api.util.toOrderBy
import dev.dornol.ticket.domain.entity.performance.QPerformance.performance
import dev.dornol.ticket.domain.entity.performance.QPerformanceSchedule.performanceSchedule
import dev.dornol.ticket.domain.entity.site.QSite.site
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.support.PageableExecutionUtils
import org.springframework.stereotype.Repository

@Repository
class PerformanceScheduleRepositoryImpl(
    private val query: JPAQueryFactory
) : PerformanceScheduleQueryRepository {

    val mapper: Map<PerformanceScheduleSearchField, List<StringPath>> = mapOf(
        PerformanceScheduleSearchField.PERFORMANCE_NAME to listOf(performance.name),
        PerformanceScheduleSearchField.SITE_NAME to listOf(site.name),
        PerformanceScheduleSearchField.SITE_ADDRESS to listOf(site.address.mainAddress, site.address.detailAddress),
    )

    override fun search(
        companyId: Long,
        search: PerformanceScheduleSearchDto,
        pageable: Pageable
    ): Page<PerformanceScheduleListDto> {

        val condition = condition(companyId, search)

        val listQuery = query
            .select(
                QPerformanceScheduleListDto(
                    performanceSchedule.id,
                    QPerformanceListDto(
                        performance.id,
                        performance.name,
                        performance.type
                    ),
                    QSiteListDto(
                        site.id,
                        site.name,
                        site.address,
                        site.company.id
                    ),
                    performanceSchedule.performanceDate,
                    performanceSchedule.performanceTime,
                )
            )
            .from(performanceSchedule)
            .join(performanceSchedule.performance, performance).on(performance.deleted.isFalse)
            .join(performanceSchedule.site, site).on(site.deleted.isFalse)
            .where(*condition)
            .orderBy(*sort(pageable.sort))
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())

        val countQuery = query
            .select(performanceSchedule.count())
            .from(performanceSchedule)
            .join(performanceSchedule.performance, performance).on(performance.deleted.isFalse)
            .join(performanceSchedule.site, site).on(site.deleted.isFalse)
            .where(*condition)

        return PageableExecutionUtils.getPage(listQuery.fetch(), pageable) { countQuery.fetchOne() ?: 0L }
    }

    private fun condition(companyId: Long, search: PerformanceScheduleSearchDto) = arrayOf(
        performance.deleted.isFalse,
        performance.company.id.eq(companyId),
        search.performanceDateStart?.let { performanceSchedule.performanceDate.between(search.performanceDateStart, search.performanceDateEnd) },
        search.performanceType?.let { performance.type.eq(search.performanceType) },
        search.textSearch(mapper),
    )

    private fun sort(sort: Sort) = sort.toOrderBy {
        when (it.property) {
            "performance.name" -> performanceSchedule.performance.name.sort(it)
            "performanceDate" -> performanceSchedule.performanceDate.sort(it)
            else -> null
        }
    }

}