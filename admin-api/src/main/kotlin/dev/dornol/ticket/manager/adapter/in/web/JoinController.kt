package dev.dornol.ticket.manager.adapter.`in`.web

import dev.dornol.ticket.admin.api.app.dto.manager.request.CheckUsernameDto
import dev.dornol.ticket.admin.api.app.dto.manager.request.JoinRequestDto
import dev.dornol.ticket.manager.adapter.`in`.web.dto.CheckUsernameResponseDto
import dev.dornol.ticket.manager.application.port.`in`.CheckUsernameExistsUseCase
import dev.dornol.ticket.manager.application.port.`in`.ManagerJoinCommand
import dev.dornol.ticket.manager.application.port.`in`.ManagerJoinUseCase
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
    private val checkUsernameExistsUseCase: CheckUsernameExistsUseCase,
    private val joinUseCase: ManagerJoinUseCase
) {

    @GetMapping("/join/check-username")
    fun checkUsername(@Validated request: CheckUsernameDto): ResponseEntity<CheckUsernameResponseDto> {
        val exists = checkUsernameExistsUseCase.exists(username = request.username)

        val cacheControl = if (exists) {
            CacheControl.maxAge(1, TimeUnit.HOURS)
        } else {
            CacheControl.noCache()
        }
        return ResponseEntity.ok().cacheControl(cacheControl).body(CheckUsernameResponseDto(!exists))
    }

    @PostMapping("/join")
    fun join(@RequestBody @Validated joinDto: JoinRequestDto) {
        joinUseCase.join(ManagerJoinCommand(
            username = joinDto.username,
            password = joinDto.password,
            email = joinDto.email,
            phone = joinDto.phone,
            name = joinDto.name,
            companyName = joinDto.company.name,
            businessNumber = joinDto.company.businessNumber,
        ))
    }

}