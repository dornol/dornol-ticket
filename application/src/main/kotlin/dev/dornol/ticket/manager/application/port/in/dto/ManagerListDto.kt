package dev.dornol.ticket.manager.application.port.`in`.dto

import dev.dornol.ticket.manager.application.port.out.CompanyDto
import dev.dornol.ticket.manager.domain.ManagerId
import dev.dornol.ticket.manager.domain.ManagerRole
import dev.dornol.ticket.manager.domain.value.ManagerApproval

data class ManagerListDto(
    val id: ManagerId,
    val username: String,
    val name: String,
    val phone: String,
    val email: String,
    val managerRole: ManagerRole,
    val approval: ManagerApproval,
    val company: CompanyDto
)