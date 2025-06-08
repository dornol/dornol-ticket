package dev.dornol.ticket.site.adapter.out.persistence

import dev.dornol.ticket.domain.entity.site.SiteEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface SiteEntityRepository : JpaRepository<SiteEntity, Long> {

    @Query("""
        select s
        from SiteEntity s
            join fetch s.seatingMapFile
        where s.id = :id
    """)
    fun findWithFileByIdOrNull(@Param("id") id: Long): SiteEntity?

}