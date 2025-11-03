package dev.dornol.ticket.site.adapter.out.jpa.mapper

import dev.dornol.ticket.site.adapter.out.jpa.SeatGroupEntity
import dev.dornol.ticket.site.adapter.out.jpa.SiteEntity
import dev.dornol.ticket.site.domain.SeatGroup
import dev.dornol.ticket.site.domain.SeatGroupId
import dev.dornol.ticket.site.domain.SiteId

fun SeatGroupEntity.toDomain() = SeatGroup(
    id = SeatGroupId(this.id),
    name = this.name,
    siteId = SiteId(this.site.id),
    color = this.color,
    displayOrder = this.displayOrder,
)

fun SeatGroup.toEntity(siteEntity: SiteEntity) = SeatGroupEntity(
    id = this.id.get(),
    name = this.name,
    site = siteEntity,
    color = this.color,
    displayOrder = this.displayOrder,
)