package dev.dornol.ticket.site.adapter.out.persistence.query

import com.querydsl.jpa.impl.JPAQueryFactory
import dev.dornol.ticket.common.toPageable
import dev.dornol.ticket.site.adapter.out.persistence.QSiteListProjection
import dev.dornol.ticket.site.adapter.out.persistence.SiteListProjection
import dev.dornol.ticket.site.port.out.SearchSitesCriteria
import org.springframework.data.domain.Page
import org.springframework.data.support.PageableExecutionUtils
import org.springframework.stereotype.Component
import dev.dornol.ticket.site.adapter.out.jpa.QSiteEntity.siteEntity as site

@Component
class SiteQueryDslSupport(
    private val query: JPAQueryFactory
) {

    fun search(criteria: SearchSitesCriteria): Page<SiteListProjection> {
        val pageable = criteria.pageQuery.toPageable()
        val condition = SitePredicates.build(criteria)

        val list = query
            .select(QSiteListProjection(
                site.id,
                site.name,
                site.address,
                site.company.id
            ))
            .from(site)
            .join(site.company)
            .where(*condition)
            .orderBy(*SitePredicates.sort(pageable.sort), site.id.desc())
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())
            .fetch()

        val countQuery = query
            .select(site.count())
            .from(site)
            .join(site.company)
            .where(*condition)

        return PageableExecutionUtils.getPage(list, pageable) { countQuery.fetchOne() ?: 0}
    }

}