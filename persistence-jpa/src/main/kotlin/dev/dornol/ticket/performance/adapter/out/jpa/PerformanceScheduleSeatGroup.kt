package dev.dornol.ticket.performance.adapter.out.jpa

import dev.dornol.ticket.common.domain.Money
import dev.dornol.ticket.domain.converter.MoneyConverter
import dev.dornol.ticket.domain.entity.BaseEntity
import dev.dornol.ticket.site.adapter.out.jpa.SeatGroupEntity
import jakarta.persistence.*

@Table(name = "performance_schedule_seat_group")
@Entity
class PerformanceScheduleSeatGroup(
    id: Long,
    performanceSchedule: PerformanceScheduleEntity,
    seatGroup: SeatGroupEntity,
    price: Money
) : BaseEntity(id) {

    @Convert(converter = MoneyConverter::class)
    @Column(nullable = false)
    var price: Money = price
        protected set

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "performance_schedule_id", nullable = false, updatable = false)
    val performanceSchedule: PerformanceScheduleEntity = performanceSchedule

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "seat_group_id", nullable = false, updatable = false)
    val seatGroup: SeatGroupEntity = seatGroup

}