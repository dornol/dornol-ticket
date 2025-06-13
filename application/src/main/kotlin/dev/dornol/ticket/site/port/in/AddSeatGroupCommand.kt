package dev.dornol.ticket.site.port.`in`

data class AddSeatGroupCommand(
    val siteId: Long,
    val name: String,
    val color: String,
)
