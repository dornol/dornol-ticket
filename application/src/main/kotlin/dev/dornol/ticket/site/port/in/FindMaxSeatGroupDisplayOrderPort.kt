package dev.dornol.ticket.site.port.`in`

import dev.dornol.ticket.site.domain.SiteId

interface FindMaxSeatGroupDisplayOrderPort {

    fun findMaxDisplayOrderBySiteId(siteId: SiteId): Long

}