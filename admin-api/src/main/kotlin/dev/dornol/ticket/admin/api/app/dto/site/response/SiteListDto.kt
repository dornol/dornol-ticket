package dev.dornol.ticket.admin.api.app.dto.site.response

import com.querydsl.core.annotations.QueryProjection
import dev.dornol.ticket.domain.entity.site.address.Address

data class SiteListDto @QueryProjection constructor(
    val id: String,
    val name: String,
    val address: Address,
    val companyId: String
)