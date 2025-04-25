package dev.dornol.ticket.admin.api.app.dto.performance.response

import dev.dornol.ticket.domain.entity.site.address.Address

data class SiteSimpleDto(
    val id: String,
    val name: String,
    val address: Address,
)