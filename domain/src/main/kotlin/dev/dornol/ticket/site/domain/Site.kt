package dev.dornol.ticket.site.domain

import dev.dornol.ticket.common.domain.Domain
import dev.dornol.ticket.manager.domain.CompanyId
import dev.dornol.ticket.site.domain.value.Address
import java.util.*

class Site(
    override val id: SiteId,
    var name: String,
    var address: Address,
    var seatingMapFileUuid: UUID,
    val companyId: CompanyId
) : Domain<SiteId>(id) {

    fun edit(
        name: String, address: Address, seatingMapFileUuid: UUID?
    ) {
        this.name = name
        this.address = address
        if (seatingMapFileUuid != null) {
            this.seatingMapFileUuid = seatingMapFileUuid
        }
    }

}