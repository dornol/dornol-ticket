package dev.dornol.ticket.domain.entity.performance

import dev.dornol.ticket.domain.entity.BaseEntity
import dev.dornol.ticket.domain.entity.site.Site
import jakarta.persistence.*

@Entity
class Performance(
    name: String,
    type: PerformanceType,
    site: Site
) : BaseEntity() {

    @Column(length = 255, nullable = false)
    var name: String = name
        protected set

    @Enumerated(EnumType.STRING)
    var type: PerformanceType = type
        protected set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "site_id", nullable = false, updatable = false)
    val site: Site = site
}