package dev.dornol.ticket.site.port.`in`.command

data class EditSeatCommand(
    val newSeatGroupId: Long,
    val name: String,
)
