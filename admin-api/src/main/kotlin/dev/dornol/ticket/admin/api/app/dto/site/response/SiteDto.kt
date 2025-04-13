package dev.dornol.ticket.admin.api.app.dto.site.response

data class SiteDto(
    val id: String,
    val name: String,
    val address: AddressDto,
    val seatingMapLocation: String
)
