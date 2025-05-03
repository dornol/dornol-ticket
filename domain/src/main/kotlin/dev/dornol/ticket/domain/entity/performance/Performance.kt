package dev.dornol.ticket.domain.entity.performance

import dev.dornol.ticket.domain.entity.BaseEntity
import dev.dornol.ticket.domain.entity.company.Company
import dev.dornol.ticket.domain.entity.site.Site
import jakarta.persistence.*

private const val TABLE_NAME = "performance"
private const val COMPANY_ID = "company_id"

@Table(
    name = TABLE_NAME,
)
@Entity
class Performance(
    name: String,
    type: PerformanceType,
    company: Company,
) : BaseEntity() {

    @Column(length = 255, nullable = false)
    var name: String = name
        protected set

    @Enumerated(EnumType.STRING)
    var type: PerformanceType = type
        protected set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = COMPANY_ID, nullable = false, updatable = false)
    val company: Company = company

    fun edit(name: String, type: PerformanceType) {
        this.name = name
        this.type = type
    }
}