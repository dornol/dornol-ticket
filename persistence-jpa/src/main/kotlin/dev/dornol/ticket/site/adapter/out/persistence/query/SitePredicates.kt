package dev.dornol.ticket.site.adapter.out.persistence.query

import com.querydsl.core.types.dsl.StringPath
import dev.dornol.ticket.common.sort
import dev.dornol.ticket.common.textSearch
import dev.dornol.ticket.common.toOrderBy
import dev.dornol.ticket.site.port.`in`.SiteSearchField
import dev.dornol.ticket.site.port.`in`.SiteSearchField.*
import dev.dornol.ticket.site.port.out.SearchSitesCriteria
import org.springframework.data.domain.Sort
import dev.dornol.ticket.site.adapter.out.jpa.QSiteEntity.siteEntity as site

object SitePredicates {
    val mapper: Map<SiteSearchField, List<StringPath>> = mapOf(
        NAME to listOf(site.name),
        COMPANY_NAME to listOf(site.company.name),
        ADDRESS to listOf(site.address.mainAddress, site.address.detailAddress, site.address.zipCode),
    )

    fun build(criteria: SearchSitesCriteria) = arrayOf(
        site.deleted.isFalse,
        criteria.textSearch(mapper),
        site.company.id.eq(criteria.companyId)
    )

    fun sort(sort: Sort) = sort.toOrderBy {
        when (it.property) {
            "name" -> site.name.sort(it)
            "address" -> site.address.mainAddress.sort(it)
            "company_name" -> site.company.name.sort(it)
            else -> null
        }
    }


}