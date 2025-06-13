package dev.dornol.ticket.site.domain

import dev.dornol.ticket.common.domain.Domain

class SeatGroup(
    override val id: SeatGroupId,
    var name: String,
    val siteId: SiteId,
    var color: String,
    var displayOrder: Long = 0,
) : Domain<SeatGroupId>(id) {

    fun edit(name: String, color: String, displayOrder: Long) {
        this.name = name
        this.color = color
        this.displayOrder = displayOrder
    }

}