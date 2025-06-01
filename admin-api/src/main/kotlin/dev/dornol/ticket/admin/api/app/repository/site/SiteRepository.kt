package dev.dornol.ticket.admin.api.app.repository.site

import dev.dornol.ticket.domain.entity.site.SiteEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface SiteRepository : JpaRepository<SiteEntity, Long>, SiteQueryRepository {

    @Query(
        """
            select s
            from SiteEntity s
                inner join fetch s.seatingMapFile f 
            where s.id = :id
        """
    )
    fun findWithSeatingMapById(@Param("id") id: Long): SiteEntity?

}