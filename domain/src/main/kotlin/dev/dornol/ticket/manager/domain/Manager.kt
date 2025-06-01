package dev.dornol.ticket.manager.domain

import dev.dornol.ticket.common.domain.Domain
import dev.dornol.ticket.manager.domain.value.ManagerApproval
import java.time.Instant

class Manager(
    override val id: ManagerId,
    val username: String,
    var password: String,
    var name: String,
    var phone: String,
    var email: String,
    var role: ManagerRole,
    var approval: ManagerApproval = ManagerApproval(),
    val companyId: CompanyId,
) : Domain<ManagerId>(id) {

    fun approve(by: ManagerId) {
        this.approval = ManagerApproval(
            approved = true,
            approvedBy = by,
            approvedAt = Instant.now(),
        )
    }

}