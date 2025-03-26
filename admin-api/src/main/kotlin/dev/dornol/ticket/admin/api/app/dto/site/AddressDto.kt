package dev.dornol.ticket.admin.api.app.dto.site

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class AddressDto(
    @field:Size(max = 5)
    val zipCode: String?,
    @field:NotBlank
    @field:Size(max = 255)
    val mainAddress: String,
    @field:NotBlank
    @field:Size(max = 255)
    val detailAddress: String,
)