package dev.dornol.ticket.performance.domain

import dev.dornol.ticket.common.domain.Domain
import dev.dornol.ticket.manager.domain.CompanyId

class Performance(
    override var id: PerformanceId,
    var name: String,
    var type: PerformanceType,
    val companyId: CompanyId,
) : Domain<PerformanceId>(id) {

}