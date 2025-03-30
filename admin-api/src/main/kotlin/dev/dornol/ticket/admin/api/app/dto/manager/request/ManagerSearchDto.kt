package dev.dornol.ticket.admin.api.app.dto.manager.request

import dev.dornol.ticket.admin.api.app.dto.common.request.DefaultSearchDto
import dev.dornol.ticket.domain.entity.manager.ManagerRole

class ManagerSearchDto(
    searchFields: Set<ManagerSearchField>,
    searchText: String = "",
    val approved: Boolean? = null,
    val managerRole: ManagerRole? = null
) : DefaultSearchDto<ManagerSearchField>(searchFields, searchText)