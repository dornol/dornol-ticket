package dev.dornol.ticket.site.adapter.out.persistence

import com.querydsl.core.annotations.QueryProjection
import dev.dornol.ticket.site.adapter.out.jpa.AddressEntity

data class SiteListProjection @QueryProjection constructor(
    val id: Long,
    val name: String,
    val address: AddressEntity,
    val companyId: Long
)
