package dev.dornol.ticket.admin.api.app.controller.join

import dev.dornol.ticket.admin.api.app.dto.manager.CheckUsernameDto
import dev.dornol.ticket.admin.api.app.dto.manager.CheckUsernameResponseDto
import dev.dornol.ticket.admin.api.app.dto.manager.JoinRequestDto
import dev.dornol.ticket.admin.api.app.service.manager.ManagerService
import org.springframework.http.CacheControl
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.TimeUnit

@RestController
class JoinController(
    private val managerService: ManagerService
) {

    @GetMapping("/join/check-username")
    fun checkUsername(@Validated request: CheckUsernameDto): ResponseEntity<CheckUsernameResponseDto> {
        val exists = managerService.existsUsername(username = request.username)

        val cacheControl = if (exists) {
            CacheControl.maxAge(1, TimeUnit.HOURS)
        } else {
            CacheControl.noCache()
        }
        return ResponseEntity.ok().cacheControl(cacheControl).body(CheckUsernameResponseDto(!exists))
    }

    @PostMapping("/join")
    fun join(@RequestBody @Validated joinDto: JoinRequestDto) {
        managerService.join(joinDto)
    }

}