package dev.dornol.ticket.domain.entity.seat

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
data class SeatOffsetEntity(
    @Column(nullable = false)
    val x: Double = 0.0,

    @Column(nullable = false)
    val y: Double = 0.0,
)
