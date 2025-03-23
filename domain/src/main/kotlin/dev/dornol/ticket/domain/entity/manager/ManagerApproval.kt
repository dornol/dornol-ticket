package dev.dornol.ticket.domain.entity.manager

import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.time.LocalDateTime

private val log = KotlinLogging.logger {}

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
        if (approved) {
            log.warn { "already approved by ${this.approvedBy} at ${this.approvedDate}" }
            return
        }
        this.approved = true
        this.approvedBy = approvedBy
        this.approvedDate = approvedDate
    }

}