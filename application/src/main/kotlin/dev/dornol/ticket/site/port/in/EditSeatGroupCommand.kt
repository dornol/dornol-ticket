package dev.dornol.ticket.site.port.`in`

data class EditSeatGroupCommand(
    val siteId: Long,
    val name: String,
    val color: String,
    val displayOrder: Long,
)
