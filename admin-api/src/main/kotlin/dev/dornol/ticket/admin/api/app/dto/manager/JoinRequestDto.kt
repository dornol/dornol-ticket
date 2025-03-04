package dev.dornol.ticket.admin.api.app.dto.manager

import dev.dornol.ticket.domain.constraint.RegExp
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern

data class JoinRequestDto(
    @field:NotBlank
    @field:Pattern(regexp = RegExp.USERNAME_REGEXP)
    val username: String,

    @field:NotBlank
    @field:Pattern(regexp = RegExp.PASSWORD_REGEXP)
    val password: String,

    @field:NotBlank
    @field:Email
    val email: String,

    @field:NotBlank
    @field:Pattern(regexp =  RegExp.PHONE_NUMBER_REGEXP)
    val phone: String,

    @field:NotBlank
    @field:Pattern(regexp =  RegExp.MANAGER_NAME_REGEXP)
    val name: String
)