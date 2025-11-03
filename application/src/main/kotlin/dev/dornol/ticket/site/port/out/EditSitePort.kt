package dev.dornol.ticket.site.port.out

import dev.dornol.ticket.site.domain.Site

interface EditSitePort {

    fun edit(site: Site)

}