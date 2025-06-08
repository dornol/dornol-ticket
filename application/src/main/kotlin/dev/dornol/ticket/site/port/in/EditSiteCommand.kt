package dev.dornol.ticket.site.port.`in`

import dev.dornol.ticket.site.port.`in`.dto.AddressDto
import java.util.*

data class EditSiteCommand(
    val name: String,
    val address: AddressDto,
    val seatingMapFileUuid: UUID?
)
