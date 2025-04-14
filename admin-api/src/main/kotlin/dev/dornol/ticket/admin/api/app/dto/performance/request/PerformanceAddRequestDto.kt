package dev.dornol.ticket.admin.api.app.dto.performance.request

import dev.dornol.ticket.domain.entity.performance.PerformanceType
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class PerformanceAddRequestDto(
    @NotBlank
    @field:Size(min = 3, max = 255)
    val name: String,
    @field:NotNull
    val type: PerformanceType,
    @field:Min(0)
    val siteId: Long,
)
