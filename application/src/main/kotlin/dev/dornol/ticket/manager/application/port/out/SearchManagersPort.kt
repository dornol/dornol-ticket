package dev.dornol.ticket.manager.application.port.out

import dev.dornol.ticket.common.search.PageResult

interface SearchManagersPort {

    fun searchManagers(criteria: SearchManagersCriteria): PageResult<ManagerListDto>

}