package dev.dornol.ticket.manager.domain

import dev.dornol.ticket.common.domain.Domain

class Company(
    override val id: CompanyId,
    var name: String,
    var businessNumber: String,
) : Domain<CompanyId>(id)