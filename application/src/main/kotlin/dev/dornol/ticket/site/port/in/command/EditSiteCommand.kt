package dev.dornol.ticket.site.port.`in`.command

import dev.dornol.ticket.site.port.`in`.dto.AddressDto
import java.util.*

data class EditSiteCommand(
    val name: String,
    val address: AddressDto,
    val seatingMapFileUuid: UUID?
)
