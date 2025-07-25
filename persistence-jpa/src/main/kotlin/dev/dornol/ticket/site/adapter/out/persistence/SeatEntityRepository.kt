package dev.dornol.ticket.site.adapter.out.persistence

import dev.dornol.ticket.site.adapter.out.jpa.SeatEntity
import org.springframework.data.jpa.repository.JpaRepository

interface SeatEntityRepository : JpaRepository<SeatEntity, Long> {
}