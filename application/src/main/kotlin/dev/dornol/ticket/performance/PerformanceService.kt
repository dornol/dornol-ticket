package dev.dornol.ticket.performance

import dev.dornol.ticket.common.application.out.CurrentUserPort
import dev.dornol.ticket.common.search.PageResult
import dev.dornol.ticket.performance.port.`in`.SearchPerformancesCommand
import dev.dornol.ticket.performance.port.`in`.SearchPerformancesUseCase
import dev.dornol.ticket.performance.port.`in`.dto.PerformanceListDto
import dev.dornol.ticket.performance.port.out.SearchPerformancesCriteria
import dev.dornol.ticket.performance.port.out.SearchPerformancesPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
open class PerformanceService(
    private val searchPerformancesPort: SearchPerformancesPort,

    private val currentUserPort: CurrentUserPort
) : SearchPerformancesUseCase {

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

}