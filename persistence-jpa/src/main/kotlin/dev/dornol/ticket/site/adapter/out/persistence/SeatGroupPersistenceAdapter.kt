package dev.dornol.ticket.site.adapter.out.persistence

import dev.dornol.ticket.site.adapter.out.persistence.mapper.toDto
import dev.dornol.ticket.site.adapter.out.persistence.query.SeatGroupQueryDslSupport
import dev.dornol.ticket.site.port.`in`.dto.SeatGroupDto
import dev.dornol.ticket.site.port.out.FindSeatGroupsPort
import org.springframework.stereotype.Repository

@Repository
class SeatGroupPersistenceAdapter(
    private val seatGroupEntityRepository: SeatGroupEntityRepository,
    private val seatGroupQueryDslSupport: SeatGroupQueryDslSupport
) : FindSeatGroupsPort {

    override fun findSeatGroups(siteId: Long): List<SeatGroupDto> {
        val (seatGroups, seats) = seatGroupQueryDslSupport.getSeatGroupsAndSeats(siteId)

        val seatGroupDtos = seatGroups.map { it.toDto() }

        val groupBy = seatGroupDtos.groupBy({ it.id.get() }, { it.seats })

        seats.forEach {
            groupBy[it.groupId]?.get(0)?.add(it.toDto())
        }

        return seatGroupDtos
    }

}