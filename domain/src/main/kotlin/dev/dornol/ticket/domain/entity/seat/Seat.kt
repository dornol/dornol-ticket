package dev.dornol.ticket.domain.entity.seat

import dev.dornol.ticket.domain.entity.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity
class Seat(
    name: String,
    group: SeatGroup,
) : BaseEntity() {

    @Column(length = 30, nullable = false)
    var name: String = name
        protected set

    @Embedded
    var offset: SeatOffset = SeatOffset()

    @Column(nullable = false)
    var displayOrder: Long = 0L

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seat_group_id", nullable = false, updatable = false)
    val group: SeatGroup = group

    fun moveTo(x: Double, y: Double) {
        offset = SeatOffset(x, y)
    }
}