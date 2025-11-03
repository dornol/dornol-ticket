package dev.dornol.ticket.manager.adapter.out.jpa.mapper

import dev.dornol.ticket.manager.adapter.out.jpa.CompanyEntity
import dev.dornol.ticket.manager.adapter.out.jpa.ManagerApprovalEntity
import dev.dornol.ticket.manager.adapter.out.jpa.ManagerEntity
import dev.dornol.ticket.manager.domain.CompanyId
import dev.dornol.ticket.manager.domain.Manager
import dev.dornol.ticket.manager.domain.ManagerId
import dev.dornol.ticket.manager.domain.value.ManagerApproval

fun ManagerApprovalEntity.toDomain(): ManagerApproval {
    return ManagerApproval(
        approved = this.approved,
        approvedAt = this.approvedAt,
        approvedBy = this.approvedBy?.let { ManagerId(it) }
    )
}

fun ManagerEntity.toDomain(): Manager {
    return Manager(
        id = ManagerId(this.id),
        username = this.username,
        password = this.password,
        name = this.name,
        email = this.email,
        phone = this.phone,
        role = this.role,
        approval = this.approval.toDomain(),
        companyId = CompanyId(this.company.id)
    )
}

fun Manager.toEntity(companyEntity: CompanyEntity): ManagerEntity {
    return ManagerEntity(
        id = this.id.get(),
        username = this.username,
        password = this.password,
        name = this.name,
        phone = this.phone,
        email = this.email,
        role = this.role,
        approval = ManagerApprovalEntity(
            approved = this.approval.approved,
            approvedBy = this.approval.approvedBy?.get(),
            approvedAt = this.approval.approvedAt,
        ),
        company = companyEntity,
    )
}