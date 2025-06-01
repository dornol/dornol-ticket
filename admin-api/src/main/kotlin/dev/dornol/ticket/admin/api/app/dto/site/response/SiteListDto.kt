package dev.dornol.ticket.admin.api.app.dto.site.response

import com.querydsl.core.annotations.QueryProjection
import dev.dornol.ticket.domain.entity.site.address.AddressEntity

data class SiteListDto(
    val id: String,
    val name: String,
    val address: AddressEntity,
    val companyId: String
) {
    @QueryProjection
    constructor(
        id: Long,
        name: String,
        address: AddressEntity,
        companyId: Long
    ) : this(id.toString(), name, address, companyId.toString())
}