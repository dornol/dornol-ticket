package dev.dornol.ticket.domain.entity.seat

import dev.dornol.ticket.domain.entity.BaseEntity
import dev.dornol.ticket.domain.entity.site.Site
import jakarta.persistence.*

private const val TABLE_NAME = "seat_group"
private const val SITE_ID = "site_id"

@Table(name = TABLE_NAME)
@Entity
class SeatGroup(
    name: String,
    site: Site,
    color: String,
    displayOrder: Long = 0,
) : BaseEntity() {

    @Column(length = 30, nullable = false)
    var name: String = name
        protected set

    @Column(nullable = false)
    var displayOrder: Long = displayOrder

    @Column(length = 7, nullable = false)
    var color: String = color

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = SITE_ID, nullable = false, updatable = false)
    val site: Site = site


    fun update(name: String, color: String) {
        this.name = name
        this.color = color
    }

}