package dev.dornol.ticket.admin.api.app.repository.seat

import com.querydsl.jpa.impl.JPAQueryFactory
import dev.dornol.ticket.admin.api.app.dto.seat.QSeatDto
import dev.dornol.ticket.admin.api.app.dto.seat.QSeatGroupDto
import dev.dornol.ticket.admin.api.app.dto.seat.SeatDto
import dev.dornol.ticket.admin.api.app.dto.seat.SeatGroupDto
import dev.dornol.ticket.domain.entity.seat.QSeat.seat
import dev.dornol.ticket.domain.entity.seat.QSeatGroup.seatGroup
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
class SeatGroupRepositoryImpl(
    private val query: JPAQueryFactory
) : SeatGroupQueryRepository {

    @Transactional(readOnly = true)
    override fun getSeatGroupsAndSeats(siteId: Long): Pair<List<SeatGroupDto>, List<SeatDto>> {
        val seatGroups = query
            .select(QSeatGroupDto(
                seatGroup.id,
                seatGroup.name,
                seatGroup.color,
                seatGroup.displayOrder
            ))
            .from(seatGroup)
            .where(seatGroup.site.id.eq(siteId), seatGroup.deleted.isFalse)
            .orderBy(seatGroup.displayOrder.asc())
            .fetch()

        val seatGroupIds = seatGroups.map { it.id.toLong() }.toList()

        val seats = query
            .select(QSeatDto(
                seat.id,
                seat.name,
                seat.offset,
                seat.displayOrder,
                seat.group.id
            ))
            .from(seat)
            .where(seat.group.id.`in`(seatGroupIds), seat.deleted.isFalse)
            .orderBy(seat.group.displayOrder.asc(), seat.displayOrder.asc())
            .fetch()

        return seatGroups to seats
    }

}