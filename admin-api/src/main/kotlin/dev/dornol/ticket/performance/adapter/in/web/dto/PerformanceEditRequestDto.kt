package dev.dornol.ticket.performance.adapter.`in`.web.dto

import dev.dornol.ticket.performance.domain.PerformanceType
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class PerformanceEditRequestDto(
    @NotBlank
    @field:Size(min = 3, max = 255)
    val name: String,
    @field:NotNull
    val type: PerformanceType
)
