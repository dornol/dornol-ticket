package dev.dornol.ticket.manager.adapter.out.persistence

import com.querydsl.core.annotations.QueryProjection
import dev.dornol.ticket.manager.adapter.out.jpa.ManagerApprovalEntity
import dev.dornol.ticket.manager.domain.ManagerRole

data class ManagerListProjection @QueryProjection constructor(
    val id: Long,
    val username: String,
    val name: String,
    val phone: String,
    val email: String,
    val managerRole: ManagerRole,
    val approval: ManagerApprovalEntity,
    val company: CompanyProjection
)
