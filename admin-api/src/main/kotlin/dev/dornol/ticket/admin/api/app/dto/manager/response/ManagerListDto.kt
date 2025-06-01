package dev.dornol.ticket.admin.api.app.dto.manager.response

import com.querydsl.core.annotations.QueryProjection
import dev.dornol.ticket.manager.adapter.out.jpa.CompanyEntity
import dev.dornol.ticket.manager.adapter.out.jpa.ManagerApprovalEntity
import dev.dornol.ticket.manager.domain.ManagerRole

data class ManagerListDto(
    val id: String,
    val username: String,
    val name: String,
    val phone: String,
    val email: String,
    val managerRole: ManagerRole,
    val approval: ManagerApprovalEntity,
    val company: CompanyDto?
) {

    @QueryProjection
    constructor(
        id: Long,
        username: String,
        name: String,
        phone: String,
        email: String,
        managerRole: ManagerRole,
        approval: ManagerApprovalEntity,
        company: CompanyEntity?
    ) : this(
        id.toString(),
        username,
        name,
        phone,
        email,
        managerRole,
        approval,
        company?.let { CompanyDto(it.name, it.businessNumber) })

}