package dev.dornol.ticket.site.port.out

import dev.dornol.ticket.common.search.PageResult
import dev.dornol.ticket.site.port.`in`.dto.SiteListDto

interface SearchSitesPort {

    fun searchSites(criteria: SearchSitesCriteria): PageResult<SiteListDto>

}