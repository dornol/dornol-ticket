package dev.dornol.ticket.manager.adapter.out.jpa

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
