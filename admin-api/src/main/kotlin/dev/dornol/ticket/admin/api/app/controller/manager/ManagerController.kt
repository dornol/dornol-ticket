package dev.dornol.ticket.admin.api.app.controller.manager

import dev.dornol.ticket.admin.api.app.dto.manager.ManagerSearchDto
import dev.dornol.ticket.admin.api.app.service.manager.ManagerService
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.data.domain.Pageable
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.web.bind.annotation.*

private val log = KotlinLogging.logger {}

@RequestMapping("/managers")
@RestController
class ManagerController(
    private val managerService: ManagerService
) {

    @GetMapping
    fun getManagers(search: ManagerSearchDto, page: Pageable) = managerService.searchManagers(search, page).also {
        log.debug { "page: $page" }
    }

    @PostMapping("/{id}/approve")
    fun approveManager(
        @PathVariable id: Long,
        @AuthenticationPrincipal jwt: Jwt
    ) {
        managerService.approveManager(id, jwt.subject.toLong())
    }

}