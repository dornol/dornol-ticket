package dev.dornol.ticket.admin.api.app.dto.site.request

import dev.dornol.ticket.admin.api.app.dto.common.request.DefaultSearchDto

class SiteSearchDto(
    searchFields: Set<SiteSearchField> = setOf(),
    searchText: String = ""
) : DefaultSearchDto<SiteSearchField>(searchFields, searchText)