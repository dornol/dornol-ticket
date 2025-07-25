package dev.dornol.ticket.manager.application.port.out

import dev.dornol.ticket.common.search.PageResult
import dev.dornol.ticket.manager.application.port.`in`.dto.ManagerListDto

interface SearchManagersPort {

    fun searchManagers(criteria: SearchManagersCriteria): PageResult<ManagerListDto>

}