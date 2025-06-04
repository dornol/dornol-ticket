package dev.dornol.ticket.manager.adapter.out.jpa.mapper

import dev.dornol.ticket.manager.adapter.out.jpa.CompanyEntity
import dev.dornol.ticket.manager.adapter.out.jpa.ManagerApprovalEntity
import dev.dornol.ticket.manager.adapter.out.jpa.ManagerEntity
import dev.dornol.ticket.manager.domain.Manager
import org.springframework.stereotype.Component

@Component
class ManagerManualMapper {

    fun toEntity(manager: Manager, companyEntity: CompanyEntity): ManagerEntity {
        return ManagerEntity(
            username = manager.username,
            password = manager.password,
            name = manager.name,
            phone = manager.phone,
            email = manager.email,
            role = manager.role,
            approval = ManagerApprovalEntity(
                approved = manager.approval.approved,
                approvedBy = manager.approval.approvedBy?.get(),
                approvedAt = manager.approval.approvedAt,
            ),
            company = companyEntity,
        )
    }

}