package dev.dornol.ticket.performance.adapter.`in`.web

import dev.dornol.ticket.admin.api.app.constants.DEFAULT_SORT_ORDER
import dev.dornol.ticket.performance.adapter.`in`.web.dto.PerformanceAddRequestDto
import dev.dornol.ticket.performance.adapter.`in`.web.dto.PerformanceEditRequestDto
import dev.dornol.ticket.performance.adapter.`in`.web.dto.PerformanceSearchDto
import dev.dornol.ticket.performance.adapter.`in`.web.mapper.toSearchPerformancesCommand
import dev.dornol.ticket.performance.port.`in`.*
import jakarta.validation.Valid
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.web.bind.annotation.*

@RequestMapping("/performances")
@RestController
class PerformanceController(
    private val searchPerformancesUseCase: SearchPerformancesUseCase,
    private val findPerformanceUseCase: FindPerformanceUseCase,
    private val addPerformanceUseCase: AddPerformanceUseCase,
    private val editPerformanceUseCase: EditPerformanceUseCase,
    private val deletePerformanceUseCase: DeletePerformanceUseCase
) {

    @GetMapping
    fun search(
        search: PerformanceSearchDto,
        @PageableDefault(sort = [DEFAULT_SORT_ORDER]) page: Pageable
    ) = searchPerformancesUseCase.searchPerformances(search.toSearchPerformancesCommand(page))

    @GetMapping("/{id}")
    fun get(
        @PathVariable id: Long
    ) = findPerformanceUseCase.findById(id)

    @PostMapping
    fun add(
        @Valid @RequestBody dto: PerformanceAddRequestDto
    ) = addPerformanceUseCase.add(
        AddPerformanceCommand(
            name = dto.name,
            type = dto.type
        )
    )

    @PutMapping("/{id}")
    fun edit(
        @PathVariable id: Long,
        @Valid @RequestBody dto: PerformanceEditRequestDto
    ) = editPerformanceUseCase.edit(id, EditPerformanceCommand(name = dto.name, type = dto.type))

    @DeleteMapping("/{id}")
    fun delete(
        @PathVariable id: Long
    ) = deletePerformanceUseCase.delete(id)

}