package dev.dornol.ticket.performance.adapter.out.persistence

import dev.dornol.ticket.common.alive
import dev.dornol.ticket.common.search.PageResult
import dev.dornol.ticket.common.toPageResult
import dev.dornol.ticket.manager.adapter.out.persistence.CompanyEntityRepository
import dev.dornol.ticket.manager.domain.CompanyId
import dev.dornol.ticket.performance.adapter.out.jpa.PerformanceEntity
import dev.dornol.ticket.performance.adapter.out.persistence.query.PerformanceQueryDslSupport
import dev.dornol.ticket.performance.domain.Performance
import dev.dornol.ticket.performance.domain.PerformanceId
import dev.dornol.ticket.performance.port.out.FindPerformancePort
import dev.dornol.ticket.performance.port.`in`.dto.PerformanceListDto
import dev.dornol.ticket.performance.port.out.AddPerformancePort
import dev.dornol.ticket.performance.port.out.DeletePerformancePort
import dev.dornol.ticket.performance.port.out.EditPerformancePort
import dev.dornol.ticket.performance.port.out.SearchPerformancesCriteria
import dev.dornol.ticket.performance.port.out.SearchPerformancesPort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import java.lang.IllegalStateException

@Repository
class PerformancePersistenceAdapter(
    private val performanceEntityRepository: PerformanceEntityRepository,
    private val performanceQueryDslSupport: PerformanceQueryDslSupport,
    private val companyEntityRepository: CompanyEntityRepository,
) : SearchPerformancesPort, AddPerformancePort, EditPerformancePort, DeletePerformancePort, FindPerformancePort {

    override fun searchPerformances(criteria: SearchPerformancesCriteria): PageResult<PerformanceListDto> {
        return performanceQueryDslSupport.search(criteria).toPageResult { it.toPerformanceListDto() }
    }

    override fun add(performance: Performance) {
        val performanceEntity = PerformanceEntity(
            id = performance.id.get(),
            name = performance.name,
            type = performance.type,
            company = companyEntityRepository.getReferenceById(performance.companyId.get())
        )
        performanceEntityRepository.save(performanceEntity)
    }

    override fun edit(performance: Performance) {
        val performanceEntity = performanceEntityRepository.findByIdOrNull(performance.id.get())
            ?.alive()
            ?: throw IllegalStateException()
        performanceEntity.edit(performanceEntity.name, performanceEntity.type)
        performanceEntityRepository.save(performanceEntity)
    }

    override fun delete(performance: Performance, deletedBy: Long) {
        val performanceEntity = performanceEntityRepository.findByIdOrNull(performance.id.get())
            ?.alive()
            ?: throw IllegalStateException()
        performanceEntity.delete(deletedBy)
        performanceEntityRepository.save(performanceEntity)
    }

    override fun findById(id: PerformanceId): Performance? {
        return performanceEntityRepository.findByIdOrNull(id.get())
            ?.alive()
            ?.let {
                Performance(
                    id = PerformanceId(it.id),
                    name = it.name,
                    type = it.type,
                    companyId = CompanyId(it.company.id)
                )
            }
    }

}