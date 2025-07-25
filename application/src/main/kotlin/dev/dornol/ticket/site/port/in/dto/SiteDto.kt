package dev.dornol.ticket.site.port.`in`.dto

import java.util.UUID

data class SiteDto(
    val id: Long,
    val name: String,
    val address: AddressDto,
    val seatingMapFileUuid: UUID
)
