package dev.dornol.ticket.admin.api.app.dto.manager

import com.querydsl.core.annotations.QueryProjection
import dev.dornol.ticket.domain.entity.company.Company
import dev.dornol.ticket.domain.entity.manager.ManagerApproval
import dev.dornol.ticket.domain.entity.manager.ManagerRole

data class ManagerListDto(
    val id: String,
    val username: String,
    val name: String,
    val phone: String,
    val email: String,
    val managerRole: ManagerRole,
    val approval: ManagerApproval,
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
        approval: ManagerApproval,
        company: Company?
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