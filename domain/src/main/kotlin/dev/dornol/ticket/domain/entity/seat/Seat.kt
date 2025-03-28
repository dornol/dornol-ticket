package dev.dornol.ticket.domain.entity.seat

import dev.dornol.ticket.domain.entity.BaseEntity
import jakarta.persistence.Column
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seat_group_id", nullable = false, updatable = false)
    val group: SeatGroup = group
}