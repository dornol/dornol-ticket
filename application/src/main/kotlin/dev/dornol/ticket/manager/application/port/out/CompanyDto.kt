package dev.dornol.ticket.manager.application.port.out

import dev.dornol.ticket.manager.domain.CompanyId

data class CompanyDto(
    val id: CompanyId,
    val name: String,
    val businessNumber: String,
)
