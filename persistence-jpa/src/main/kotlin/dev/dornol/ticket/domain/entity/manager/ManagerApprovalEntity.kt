package dev.dornol.ticket.domain.entity.manager

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.time.Instant

@Embeddable
data class ManagerApprovalEntity(
    @Column(nullable = false)
    val approved: Boolean = false,
    val approvedAt: Instant? = null,
    val approvedBy: Long? = null
)
