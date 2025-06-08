package dev.dornol.ticket.site.port.`in`

import dev.dornol.ticket.common.search.PageResult
import dev.dornol.ticket.site.port.`in`.dto.SiteListDto

interface SearchSitesUseCase {

    fun searchSites(command: SearchSitesCommand): PageResult<SiteListDto>

}