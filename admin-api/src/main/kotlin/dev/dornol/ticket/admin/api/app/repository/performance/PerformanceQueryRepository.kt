package dev.dornol.ticket.admin.api.app.repository.performance

import dev.dornol.ticket.admin.api.app.dto.performance.request.PerformanceSearchDto
import dev.dornol.ticket.admin.api.app.dto.performance.response.PerformanceListDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface PerformanceQueryRepository {

    fun search(search: PerformanceSearchDto, pageable: Pageable): Page<PerformanceListDto>

}