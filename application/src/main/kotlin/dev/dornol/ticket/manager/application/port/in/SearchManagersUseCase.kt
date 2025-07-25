package dev.dornol.ticket.manager.application.port.`in`

import dev.dornol.ticket.common.search.PageResult
import dev.dornol.ticket.manager.application.port.`in`.dto.ManagerListDto

interface SearchManagersUseCase {

    fun searchManagers(command: SearchManagersCommand): PageResult<ManagerListDto>

}