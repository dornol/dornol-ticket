package dev.dornol.ticket.domain.entity.reservation

import dev.dornol.ticket.domain.entity.BaseEntity
import dev.dornol.ticket.domain.entity.common.Money
import dev.dornol.ticket.domain.entity.performance.PerformanceScheduleSeatGroup
import dev.dornol.ticket.domain.entity.seat.Seat
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

private const val TABLE_NAME = "reservation"

private const val SCHEDULE_SEAT_GROUP_ID = "schedule_seat_group_id"
private const val SEAT_ID = "seat_id"

@Table(name = TABLE_NAME)
@Entity
class Reservation(
    scheduleSeatGroup: PerformanceScheduleSeatGroup,
    seat: Seat,
    price: Money,
    state: ReservationState = ReservationState.PENDING_PAYMENT,
) : BaseEntity() {

    var price: Money = price

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    var state: ReservationState = state

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    var cancelReason: CancelReason? = null

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = SCHEDULE_SEAT_GROUP_ID, nullable = false, updatable = false)
    val scheduleSeatGroup: PerformanceScheduleSeatGroup = scheduleSeatGroup

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = SEAT_ID, nullable = false, updatable = false)
    val seat: Seat = seat

}