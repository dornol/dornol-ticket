package dev.dornol.ticket.site.adapter.out.jpa.mapper

import dev.dornol.ticket.domain.entity.site.SiteEntity
import dev.dornol.ticket.file.adapter.out.jpa.FileMetadataEntity
import dev.dornol.ticket.manager.adapter.out.jpa.CompanyEntity
import dev.dornol.ticket.manager.domain.CompanyId
import dev.dornol.ticket.site.domain.Site
import dev.dornol.ticket.site.domain.SiteId

fun Site.toEntity(companyEntity: CompanyEntity, seatingMapFileEntity: FileMetadataEntity) = SiteEntity(
    id = this.id.get(),
    name = this.name,
    address = this.address.toEntity(),
    company = companyEntity,
    seatingMapFile = seatingMapFileEntity
)

fun SiteEntity.toDomain() = Site(
    id = SiteId(this.id),
    name = this.name,
    address = this.address.toDomain(),
    seatingMapFileUuid = this.seatingMapFile.uuid,
    companyId = CompanyId(this.company.id)
)