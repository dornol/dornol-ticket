package dev.dornol.ticket.domain.entity.manager

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.time.LocalDateTime

@Embeddable
class ManagerApproval(
    approved: Boolean = false
) {

    @Column(nullable = false)
    var approved: Boolean = approved
        protected set

    var approvedDate: LocalDateTime? = null
        protected set

    var approvedBy: Long? = null
        protected set

    fun approve(approvedBy: Long, approvedDate: LocalDateTime) {
        this.approved = true
        this.approvedBy = approvedBy
        this.approvedDate = approvedDate
    }

}