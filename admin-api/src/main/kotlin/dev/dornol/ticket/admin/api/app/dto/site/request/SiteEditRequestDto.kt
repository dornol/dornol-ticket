package dev.dornol.ticket.admin.api.app.dto.site.request

import dev.dornol.ticket.admin.api.app.dto.site.response.AddressDto
import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class SiteEditRequestDto(
    @field:NotBlank
    @field:Size(max = 50)
    val name: String,
    @field:Valid
    @field:NotNull
    val address: AddressDto,
    val seatingMapFileId: Long?
)
