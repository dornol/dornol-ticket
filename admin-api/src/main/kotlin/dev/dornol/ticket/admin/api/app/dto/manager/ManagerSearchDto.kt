package dev.dornol.ticket.admin.api.app.dto.manager

import dev.dornol.ticket.domain.entity.manager.ManagerRole

data class ManagerSearchDto(
    val searchType: ManagerSearchType? = null,
    val searchText: String = "",
    val approved: Boolean? = null,
    val managerRole: ManagerRole? = null
)