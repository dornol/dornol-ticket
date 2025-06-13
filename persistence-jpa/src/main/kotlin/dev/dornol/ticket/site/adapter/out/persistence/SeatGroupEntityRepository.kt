package dev.dornol.ticket.site.adapter.out.persistence

import dev.dornol.ticket.site.adapter.out.jpa.SeatGroupEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface SeatGroupEntityRepository : JpaRepository<SeatGroupEntity, Long> {

    @Query(
        """
        select max(sg.displayOrder)
        from SeatGroupEntity sg 
        where sg.site.id = :siteId
    """
    )
    fun findMaxDisplayOrderBySiteId(siteId: Long): Long?

}