package dev.dornol.ticket.admin.api.app.dto.manager.request

import dev.dornol.ticket.domain.entity.manager.ManagerRole

data class ManagerSearchDto(
    val searchType: ManagerSearchType = ManagerSearchType.ALL,
    val searchText: String = "",
    val approved: Boolean? = null,
    val managerRole: ManagerRole? = null
)