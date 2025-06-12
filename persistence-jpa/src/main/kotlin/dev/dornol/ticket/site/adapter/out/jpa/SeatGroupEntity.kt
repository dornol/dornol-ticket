package dev.dornol.ticket.site.adapter.out.jpa

import dev.dornol.ticket.domain.entity.BaseEntity
import jakarta.persistence.*

@Table(name = "seat_group")
@Entity
class SeatGroupEntity(
    id: Long,
    name: String,
    site: SiteEntity,
    color: String,
    displayOrder: Long = 0,
) : BaseEntity(id) {

    @Column(length = 30, nullable = false)
    var name: String = name
        protected set

    @Column(nullable = false)
    var displayOrder: Long = displayOrder

    @Column(length = 7, nullable = false)
    var color: String = color

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "site_id", nullable = false, updatable = false)
    val site: SiteEntity = site

    fun update(name: String, color: String) {
        this.name = name
        this.color = color
    }

}