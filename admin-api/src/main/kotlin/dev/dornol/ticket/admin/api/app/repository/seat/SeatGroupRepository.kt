package dev.dornol.ticket.admin.api.app.repository.seat

import dev.dornol.ticket.domain.entity.seat.SeatGroup
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface SeatGroupRepository : JpaRepository<SeatGroup, Long>, SeatGroupQueryRepository {

    @Query("""
        select max(sg.displayOrder)
        from SeatGroup sg 
        where sg.site.id = :siteId
    """)
    fun findMaxDisplayOrderBySiteId(siteId: Long): Long?

}