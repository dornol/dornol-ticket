package dev.dornol.ticket.performance.adapter.out.persistence.query

import com.querydsl.jpa.impl.JPAQueryFactory
import dev.dornol.ticket.common.toPageable
import dev.dornol.ticket.performance.adapter.out.jpa.QPerformanceEntity.performanceEntity as performance
import dev.dornol.ticket.performance.adapter.out.persistence.PerformanceListProjection
import dev.dornol.ticket.performance.adapter.out.persistence.QPerformanceListProjection
import dev.dornol.ticket.performance.port.out.SearchPerformancesCriteria
import org.springframework.data.domain.Page
import org.springframework.data.support.PageableExecutionUtils
import org.springframework.stereotype.Component

@Component
class PerformanceQueryDslSupport(
    private val query: JPAQueryFactory
) {

    fun search(criteria: SearchPerformancesCriteria): Page<PerformanceListProjection> {
        val pageable = criteria.pageQuery.toPageable()
        val condition = PerformancePredicates.build(criteria)

        val listQuery = query
            .select(QPerformanceListProjection(
                performance.id,
                performance.name,
                performance.type
            ))
            .from(performance)
            .where(*condition)
            .orderBy(*PerformancePredicates.sort(pageable.sort), performance.id.desc())
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())

        val countQuery = query
            .select(performance.count())
            .from(performance)
            .where(*condition)

        return PageableExecutionUtils.getPage(listQuery.fetch(), pageable) { countQuery.fetchOne() ?: 0 }

    }

}