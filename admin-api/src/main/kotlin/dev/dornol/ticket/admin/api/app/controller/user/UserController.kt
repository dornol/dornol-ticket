package dev.dornol.ticket.admin.api.app.controller.user

import dev.dornol.ticket.admin.api.app.dto.user.UserWithAuthDto
import dev.dornol.ticket.admin.api.app.service.manager.ManagerService
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/user")
@RestController
class UserController(
    private val managerService: ManagerService
) {

    @GetMapping("/me")
    fun me(authentication: Authentication) = UserWithAuthDto(
        user = managerService.getUserDataById(authentication.name.toLong()),
        authorities = authentication.authorities.map { it.authority }
    )

}