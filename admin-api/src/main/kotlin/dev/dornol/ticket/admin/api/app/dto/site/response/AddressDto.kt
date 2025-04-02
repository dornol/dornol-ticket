package dev.dornol.ticket.admin.api.app.dto.site.response

import dev.dornol.ticket.domain.entity.site.address.Address
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class AddressDto(
    @field:Size(max = 5)
    @field:NotBlank
    val zipCode: String?,
    @field:NotBlank
    @field:Size(max = 255)
    val mainAddress: String,
    @field:NotBlank
    @field:Size(max = 255)
    val detailAddress: String,
) {
    fun toEntity() = Address(zipCode, mainAddress, detailAddress)
}