package dev.dornol.ticket.site.port.out

import dev.dornol.ticket.site.domain.Site

interface SaveSitePort {

    fun save(site: Site)

}