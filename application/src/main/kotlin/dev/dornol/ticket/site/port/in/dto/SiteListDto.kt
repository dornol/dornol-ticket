package dev.dornol.ticket.site.port.`in`.dto

import dev.dornol.ticket.manager.domain.CompanyId
import dev.dornol.ticket.site.domain.SiteId
import dev.dornol.ticket.site.domain.value.Address

class SiteListDto(
    val id: SiteId,
    val name: String,
    val address: Address,
    val companyId: CompanyId
)
