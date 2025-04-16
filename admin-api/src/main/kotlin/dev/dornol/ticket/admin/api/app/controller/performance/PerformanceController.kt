package dev.dornol.ticket.admin.api.app.controller.performance

import dev.dornol.ticket.admin.api.app.constants.DEFAULT_SORT_ORDER
import dev.dornol.ticket.admin.api.app.dto.performance.request.PerformanceAddRequestDto
import dev.dornol.ticket.admin.api.app.dto.performance.request.PerformanceEditRequestDto
import dev.dornol.ticket.admin.api.app.dto.performance.request.PerformanceSearchDto
import dev.dornol.ticket.admin.api.app.service.performance.PerformanceService
import jakarta.validation.Valid
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.web.bind.annotation.*

@RequestMapping("/performances")
@RestController
class PerformanceController(
    private val performanceService: PerformanceService
) {

    @GetMapping
    fun search(
        search: PerformanceSearchDto,
        @PageableDefault(sort = [DEFAULT_SORT_ORDER]) page: Pageable
    ) = performanceService.search(search, page)

    @PostMapping
    fun add(
        @Valid @RequestBody dto: PerformanceAddRequestDto,
        @AuthenticationPrincipal jwt: Jwt,
    ) = performanceService.add(jwt.subject.toLong(), dto.name, dto.type, dto.siteId).id

    @PutMapping("/{id}")
    fun edit(
        @PathVariable id: Long,
        @Valid @RequestBody dto: PerformanceEditRequestDto,
        @AuthenticationPrincipal jwt: Jwt,
    ) = performanceService.edit(jwt.subject.toLong(), id, dto.name, dto.type)

    @DeleteMapping("/{id}")
    fun delete(
        @PathVariable id: Long,
        @Valid @RequestBody dto: PerformanceEditRequestDto,
        @AuthenticationPrincipal jwt: Jwt,
    ) = performanceService.delete(jwt.subject.toLong(), id)

}