package dev.dornol.ticket.site.domain

import dev.dornol.ticket.common.domain.Domain
import dev.dornol.ticket.file.domain.FileMetadataId
import dev.dornol.ticket.manager.domain.CompanyId
import dev.dornol.ticket.site.domain.value.Address

class Site(
    override val id: SiteId,
    var name: String,
    var address: Address,
    var seatingMapFileId: FileMetadataId,
    val companyId: CompanyId
) : Domain<SiteId>(id) {
}