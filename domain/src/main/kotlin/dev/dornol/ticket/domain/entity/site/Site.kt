package dev.dornol.ticket.domain.entity.site

import dev.dornol.ticket.domain.entity.BaseEntity
import dev.dornol.ticket.domain.entity.company.Company
import dev.dornol.ticket.domain.entity.file.CommonFile
import dev.dornol.ticket.domain.entity.site.address.Address
import jakarta.persistence.Column
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToOne

@Entity
class Site(
    name: String,
    address: Address,
    company: Company,
    seatingMapFile: CommonFile,
) : BaseEntity() {

    @Column(name = "name", length = 100, nullable = false)
    var name: String = name
        protected set

    @Embedded
    var address: Address = address
        protected set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false, updatable = false)
    val company: Company = company

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seating_map_file_id", nullable = false)
    var seatingMapFile: CommonFile = seatingMapFile
        protected set

    fun edit(name: String, address: Address, seatingMapFile: CommonFile?) {
        this.name = name
        this.address = address
        if (seatingMapFile != null) {
            this.seatingMapFile = seatingMapFile
        }
    }

}