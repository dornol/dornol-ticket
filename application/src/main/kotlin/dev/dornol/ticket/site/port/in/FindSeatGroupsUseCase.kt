package dev.dornol.ticket.site.port.`in`

import dev.dornol.ticket.site.port.`in`.dto.SeatGroupDto

interface FindSeatGroupsUseCase {

    fun findSeatGroups(siteId: Long): List<SeatGroupDto>

}