package dev.dornol.ticket.admin.api.app.dto.site.response

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer

data class SiteDto(
    @JsonSerialize(using = ToStringSerializer::class)
    val id: Long,
    val name: String,
    val address: AddressDto,
    val seatingMapLocation: String
)
