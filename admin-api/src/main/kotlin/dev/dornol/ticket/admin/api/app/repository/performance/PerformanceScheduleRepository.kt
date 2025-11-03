package dev.dornol.ticket.admin.api.app.repository.performance

import dev.dornol.ticket.performance.adapter.out.jpa.PerformanceScheduleEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface PerformanceScheduleRepository : JpaRepository<PerformanceScheduleEntity, Long>, PerformanceScheduleQueryRepository {

    @Query(
        """
        SELECT s
        FROM PerformanceScheduleEntity s
            join fetch s.performance
            join fetch s.site
            join fetch s.site.seatingMapFile
        WHERE s.id = :id
    """
    )
    fun findWithDetailsById(@Param("id") id: Long): PerformanceScheduleEntity?

}