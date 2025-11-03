package dev.dornol.ticket.domain.entity

import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseCreationEntity(
    id: Long
) : BaseIdEntity(id) {

    @CreatedBy
    var createdBy: Long? = null
        protected set

    @LastModifiedBy
    var lastModifiedBy: Long? = null
        protected set

    @CreatedDate
    var createdDate: LocalDateTime? = null
        protected set

    @LastModifiedDate
    var lastModifiedDate: LocalDateTime? = null
        protected set
}