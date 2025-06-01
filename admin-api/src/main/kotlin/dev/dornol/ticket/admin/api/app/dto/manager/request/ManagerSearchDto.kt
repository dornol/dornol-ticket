package dev.dornol.ticket.admin.api.app.dto.manager.request

import dev.dornol.ticket.admin.api.app.dto.common.request.DefaultSearchDto
import dev.dornol.ticket.manager.domain.ManagerRole

class ManagerSearchDto(
    searchFields: Set<ManagerSearchField> = hashSetOf(),
    searchText: String = "",
    val approved: Boolean? = null,
    val managerRole: ManagerRole? = null
) : DefaultSearchDto<ManagerSearchField>(searchFields, searchText)