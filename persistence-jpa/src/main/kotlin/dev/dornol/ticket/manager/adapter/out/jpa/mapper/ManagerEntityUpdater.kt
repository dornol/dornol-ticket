package dev.dornol.ticket.manager.adapter.out.jpa.mapper

import dev.dornol.ticket.manager.adapter.out.jpa.ManagerApprovalEntity
import dev.dornol.ticket.manager.adapter.out.jpa.ManagerEntity
import dev.dornol.ticket.manager.domain.Manager
import org.springframework.stereotype.Component

@Component
class ManagerEntityUpdater {

    fun updateEntityFromDomain(target: ManagerEntity, source: Manager) {
        target.update(
            password = source.password,
            name = source.name,
            phone = source.phone,
            email = source.email,
            approval = ManagerApprovalEntity(
                approved = source.approval.approved,
                approvedAt = source.approval.approvedAt,
                approvedBy = source.approval.approvedBy?.get()
            ),
            role = source.role
        )
    }

}
