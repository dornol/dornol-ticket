package dev.dornol.ticket.admin.api.app.dto.site

import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class SiteAddRequestDto(
    @field:NotBlank
    val name: String,
    val test: String,
    @field:Valid
    @field:NotNull
    val address: AddressDto,
)
