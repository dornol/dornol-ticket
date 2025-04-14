package dev.dornol.ticket.domain.entity.site

import dev.dornol.ticket.domain.entity.BaseEntity
import dev.dornol.ticket.domain.entity.company.Company
import dev.dornol.ticket.domain.entity.file.CommonFile
import dev.dornol.ticket.domain.entity.site.address.Address
import jakarta.persistence.*

private const val TABLE_NAME = "site"
private const val NAME = "name"
private const val COMPANY_ID = "company_id"
private const val SEATING_MAP_FILE_ID = "seating_map_file_id"

@Table(
    name = TABLE_NAME,
)
@Entity
class Site(
    name: String,
    address: Address,
    company: Company,
    seatingMapFile: CommonFile,
) : BaseEntity() {

    @Column(name = NAME, length = 100, nullable = false)
    var name: String = name
        protected set

    @Embedded
    var address: Address = address
        protected set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = COMPANY_ID, nullable = false, updatable = false)
    val company: Company = company

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = SEATING_MAP_FILE_ID, nullable = false)
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