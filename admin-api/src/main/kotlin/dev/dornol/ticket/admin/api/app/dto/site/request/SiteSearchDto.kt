package dev.dornol.ticket.admin.api.app.dto.site.request

import jakarta.validation.constraints.NotNull

data class SiteSearchDto(
    @field:NotNull
    val searchType: SiteSearchType = SiteSearchType.ALL,
    val searchText: String = ""
)