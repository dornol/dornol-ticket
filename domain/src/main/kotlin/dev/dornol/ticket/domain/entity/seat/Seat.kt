package dev.dornol.ticket.domain.entity.seat

import dev.dornol.ticket.domain.entity.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity

@Entity
class Seat(
    name: String
) : BaseEntity() {

    @Column(length = 30, nullable = false)
    val name: String = name

}