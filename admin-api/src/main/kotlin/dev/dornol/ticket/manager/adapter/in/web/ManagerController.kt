package dev.dornol.ticket.manager.adapter.`in`.web

import dev.dornol.ticket.admin.api.app.constants.DEFAULT_SORT_ORDER
import dev.dornol.ticket.manager.adapter.`in`.web.dto.ManagerSearchDto
import dev.dornol.ticket.manager.adapter.`in`.web.mapper.toSearchManagersCommand
import dev.dornol.ticket.manager.application.port.`in`.ApproveManagerUseCase
import dev.dornol.ticket.manager.application.port.`in`.SearchManagersUseCase
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.web.bind.annotation.*

@RequestMapping("/managers")
@RestController
class ManagerController(
    private val searchManagersUseCase: SearchManagersUseCase,
    private val approveManagerUseCase: ApproveManagerUseCase,
) {

    @GetMapping
    fun getManagers(
        search: ManagerSearchDto,
        @PageableDefault(sort = [DEFAULT_SORT_ORDER]) page: Pageable
    ) = searchManagersUseCase.searchManagers(search.toSearchManagersCommand(page))

    @PostMapping("/{id}/approve")
    fun approveManager(
        @PathVariable id: Long,
    ) = approveManagerUseCase.approveManager(id)

}