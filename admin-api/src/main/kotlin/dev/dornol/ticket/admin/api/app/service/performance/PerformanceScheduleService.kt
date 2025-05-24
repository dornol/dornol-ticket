package dev.dornol.ticket.admin.api.app.service.performance

import dev.dornol.ticket.admin.api.app.dto.performance.request.PerformanceScheduleSearchDto
import dev.dornol.ticket.admin.api.app.dto.performance.response.PerformanceScheduleDetailDto
import dev.dornol.ticket.admin.api.app.repository.performance.PerformanceRepository
import dev.dornol.ticket.admin.api.app.repository.performance.PerformanceScheduleRepository
import dev.dornol.ticket.admin.api.app.repository.site.SiteRepository
import dev.dornol.ticket.admin.api.app.service.common.SecurityService
import dev.dornol.ticket.admin.api.config.exception.common.BadRequestException
import dev.dornol.ticket.admin.api.util.alive
import dev.dornol.ticket.domain.entity.performance.PerformanceSchedule
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import java.time.LocalTime

@Service
class PerformanceScheduleService(
    private val performanceScheduleRepository: PerformanceScheduleRepository,
    private val performanceRepository: PerformanceRepository,
    private val siteRepository: SiteRepository,
    private val securityService: SecurityService
) {

    @Transactional(readOnly = true)
    fun search(searchDto: PerformanceScheduleSearchDto, pageable: Pageable) =
        performanceScheduleRepository.search(securityService.getCompanyId(), searchDto, pageable)

    @Transactional(readOnly = true)
    fun findWithDetailsById(scheduleId: Long): PerformanceScheduleDetailDto {
        val schedule =
            performanceScheduleRepository.findWithDetailsById(scheduleId)?.alive() ?: throw BadRequestException()
        securityService.assertCompanyId(schedule.performance.company.id!!)

        return PerformanceScheduleDetailDto(schedule)
    }

    @Transactional
    fun add(
        performanceId: Long,
        siteId: Long,
        performanceDate: LocalDate,
        performanceTime: LocalTime
    ): PerformanceSchedule {
        val performance = performanceRepository.findByIdOrNull(performanceId)?.alive() ?: throw BadRequestException()
        val site = siteRepository.findByIdOrNull(siteId)?.alive() ?: throw BadRequestException()
        securityService.assertCompanyId(performance.company.id!!)
        securityService.assertCompanyId(site.company.id!!)

        return PerformanceSchedule(
            performance = performance,
            site = site,
            performanceDate = performanceDate,
            performanceTime = performanceTime,
        ).also { performanceScheduleRepository.save(it) }
    }

    @Transactional
    fun edit(
        performanceScheduleId: Long,
        performanceDate: LocalDate,
        performanceTime: LocalTime
    ) {
        val schedule = findValidSchedule(performanceScheduleId)
        schedule.edit(performanceDate, performanceTime)
    }

    @Transactional
    fun delete(
        performanceScheduleId: Long,
    ) {
        val schedule = findValidSchedule(performanceScheduleId)
        val userId = securityService.getUserId()
        schedule.delete(userId)
    }

    private fun findValidSchedule(
        performanceScheduleId: Long,
    ): PerformanceSchedule {
        val schedule =
            performanceScheduleRepository.findByIdOrNull(performanceScheduleId)?.alive() ?: throw BadRequestException()
        securityService.assertCompanyId(schedule.performance.company.id!!)
        securityService.assertCompanyId(schedule.site.company.id!!)

        return schedule
    }

}