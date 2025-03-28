package dev.dornol.ticket.admin.api.app.repository.site

import dev.dornol.ticket.admin.api.app.dto.site.request.SiteSearchDto
import dev.dornol.ticket.admin.api.app.dto.site.response.SiteListDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface SiteQueryRepository {

    fun search(search: SiteSearchDto, pageable: Pageable): Page<SiteListDto>

}