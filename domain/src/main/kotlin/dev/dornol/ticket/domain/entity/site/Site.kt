package dev.dornol.ticket.domain.entity.site

import dev.dornol.ticket.domain.entity.BaseEntity
import dev.dornol.ticket.domain.entity.company.Company
import dev.dornol.ticket.domain.entity.site.address.Address
import jakarta.persistence.Column
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity
class Site(
    name: String,
    address: Address,
    company: Company
) : BaseEntity() {

    @Column(name = "name", length = 100, nullable = false)
    var name: String = name

    @Embedded
    var address: Address = address

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false, updatable = false)
    val company: Company = company

}