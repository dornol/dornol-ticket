package dev.dornol.ticket.admin.api.app.dto.common.request

open class DefaultSearchDto<T : Enum<T>>(
    val searchFields: Set<T>,
    val searchText: String = ""
)