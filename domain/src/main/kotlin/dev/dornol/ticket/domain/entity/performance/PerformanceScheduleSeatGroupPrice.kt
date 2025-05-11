package dev.dornol.ticket.domain.entity.performance

import dev.dornol.ticket.domain.entity.BaseEntity
import dev.dornol.ticket.domain.entity.common.Money
import dev.dornol.ticket.domain.entity.seat.SeatGroup
import jakarta.persistence.*

private const val TABLE_NAME = "performance_schedule_seat_group_price"
private const val PERFORMANCE_SCHEDULE_ID = "performance_schedule_id"
private const val SEAT_GROUP_ID = "seat_group_id"

@Entity
@Table(name = TABLE_NAME)
class PerformanceScheduleSeatGroupPrice(
    performanceSchedule: PerformanceSchedule,
    seatGroup: SeatGroup,
    price: Money
) : BaseEntity() {

    @Column(nullable = false)
    var price: Money = price
        protected set

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = PERFORMANCE_SCHEDULE_ID, nullable = false, updatable = false)
    val performanceSchedule: PerformanceSchedule = performanceSchedule

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = SEAT_GROUP_ID, nullable = false, updatable = false)
    val seatGroup: SeatGroup = seatGroup

}