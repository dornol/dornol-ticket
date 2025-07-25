package dev.dornol.ticket.manager.adapter.`in`.web

import dev.dornol.ticket.admin.api.app.dto.user.UserDto
import dev.dornol.ticket.admin.api.app.dto.user.UserWithAuthDto
import dev.dornol.ticket.manager.application.port.`in`.FindManagerUseCase
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/user")
@RestController
class AuthenticatedUserController(
    private val findManagerUseCase: FindManagerUseCase
) {

    @GetMapping("/me")
    fun me(authentication: Authentication): UserWithAuthDto {
        val managerDto = findManagerUseCase.findManagerById(authentication.name.toLong())

        val user = UserDto(
            userId = managerDto.id,
            name = managerDto.name,
            email = managerDto.email,
            username = managerDto.username,
            phone = managerDto.phone,
        )

        return UserWithAuthDto(
            user = user,
            authorities = authentication.authorities.map { it.authority }
        )
    }
}