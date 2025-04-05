package dev.dornol.ticket.admin.api.app.dto.common

import dev.dornol.ticket.admin.api.app.dto.common.request.DefaultSearchDto

data class SearchContext<T : DefaultSearchDto<*>>(
    val userId: Long? = null,
    val companyId: Long? = null,
    val search: T
)