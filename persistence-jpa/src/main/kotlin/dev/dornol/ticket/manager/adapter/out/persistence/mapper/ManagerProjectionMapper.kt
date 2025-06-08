package dev.dornol.ticket.manager.adapter.out.persistence.mapper

import dev.dornol.ticket.manager.adapter.out.jpa.mapper.toDomain
import dev.dornol.ticket.manager.adapter.out.persistence.ManagerListProjection
import dev.dornol.ticket.manager.application.port.`in`.dto.ManagerListDto
import dev.dornol.ticket.manager.domain.ManagerId

fun ManagerListProjection.toManagerListDto(): ManagerListDto = this.let {
    ManagerListDto(
        id = ManagerId(it.id),
        username = it.username,
        email = it.email,
        phone = it.phone,
        name = it.name,
        managerRole = it.managerRole,
        approval = it.approval.toDomain(),
        company = it.company.toCompanyDto()
    )
}