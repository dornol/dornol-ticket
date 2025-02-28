package dev.dornol.ticket.domain.entity.manager

import dev.dornol.ticket.domain.constraint.RegExp
import dev.dornol.ticket.domain.entity.BaseCreationEntity
import jakarta.persistence.*
import jakarta.validation.constraints.Pattern

@Entity
class Manager(
    username: String,
    password: String,
    name: String,
    phone: String,
    email: String,
    role: AccessRole,
) : BaseCreationEntity() {

    @Pattern(regexp = RegExp.USERNAME_REGEXP)
    @Column(length = 18, nullable = false, updatable = false, unique = true)
    val username: String = username

    @Column(nullable = false)
    var password: String = password
        protected set

    @Pattern(regexp = RegExp.USERNAME_REGEXP)
    @Column(length = 10, nullable = false)
    var name: String = name
        protected set

    @Column(length = 11, nullable = false)
    var phone: String = phone
        protected set

    @Column(length = 320, nullable = false)
    var email: String = email
        protected set

    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    var managerRole: AccessRole = role
}