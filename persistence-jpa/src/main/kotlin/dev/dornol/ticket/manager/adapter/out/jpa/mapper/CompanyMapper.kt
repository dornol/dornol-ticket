package dev.dornol.ticket.manager.adapter.out.jpa.mapper

import dev.dornol.ticket.manager.adapter.out.jpa.CompanyEntity
import dev.dornol.ticket.manager.domain.Company
import dev.dornol.ticket.manager.domain.CompanyId


fun CompanyEntity.toDomain(): Company {
    return Company(
        id = CompanyId(this.id),
        name = this.name,
        businessNumber = this.businessNumber,
    )
}

fun Company.toEntity(): CompanyEntity {
    return CompanyEntity(
        id = this.id.get(),
        name = this.name,
        businessNumber = this.businessNumber,
    )
}