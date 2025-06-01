package dev.dornol.ticket.domain.entity.site

import dev.dornol.ticket.domain.entity.BaseEntity
import dev.dornol.ticket.domain.entity.manager.CompanyEntity
import dev.dornol.ticket.file.adapter.out.jpa.FileMetadataEntity
import dev.dornol.ticket.domain.entity.site.address.AddressEntity
import jakarta.persistence.*

@Table(name = "site")
@Entity
class SiteEntity(
    name: String,
    address: AddressEntity,
    company: CompanyEntity,
    seatingMapFile: FileMetadataEntity,
) : BaseEntity() {

    @Column(length = 100, nullable = false)
    var name: String = name
        protected set

    @Embedded
    var address: AddressEntity = address
        protected set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, updatable = false)
    val company: CompanyEntity = company

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seating_map_file_id", nullable = false)
    var seatingMapFile: FileMetadataEntity = seatingMapFile
        protected set

    fun edit(name: String, address: AddressEntity, seatingMapFile: FileMetadataEntity?) {
        this.name = name
        this.address = address
        if (seatingMapFile != null) {
            this.seatingMapFile = seatingMapFile
        }
    }

}