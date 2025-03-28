package dev.dornol.ticket.admin.api.app.service.site

import dev.dornol.ticket.admin.api.app.dto.site.request.SiteAddRequestDto
import dev.dornol.ticket.admin.api.app.dto.site.request.SiteSearchDto
import dev.dornol.ticket.admin.api.app.dto.site.response.SiteListDto
import dev.dornol.ticket.admin.api.app.repository.manager.ManagerRepository
import dev.dornol.ticket.admin.api.app.repository.site.SiteRepository
import dev.dornol.ticket.domain.entity.site.Site
import org.apache.coyote.BadRequestException
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SiteService(
    private val siteRepository: SiteRepository,
    private val managerRepository: ManagerRepository
) {

    @Transactional(readOnly = false)
    fun search(search: SiteSearchDto, pageable: Pageable): Page<SiteListDto> {
        return siteRepository.search(search, pageable)
    }

    @Transactional
    fun save(userId: Long, site: SiteAddRequestDto): Site {
        val manager = managerRepository.findByIdOrNull(userId) ?: throw BadRequestException()
        return Site(site.name, site.address.toEntity(), manager.company).also { siteRepository.save(it) }
    }

}