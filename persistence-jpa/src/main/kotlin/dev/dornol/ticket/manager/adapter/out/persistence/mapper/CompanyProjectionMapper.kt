package dev.dornol.ticket.manager.adapter.out.persistence.mapper

import dev.dornol.ticket.manager.adapter.out.persistence.CompanyProjection
import dev.dornol.ticket.manager.application.port.out.CompanyDto
import dev.dornol.ticket.manager.domain.CompanyId

fun CompanyProjection.toCompanyDto(): CompanyDto = this.let {
    CompanyDto(
        id = CompanyId(it.id),
        name = it.name,
        businessNumber = it.businessNumber,
    )
}