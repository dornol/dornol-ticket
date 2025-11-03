package dev.dornol.ticket.site.port.out

import dev.dornol.ticket.site.domain.Site

interface FindSitePort {

    fun findById(id: Long): Site?

}