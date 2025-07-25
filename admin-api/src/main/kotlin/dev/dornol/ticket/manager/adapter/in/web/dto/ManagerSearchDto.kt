package dev.dornol.ticket.manager.adapter.`in`.web.dto

import dev.dornol.ticket.manager.domain.ManagerRole

class ManagerSearchDto(
    val searchFields: Set<String> = hashSetOf(),
    val searchText: String = "",
    val approved: Boolean? = null,
    val managerRole: ManagerRole? = null
)