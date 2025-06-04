package dev.dornol.ticket.manager.application

import dev.dornol.ticket.common.search.PageResult
import dev.dornol.ticket.manager.application.port.`in`.SearchManagersCommand
import dev.dornol.ticket.manager.application.port.`in`.SearchManagersUseCase
import dev.dornol.ticket.manager.application.port.out.ManagerListDto
import dev.dornol.ticket.manager.application.port.out.SearchManagersCriteria
import dev.dornol.ticket.manager.application.port.out.SearchManagersPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
open class ManagerSearchService(
    private val searchManagersPort: SearchManagersPort,
) : SearchManagersUseCase {

    @Transactional
    override fun searchManagers(command: SearchManagersCommand): PageResult<ManagerListDto> {
        return searchManagersPort.searchManagers(
            SearchManagersCriteria(
                searchKeys = command.searchKeys,
                searchText = command.searchText,
                approved = command.approved,
                managerRole = command.managerRole,
                pageQuery = command.pageQuery
            )
        )
    }

}