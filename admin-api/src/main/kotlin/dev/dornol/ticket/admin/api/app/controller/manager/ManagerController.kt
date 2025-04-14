package dev.dornol.ticket.admin.api.app.controller.manager

import dev.dornol.ticket.admin.api.app.constants.DEFAULT_SORT_ORDER
import dev.dornol.ticket.admin.api.app.dto.manager.request.ManagerSearchDto
import dev.dornol.ticket.admin.api.app.service.manager.ManagerService
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.web.bind.annotation.*

@RequestMapping("/managers")
@RestController
class ManagerController(
    private val managerService: ManagerService
) {

    @GetMapping
    fun getManagers(
        search: ManagerSearchDto,
        @PageableDefault(sort = [DEFAULT_SORT_ORDER]) page: Pageable
    ) = managerService.searchManagers(search, page)

    @PostMapping("/{id}/approve")
    fun approveManager(
        @PathVariable id: Long,
        @AuthenticationPrincipal jwt: Jwt
    ) {
        managerService.approveManager(id, jwt.subject.toLong())
    }

}