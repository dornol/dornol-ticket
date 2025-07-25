package dev.dornol.ticket.site.port.`in`.command

data class AddSeatCommand(
    val siteId: Long,
    val seatGroupId: Long,
    val x: Double,
    val y: Double,
)
