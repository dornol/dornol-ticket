package dev.dornol.ticket.site.port.`in`

import dev.dornol.ticket.site.port.`in`.dto.SiteDto

interface FindSiteUseCase {

    fun findById(id: Long): SiteDto

}