package dev.dornol.ticket.site.adapter.`in`.web.dto

import dev.dornol.ticket.domain.constant.COLOR_REGEXP_PATTERN
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

data class SeatGroupAddRequestDto(

    @field:NotBlank
    @field:Size(max = 30)
    val name: String,

    @field:NotBlank
    @field:Pattern(regexp = COLOR_REGEXP_PATTERN)
    val color: String,

)
