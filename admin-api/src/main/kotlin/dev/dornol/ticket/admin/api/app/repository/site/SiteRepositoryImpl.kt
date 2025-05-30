package dev.dornol.ticket.admin.api.app.repository.site

import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import dev.dornol.ticket.admin.api.app.dto.common.SearchContext
import dev.dornol.ticket.admin.api.app.dto.site.request.SiteSearchDto
import dev.dornol.ticket.admin.api.app.dto.site.request.SiteSearchField.*
import dev.dornol.ticket.admin.api.app.dto.site.response.QSiteListDto
import dev.dornol.ticket.admin.api.app.dto.site.response.SiteListDto
import dev.dornol.ticket.admin.api.util.sort
import dev.dornol.ticket.admin.api.util.textSearch
import dev.dornol.ticket.admin.api.util.toOrderBy
import dev.dornol.ticket.domain.entity.site.QSite.site
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.support.PageableExecutionUtils
import org.springframework.stereotype.Repository

@Repository
class SiteRepositoryImpl(
    private val query: JPAQueryFactory
) : SiteQueryRepository {

    private val mapper = mapOf(
        NAME to listOf(site.name),
        COMPANY_NAME to listOf(site.company.name),
        ADDRESS to listOf(site.address.mainAddress, site.address.detailAddress, site.address.zipCode),
    )

    override fun search(context: SearchContext<SiteSearchDto>, pageable: Pageable): Page<SiteListDto> {
        val condition = condition(context)

        val list = query
            .select(QSiteListDto(
                site.id,
                site.name,
                site.address,
                site.company.id
            ))
            .from(site)
            .join(site.company)
            .where(*condition)
            .orderBy(*sort(pageable.sort), site.id.desc())
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

    private fun condition(context: SearchContext<SiteSearchDto>): Array<BooleanExpression?> {
        return arrayOf(
            site.deleted.isFalse,
            context.search.textSearch(mapper),
            site.company.id.eq(context.companyId)
        )
    }

    private fun sort(sort: Sort) = sort.toOrderBy {
        when (it.property) {
            "name" -> site.name.sort(it)
            "address" -> site.address.mainAddress.sort(it)
            "company_name" -> site.company.name.sort(it)
            else -> null
        }
    }

}