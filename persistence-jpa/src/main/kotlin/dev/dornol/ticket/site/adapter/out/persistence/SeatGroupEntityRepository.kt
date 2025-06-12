package dev.dornol.ticket.site.adapter.out.persistence

import dev.dornol.ticket.site.adapter.out.jpa.SeatGroupEntity
import org.springframework.data.jpa.repository.JpaRepository

interface SeatGroupEntityRepository : JpaRepository<SeatGroupEntity, Long> {
}