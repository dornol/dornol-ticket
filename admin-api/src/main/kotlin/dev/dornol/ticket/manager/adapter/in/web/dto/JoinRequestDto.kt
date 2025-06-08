package dev.dornol.ticket.manager.adapter.`in`.web.dto

import dev.dornol.ticket.domain.constant.MANAGER_NAME_PATTERN
import dev.dornol.ticket.domain.constant.PHONE_NUMBER_PATTERN
import dev.dornol.ticket.domain.constant.USERNAME_PATTERN
import jakarta.validation.Valid
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

data class JoinRequestDto(
    @field:NotBlank
    @field:Pattern(regexp = USERNAME_PATTERN)
    val username: String,

    @field:NotBlank
    @field:Size(min = 4, max = 30)
    val password: String,

    @field:NotBlank
    @field:Email
    val email: String,

    @field:NotBlank
    @field:Pattern(regexp = PHONE_NUMBER_PATTERN)
    val phone: String,

    @field:NotBlank
    @field:Pattern(regexp = MANAGER_NAME_PATTERN)
    val name: String,

    @field:Valid
    val company: CompanyRequestDto
)