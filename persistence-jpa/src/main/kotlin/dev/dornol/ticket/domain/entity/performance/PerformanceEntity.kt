package dev.dornol.ticket.domain.entity.performance

import dev.dornol.ticket.domain.converter.enums.PerformanceTypeConverter
import dev.dornol.ticket.domain.entity.BaseEntity
import dev.dornol.ticket.manager.adapter.out.jpa.CompanyEntity
import dev.dornol.ticket.performance.domain.PerformanceType
import jakarta.persistence.*

@Table(name = "performance")
@Entity
class PerformanceEntity(
    name: String,
    type: PerformanceType,
    company: CompanyEntity,
) : BaseEntity() {

    @Column(length = 255, nullable = false)
    var name: String = name
        protected set

    @Convert(converter = PerformanceTypeConverter::class)
    @Column(nullable = false)
    var type: PerformanceType = type
        protected set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false, updatable = false)
    val company: CompanyEntity = company

    fun edit(name: String, type: PerformanceType) {
        this.name = name
        this.type = type
    }
}