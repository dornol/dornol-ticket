package dev.dornol.ticket.admin.api.app.repository.site

import dev.dornol.ticket.domain.entity.site.Site
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SiteRepository : JpaRepository<Site, Long>, SiteQueryRepository {

}