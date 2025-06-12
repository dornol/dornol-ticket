package dev.dornol.ticket.site.adapter.out.jpa

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
data class SeatOffsetEntity(
    @Column(nullable = false)
    val x: Double = 0.0,

    @Column(nullable = false)
    val y: Double = 0.0,
)
