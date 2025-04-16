package dev.dornol.ticket.admin.api.app.service.performance

import dev.dornol.ticket.admin.api.app.dto.performance.request.PerformanceSearchDto
import dev.dornol.ticket.admin.api.app.dto.performance.response.PerformanceListDto
import dev.dornol.ticket.admin.api.app.repository.manager.ManagerRepository
import dev.dornol.ticket.admin.api.app.repository.performance.PerformanceRepository
import dev.dornol.ticket.admin.api.app.repository.site.SiteRepository
import dev.dornol.ticket.admin.api.config.exception.common.AccessDeniedException
import dev.dornol.ticket.admin.api.config.exception.common.BadRequestException
import dev.dornol.ticket.admin.api.util.alive
import dev.dornol.ticket.admin.api.util.assertAccess
import dev.dornol.ticket.domain.entity.performance.Performance
import dev.dornol.ticket.domain.entity.performance.PerformanceType
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PerformanceService(
    private val performanceRepository: PerformanceRepository,
    private val siteRepository: SiteRepository,
    private val managerRepository: ManagerRepository
) {

    @Transactional(readOnly = true)
    fun search(search: PerformanceSearchDto, pageable: Pageable): Page<PerformanceListDto> =
        performanceRepository.search(search, pageable)

    @Transactional
    fun add(userId: Long, name: String, type: PerformanceType, siteId: Long): Performance {
        val site = siteRepository.findByIdOrNull(siteId)?.alive() ?: throw BadRequestException()
        val manager = managerRepository.findByIdOrNull(userId)?.alive() ?: throw AccessDeniedException()
        assertAccess(site.company.id == manager.company.id)

        return Performance(
            name = name,
            type = type,
            site = site,
        ).also { performanceRepository.save(it) }
    }

    @Transactional
    fun edit(userId: Long, id: Long, name: String, type: PerformanceType) {
        val performance = performanceRepository.findByIdOrNull(id)?.alive() ?: throw BadRequestException()
        val site = siteRepository.findByIdOrNull(performance.site.id)?.alive() ?: throw BadRequestException()
        val manager = managerRepository.findByIdOrNull(userId)?.alive() ?: throw AccessDeniedException()
        assertAccess(site.company.id == manager.company.id)

        performance.edit(name, type)
    }

    @Transactional
    fun delete(userId: Long, id: Long) {
        val performance = performanceRepository.findByIdOrNull(id)?.alive() ?: throw BadRequestException()
        val site = siteRepository.findByIdOrNull(performance.site.id)?.alive() ?: throw BadRequestException()
        val manager = managerRepository.findByIdOrNull(userId)?.alive() ?: throw AccessDeniedException()
        assertAccess(site.company.id == manager.company.id)

        performance.delete(userId)
    }

}