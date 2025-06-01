package dev.dornol.ticket.site.domain

import dev.dornol.ticket.common.domain.Domain

class SeatGroup(
    override val id: SeatGroupId,
    var name: String,
    val siteId: SiteId,
    var color: String,
    var displayOrder: Long = 0,
) : Domain<SeatGroupId>(id)