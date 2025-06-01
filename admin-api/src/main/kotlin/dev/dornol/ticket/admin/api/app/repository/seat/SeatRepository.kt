package dev.dornol.ticket.admin.api.app.repository.seat

import dev.dornol.ticket.domain.entity.seat.SeatEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface SeatRepository : JpaRepository<SeatEntity, Long> {

    @Query(
        """
        select s
        from SeatEntity s
        where s.seatGroup.id = :groupId
          and s.deleted is false
        order by s.displayOrder
    """
    )
    fun findAllByGroupId(@Param("groupId") seatGroupId: Long): List<SeatEntity>

    @Query(
        """
        select max(s.displayOrder)
        from SeatEntity s
        where s.seatGroup.id = :groupId
          and s.deleted is false
    """
    )
    fun maxDisplayOrderByGroupId(@Param("groupId") groupId: Long): Long?

}