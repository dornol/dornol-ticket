package dev.dornol.ticket.performance

import dev.dornol.ticket.common.application.infra.DomainIdGenerator
import dev.dornol.ticket.common.application.out.CurrentUserPort
import dev.dornol.ticket.common.exception.BadRequestException
import dev.dornol.ticket.common.search.PageResult
import dev.dornol.ticket.manager.domain.CompanyId
import dev.dornol.ticket.performance.domain.Performance
import dev.dornol.ticket.performance.domain.PerformanceId
import dev.dornol.ticket.performance.port.`in`.AddPerformanceCommand
import dev.dornol.ticket.performance.port.`in`.AddPerformanceUseCase
import dev.dornol.ticket.performance.port.`in`.DeletePerformanceUseCase
import dev.dornol.ticket.performance.port.`in`.EditPerformanceCommand
import dev.dornol.ticket.performance.port.`in`.EditPerformanceUseCase
import dev.dornol.ticket.performance.port.`in`.FindPerformanceUseCase
import dev.dornol.ticket.performance.port.out.FindPerformancePort
import dev.dornol.ticket.performance.port.`in`.SearchPerformancesCommand
import dev.dornol.ticket.performance.port.`in`.SearchPerformancesUseCase
import dev.dornol.ticket.performance.port.`in`.dto.PerformanceDetailDto
import dev.dornol.ticket.performance.port.`in`.dto.PerformanceListDto
import dev.dornol.ticket.performance.port.out.AddPerformancePort
import dev.dornol.ticket.performance.port.out.DeletePerformancePort
import dev.dornol.ticket.performance.port.out.EditPerformancePort
import dev.dornol.ticket.performance.port.out.SearchPerformancesCriteria
import dev.dornol.ticket.performance.port.out.SearchPerformancesPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
open class PerformanceService(
    private val searchPerformancesPort: SearchPerformancesPort,
    private val addPerformancePort: AddPerformancePort,
    private val editPerformancePort: EditPerformancePort,
    private val deletePerformancePort: DeletePerformancePort,
    private val findPerformancePort: FindPerformancePort,

    private val performanceIdGenerator: DomainIdGenerator,
    private val currentUserPort: CurrentUserPort
) : SearchPerformancesUseCase, FindPerformanceUseCase, AddPerformanceUseCase, EditPerformanceUseCase,
    DeletePerformanceUseCase {

    @Transactional(readOnly = true)
    override fun searchPerformances(command: SearchPerformancesCommand): PageResult<PerformanceListDto> {
        return searchPerformancesPort.searchPerformances(
            SearchPerformancesCriteria(
                searchKeys = command.searchKeys,
                searchText = command.searchText,
                pageQuery = command.pageQuery,
                companyId = currentUserPort.getCurrentUserCompanyId()
            )
        )
    }

    @Transactional(readOnly = true)
    override fun findById(id: Long): PerformanceDetailDto {
        val performance = findPerformancePort.findById(PerformanceId(id)) ?: throw BadRequestException()
        currentUserPort.matchCompanyId(performance.companyId.get())
        return PerformanceDetailDto(
            id = performance.id,
            name = performance.name,
            type = performance.type,
        )
    }

    @Transactional
    override fun add(command: AddPerformanceCommand) {
        val id = performanceIdGenerator.generate<PerformanceId>()
        val companyId = currentUserPort.getCurrentUserCompanyId()
        val performance = Performance(
            id = id,
            name = command.name,
            type = command.type,
            companyId = CompanyId(companyId)
        )
        addPerformancePort.add(performance)
    }

    @Transactional
    override fun edit(id: Long, command: EditPerformanceCommand) {
        val performance = findPerformancePort.findById(PerformanceId(id)) ?: throw BadRequestException()
        currentUserPort.matchCompanyId(performance.companyId.get())
        performance.edit(command.name, command.type)
        editPerformancePort.edit(performance)
    }

    @Transactional
    override fun delete(id: Long) {
        val performance = findPerformancePort.findById(PerformanceId(id)) ?: throw BadRequestException()
        currentUserPort.matchCompanyId(performance.companyId.get())
        deletePerformancePort.delete(performance, currentUserPort.getCurrentUserId())
    }

}