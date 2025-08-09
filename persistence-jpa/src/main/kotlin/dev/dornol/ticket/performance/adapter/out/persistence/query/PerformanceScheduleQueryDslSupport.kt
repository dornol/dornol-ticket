package dev.dornol.ticket.performance.adapter.out.persistence.query

import com.querydsl.jpa.impl.JPAQueryFactory
import dev.dornol.ticket.performance.adapter.out.jpa.QPerformanceEntity.performanceEntity
import dev.dornol.ticket.performance.adapter.out.jpa.QPerformanceScheduleEntity.performanceScheduleEntity
import dev.dornol.ticket.performance.adapter.out.persistence.PerformanceScheduleListProjection
import dev.dornol.ticket.performance.port.out.SearchPerformanceScheduleCriteria
import dev.dornol.ticket.site.adapter.out.jpa.QSiteEntity.siteEntity
import org.springframework.data.domain.Page
import org.springframework.data.support.PageableExecutionUtils
import org.springframework.stereotype.Component

@Component
class PerformanceScheduleQueryDslSupport(
    private val query: JPAQueryFactory
) {

    fun search(criteria: SearchPerformanceScheduleCriteria): Page<PerformanceScheduleListProjection> {
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
}