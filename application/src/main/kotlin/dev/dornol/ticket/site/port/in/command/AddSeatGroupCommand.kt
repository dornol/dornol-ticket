package dev.dornol.ticket.site.port.`in`.command

data class AddSeatGroupCommand(
    val siteId: Long,
    val name: String,
    val color: String,
)
