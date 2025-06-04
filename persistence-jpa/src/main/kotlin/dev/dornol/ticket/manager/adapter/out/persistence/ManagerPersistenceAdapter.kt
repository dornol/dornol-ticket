package dev.dornol.ticket.manager.adapter.out.persistence

import dev.dornol.ticket.common.search.PageResult
import dev.dornol.ticket.common.toPageResult
import dev.dornol.ticket.manager.adapter.out.jpa.mapper.CompanyMapper
import dev.dornol.ticket.manager.adapter.out.jpa.mapper.ManagerManualMapper
import dev.dornol.ticket.manager.adapter.out.jpa.mapper.ManagerMapper
import dev.dornol.ticket.manager.adapter.out.persistence.query.ManagerQueryDslSupport
import dev.dornol.ticket.manager.application.port.out.*
import dev.dornol.ticket.manager.domain.Company
import dev.dornol.ticket.manager.domain.CompanyId
import dev.dornol.ticket.manager.domain.Manager
import dev.dornol.ticket.manager.domain.ManagerId
import dev.dornol.ticket.manager.domain.value.ManagerApproval
import org.springframework.stereotype.Repository

@Repository
class ManagerPersistenceAdapter(
    private val managerEntityRepository: ManagerEntityRepository,
    private val companyEntityRepository: CompanyEntityRepository,
    private val queryDslSupport: ManagerQueryDslSupport,

    private val managerMapper: ManagerMapper,
    private val managerManualMapper: ManagerManualMapper,
    private val companyMapper: CompanyMapper
) : SearchManagersPort, FindManagerPort, SaveManagerPort {

    override fun searchManagers(criteria: SearchManagersCriteria): PageResult<ManagerListDto> {
        val page = queryDslSupport.searchManagers(criteria)
        return page.toPageResult {
            ManagerListDto(
                id = ManagerId(it.id),
                username = it.username,
                email = it.email,
                phone = it.phone,
                name = it.name,
                managerRole = it.managerRole,
                approval = ManagerApproval(
                    approved = it.approval.approved,
                    approvedAt = it.approval.approvedAt,
                    approvedBy = it.approval.approvedBy?.let { by -> ManagerId(by) }
                ),
                company = CompanyDto(
                    id = CompanyId(it.company.id),
                    name = it.company.name,
                    businessNumber = it.company.businessNumber,
                )
            )
        }
    }

    override fun findByUsername(username: String): Manager? {
        return managerEntityRepository.findByUsername(username)?.let { managerMapper.toDomain(it) }
    }

    override fun save(manager: Manager, company: Company) {
        val companyEntity = companyMapper.toEntity(company)
        companyEntityRepository.save(companyEntity)

        val managerEntity = managerManualMapper.toEntity(manager, companyEntity)
        managerEntityRepository.save(managerEntity)
    }


}