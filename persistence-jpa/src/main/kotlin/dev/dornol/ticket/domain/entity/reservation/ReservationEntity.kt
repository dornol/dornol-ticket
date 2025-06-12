package dev.dornol.ticket.domain.entity.reservation

import dev.dornol.ticket.common.domain.Money
import dev.dornol.ticket.domain.converter.MoneyConverter
import dev.dornol.ticket.domain.converter.enums.CancelReasonConverter
import dev.dornol.ticket.domain.converter.enums.ReservationStatusConverter
import dev.dornol.ticket.domain.entity.BaseEntity
import dev.dornol.ticket.performance.adapter.out.jpa.PerformanceScheduleSeatGroup
import dev.dornol.ticket.reservation.domain.CancelReason
import dev.dornol.ticket.reservation.domain.ReservationStatus
import dev.dornol.ticket.site.adapter.out.jpa.SeatEntity
import jakarta.persistence.*

@Table(name = "reservation")
@Entity
class ReservationEntity(
    id: Long,
    scheduleSeatGroup: PerformanceScheduleSeatGroup,
    seat: SeatEntity,
    price: Money,
    state: ReservationStatus = ReservationStatus.PENDING_PAYMENT,
) : BaseEntity(id) {

    @Convert(converter = MoneyConverter::class)
    @Column(nullable = false)
    var price: Money = price

    @Convert(converter = ReservationStatusConverter::class)
    @Column(nullable = false)
    var state: ReservationStatus = state

    @Convert(converter = CancelReasonConverter::class)
    @Column(nullable = false)
    var cancelReason: CancelReason? = null

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "schedule_seat_group_id", nullable = false, updatable = false)
    val scheduleSeatGroup: PerformanceScheduleSeatGroup = scheduleSeatGroup

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "seat_id", nullable = false, updatable = false)
    val seat: SeatEntity = seat

}