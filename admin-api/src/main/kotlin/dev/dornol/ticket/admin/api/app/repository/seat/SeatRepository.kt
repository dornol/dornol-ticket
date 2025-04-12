package dev.dornol.ticket.admin.api.app.repository.seat

import dev.dornol.ticket.domain.entity.seat.Seat
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface SeatRepository : JpaRepository<Seat, Long> {

    @Query("""
        select s
        from Seat s
        where s.group.id = :groupId
          and s.deleted is false
        order by s.displayOrder
    """)
    fun findAllByGroupId(@Param("groupId") seatGroupId: Long): List<Seat>

}