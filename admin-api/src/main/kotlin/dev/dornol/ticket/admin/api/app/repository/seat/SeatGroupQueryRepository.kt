package dev.dornol.ticket.admin.api.app.repository.seat

import dev.dornol.ticket.admin.api.app.dto.seat.SeatDto
import dev.dornol.ticket.admin.api.app.dto.seat.SeatGroupDto

interface SeatGroupQueryRepository {

    fun getSeatGroupsAndSeats(siteId: Long): Pair<List<SeatGroupDto>, List<SeatDto>>

}