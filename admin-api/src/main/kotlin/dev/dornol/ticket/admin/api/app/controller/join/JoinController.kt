package dev.dornol.ticket.admin.api.app.controller.join

import dev.dornol.ticket.admin.api.app.dto.manager.CheckUsernameDto
import dev.dornol.ticket.admin.api.app.dto.manager.CheckUsernameResponseDto
import dev.dornol.ticket.admin.api.app.dto.manager.JoinRequestDto
import dev.dornol.ticket.admin.api.app.service.manager.ManagerService
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class JoinController(
    private val managerService: ManagerService
) {

    @PostMapping("/join/exists-username")
    fun existsUsername(@RequestBody @Validated request: CheckUsernameDto): CheckUsernameResponseDto {
        return CheckUsernameResponseDto(managerService.existsUsername(username = request.username))
    }

    @PostMapping("/join")
    fun join(@RequestBody @Validated joinDto: JoinRequestDto) {
        managerService.join(joinDto)
    }

}