package dev.dornol.ticket.site.port.out

import dev.dornol.ticket.site.port.`in`.dto.SeatGroupDto

interface FindSeatGroupsPort {

    fun findSeatGroups(siteId: Long): List<SeatGroupDto>

}