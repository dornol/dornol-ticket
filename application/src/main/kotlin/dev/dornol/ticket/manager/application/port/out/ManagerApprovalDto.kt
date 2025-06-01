package dev.dornol.ticket.manager.application.port.out

import dev.dornol.ticket.manager.domain.ManagerId
import java.time.Instant

data class ManagerApprovalDto(
    val approved: Boolean = false,
    val approvedAt: Instant? = null,
    val approvedBy: ManagerId? = null
)
