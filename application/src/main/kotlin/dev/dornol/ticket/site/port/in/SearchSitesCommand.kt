package dev.dornol.ticket.site.port.`in`

import dev.dornol.ticket.common.search.PageQuery
import dev.dornol.ticket.common.search.SearchBaseCommand

class SearchSitesCommand(
    override val searchKeys: Set<SiteSearchField>,
    override val searchText: String,
    override val pageQuery: PageQuery,
) : SearchBaseCommand<SiteSearchField>