package dev.dornol.ticket.site.port.`in`.command

data class DuplicateSeatCommand(
    val seatId: Long,
    val siteId: Long,
    val seatGroupId: Long
)
