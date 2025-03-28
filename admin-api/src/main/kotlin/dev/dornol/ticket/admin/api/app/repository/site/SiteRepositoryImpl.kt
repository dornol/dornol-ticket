package dev.dornol.ticket.admin.api.app.repository.site

import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import dev.dornol.ticket.admin.api.app.dto.site.request.SiteSearchDto
import dev.dornol.ticket.admin.api.app.dto.site.request.SiteSearchType.*
import dev.dornol.ticket.admin.api.app.dto.site.response.QSiteListDto
import dev.dornol.ticket.admin.api.app.dto.site.response.SiteListDto
import dev.dornol.ticket.admin.api.util.sort
import dev.dornol.ticket.admin.api.util.toOrderBy
import dev.dornol.ticket.domain.entity.site.QSite.site
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.support.PageableExecutionUtils
import org.springframework.stereotype.Repository
import org.springframework.util.StringUtils

@Repository
class SiteRepositoryImpl(
    private val query: JPAQueryFactory
) : SiteQueryRepository {

    override fun search(search: SiteSearchDto, pageable: Pageable): Page<SiteListDto> {
        val condition = condition(search)

        val list = query
            .select(QSiteListDto(
                site.id.stringValue(),
                site.name,
                site.address,
                site.company.id.stringValue()
            ))
            .from(site)
            .join(site.company)
            .where(condition, site.deleted.isFalse)
            .orderBy(*sort(pageable.sort))
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())
            .fetch()

        val countQuery = query
            .select(site.count())
            .from(site)
            .join(site.company)
            .where(condition, site.deleted.isFalse)

        return PageableExecutionUtils.getPage(list, pageable) { countQuery.fetchOne() ?: 0}
    }

    private fun condition(search: SiteSearchDto): BooleanExpression? {
        return search.searchText.takeIf { StringUtils.hasText(it) }?.let {
            when (search.searchType) {
                NAME -> site.name.contains(search.searchText)
                COMPANY_NAME -> site.company.name.contains(search.searchText)
                ADDRESS -> site.address.mainAddress.contains(search.searchText)
                    .or(site.address.detailAddress.contains(search.searchText))
                    .or(site.address.zipCode.contains(search.searchText))

                else -> site.name.contains(search.searchText)
                    .or(site.address.mainAddress.contains(search.searchText))
                    .or(site.address.detailAddress.contains(search.searchText))
                    .or(site.address.zipCode.contains(search.searchText))
                    .or(site.company.name.contains(search.searchText))
            }
        }
    }

    private fun sort(sort: Sort) = sort.toOrderBy {
        when (it.property) {
            "name" -> site.name.sort(it)
            "address" -> site.address.mainAddress.sort(it)
            "company_name" -> site.company.name.sort(it)
            else -> site.id.sort(it)
        }
    }

}