package dev.dornol.ticket.site.port.out

import dev.dornol.ticket.common.search.PageQuery
import dev.dornol.ticket.common.search.SearchBaseCriteria
import dev.dornol.ticket.site.port.`in`.SiteSearchField

class SearchSitesCriteria(
    override val searchKeys: Set<SiteSearchField>,
    override val searchText: String,
    override val pageQuery: PageQuery,
    val companyId: Long
) : SearchBaseCriteria<SiteSearchField> {
}