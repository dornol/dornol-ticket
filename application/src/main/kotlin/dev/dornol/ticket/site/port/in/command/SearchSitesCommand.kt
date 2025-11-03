package dev.dornol.ticket.site.port.`in`.command

import dev.dornol.ticket.common.search.PageQuery
import dev.dornol.ticket.common.search.SearchBaseCommand
import dev.dornol.ticket.site.port.`in`.SiteSearchField

class SearchSitesCommand(
    override val searchKeys: Set<SiteSearchField>,
    override val searchText: String,
    override val pageQuery: PageQuery,
) : SearchBaseCommand<SiteSearchField>