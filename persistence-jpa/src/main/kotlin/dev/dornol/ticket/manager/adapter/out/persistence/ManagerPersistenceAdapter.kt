package dev.dornol.ticket.manager.adapter.out.persistence

import dev.dornol.ticket.common.search.PageResult
import dev.dornol.ticket.common.toPageResult
import dev.dornol.ticket.manager.adapter.out.jpa.mapper.ManagerEntityUpdater
import dev.dornol.ticket.manager.adapter.out.jpa.mapper.toDomain
import dev.dornol.ticket.manager.adapter.out.jpa.mapper.toEntity
import dev.dornol.ticket.manager.adapter.out.persistence.query.ManagerQueryDslSupport
import dev.dornol.ticket.manager.application.port.out.*
import dev.dornol.ticket.manager.domain.Company
import dev.dornol.ticket.manager.domain.CompanyId
import dev.dornol.ticket.manager.domain.Manager
import dev.dornol.ticket.manager.domain.ManagerId
import dev.dornol.ticket.manager.domain.value.ManagerApproval
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class ManagerPersistenceAdapter(
    private val managerEntityRepository: ManagerEntityRepository,
    private val companyEntityRepository: CompanyEntityRepository,
    private val queryDslSupport: ManagerQueryDslSupport,

    private val managerEntityUpdater: ManagerEntityUpdater
) : SearchManagersPort, SaveManagerPort, FindManagerByUsernamePort, FindManagerByIdPort, ApproveManagerPort {

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
        return managerEntityRepository.findByUsername(username)?.toDomain()
    }

    override fun save(manager: Manager, company: Company) {
        val companyEntity = company.toEntity()
        companyEntityRepository.save(companyEntity)

        managerEntityRepository.save(manager.toEntity(companyEntity))
    }

    override fun findManagerById(id: Long): Manager? {
        return managerEntityRepository.findByIdOrNull(id)?.toDomain()
    }

    override fun approveManager(manager: Manager) {
        val managerEntity = managerEntityRepository.findByIdOrNull(manager.id.get())!!
        managerEntityUpdater.updateEntityFromDomain(managerEntity, manager)
        managerEntityRepository.save(managerEntity)
    }

}