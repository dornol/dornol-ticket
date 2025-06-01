package dev.dornol.ticket.domain.entity

import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.persistence.Column
import jakarta.persistence.MappedSuperclass
import java.time.LocalDateTime

private val log = KotlinLogging.logger {}

@MappedSuperclass
abstract class BaseEntity : BaseCreationEntity() {

    var deletedBy: Long? = null
        protected set
    var deletedDate: LocalDateTime? = null
        protected set
    @Column(nullable = false)
    var deleted = false
        protected set

    fun delete(loginUserId: Long? = null) {
        if (this.deleted) {
            log.warn { "data already deleted at $deletedDate by id: $deletedBy" }
            return
        }
        this.deleted = true
        this.deletedDate = LocalDateTime.now()
        this.deletedBy = loginUserId
    }
}