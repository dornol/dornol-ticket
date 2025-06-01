package dev.dornol.ticket.admin.api.app.repository.seat

import dev.dornol.ticket.domain.entity.seat.SeatGroupEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface SeatGroupRepository : JpaRepository<SeatGroupEntity, Long>, SeatGroupQueryRepository {

    @Query(
        """
        select max(sg.displayOrder)
        from SeatGroupEntity sg 
        where sg.site.id = :siteId
    """
    )
    fun findMaxDisplayOrderBySiteId(siteId: Long): Long?

}