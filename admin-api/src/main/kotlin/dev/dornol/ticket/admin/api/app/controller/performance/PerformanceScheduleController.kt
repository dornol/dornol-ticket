package dev.dornol.ticket.admin.api.app.controller.performance

import dev.dornol.ticket.admin.api.app.constants.DEFAULT_SORT_ORDER
import dev.dornol.ticket.admin.api.app.dto.performance.request.PerformanceScheduleAddRequestDto
import dev.dornol.ticket.admin.api.app.dto.performance.request.PerformanceScheduleEditRequestDto
import dev.dornol.ticket.admin.api.app.dto.performance.request.PerformanceScheduleSearchDto
import dev.dornol.ticket.admin.api.app.service.performance.PerformanceScheduleService
import jakarta.validation.Valid
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.web.bind.annotation.*

@RequestMapping("/performance-schedules")
@RestController
class PerformanceScheduleController(
    private val performanceScheduleService: PerformanceScheduleService
) {

    @GetMapping
    fun search(
        search: PerformanceScheduleSearchDto,
        @PageableDefault(sort = [DEFAULT_SORT_ORDER]) pageable: Pageable
    ) = performanceScheduleService.search(search, pageable)

    @PostMapping
    fun add(@Valid @RequestBody request: PerformanceScheduleAddRequestDto) =
        performanceScheduleService.add(
            request.performanceId,
            request.siteId,
            request.performanceDate,
            request.performanceTime
        )

    @PatchMapping("/{id}")
    fun edit(
        @PathVariable id: Long,
        @Valid @RequestBody request: PerformanceScheduleEditRequestDto
    ) = performanceScheduleService.edit(id, request.performanceDate, request.performanceTime)

    @DeleteMapping("/{id}")
    fun delete(
        @PathVariable id: Long,
    ) = performanceScheduleService.delete(id)

}