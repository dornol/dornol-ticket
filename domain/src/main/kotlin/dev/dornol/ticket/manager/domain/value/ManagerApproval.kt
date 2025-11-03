package dev.dornol.ticket.manager.domain.value

import dev.dornol.ticket.manager.domain.ManagerId
import java.time.Instant

data class ManagerApproval(
    val approved: Boolean = false,
    val approvedAt: Instant? = null,
    val approvedBy: ManagerId? = null
)