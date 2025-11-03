package dev.dornol.ticket.site.adapter.out.persistence.query

import com.querydsl.jpa.impl.JPAQueryFactory
import dev.dornol.ticket.site.adapter.out.persistence.QSeatGroupProjection
import dev.dornol.ticket.site.adapter.out.persistence.QSeatProjection
import dev.dornol.ticket.site.adapter.out.persistence.SeatGroupProjection
import dev.dornol.ticket.site.adapter.out.persistence.SeatProjection
import org.springframework.stereotype.Component
import dev.dornol.ticket.site.adapter.out.jpa.QSeatEntity.seatEntity as seat
import dev.dornol.ticket.site.adapter.out.jpa.QSeatGroupEntity.seatGroupEntity as seatGroup

@Component
class SeatGroupQueryDslSupport(
    private val query: JPAQueryFactory
) {

    fun getSeatGroupsAndSeats(siteId: Long): Pair<List<SeatGroupProjection>, List<SeatProjection>> {

        val seatGroups = query
            .select(QSeatGroupProjection(
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
            .select(QSeatProjection(
                seat.id,
                seat.name,
                seat.offset,
                seat.displayOrder,
                seat.seatGroup.id
            ))
            .from(seat)
            .where(seat.seatGroup.id.`in`(seatGroupIds), seat.deleted.isFalse)
            .orderBy(seat.seatGroup.displayOrder.asc(), seat.displayOrder.asc())
            .fetch()

        return seatGroups to seats
    }
}