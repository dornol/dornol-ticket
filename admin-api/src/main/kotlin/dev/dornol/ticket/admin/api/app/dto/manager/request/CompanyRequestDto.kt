package dev.dornol.ticket.admin.api.app.dto.manager.request

import dev.dornol.ticket.domain.constant.BUSINESS_NAME_PATTERN
import dev.dornol.ticket.domain.constant.BUSINESS_NUMBER_PATTERN
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern

data class CompanyRequestDto(

    @field:NotBlank
    @field:Pattern(regexp = BUSINESS_NAME_PATTERN)
    val name: String,

    @field:NotBlank
    @field:Pattern(regexp = BUSINESS_NUMBER_PATTERN)
    val businessNumber: String,
)