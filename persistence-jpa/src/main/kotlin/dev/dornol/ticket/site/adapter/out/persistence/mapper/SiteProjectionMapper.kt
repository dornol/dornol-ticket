package dev.dornol.ticket.site.adapter.out.persistence.mapper

import dev.dornol.ticket.manager.domain.CompanyId
import dev.dornol.ticket.site.adapter.out.jpa.mapper.toDomain
import dev.dornol.ticket.site.adapter.out.persistence.SiteListProjection
import dev.dornol.ticket.site.domain.SiteId
import dev.dornol.ticket.site.port.`in`.dto.SiteListDto

fun SiteListProjection.toSiteListDto(): SiteListDto {
    return SiteListDto(
        id = SiteId(this.id),
        name = this.name,
        address = this.address.toDomain(),
        companyId = CompanyId(this.companyId)
    )
}