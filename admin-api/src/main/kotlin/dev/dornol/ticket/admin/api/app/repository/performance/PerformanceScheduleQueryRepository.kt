package dev.dornol.ticket.admin.api.app.repository.performance

import dev.dornol.ticket.admin.api.app.dto.performance.request.PerformanceScheduleSearchDto
import dev.dornol.ticket.admin.api.app.dto.performance.response.PerformanceScheduleListDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface PerformanceScheduleQueryRepository {

    fun search(companyId: Long, search: PerformanceScheduleSearchDto, pageable: Pageable): Page<PerformanceScheduleListDto>

}