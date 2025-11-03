package dev.dornol.ticket.admin.api.app.repository.site

import dev.dornol.ticket.site.adapter.out.jpa.SiteEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SiteRepository : JpaRepository<SiteEntity, Long> {

}