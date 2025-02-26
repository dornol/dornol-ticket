package dev.dornol.ticket.domain.entity.manager

import dev.dornol.ticket.domain.entity.BaseCreationEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity
class Manager(
    username: String,
    password: String,
    name: String,
    phone: String,
    email: String,
    group: ManagerGroup,
) : BaseCreationEntity() {

    @Column(length = 18, nullable = false, updatable = false, unique = true)
    val username: String = username

    @Column(nullable = false)
    var password: String = password
        protected set

    @Column(length = 10, nullable = false)
    var name: String = name
        protected set

    @Column(length = 11, nullable = false)
    var phone: String = phone
        protected set

    @Column(length = 320, nullable = false)
    var email: String = email
        protected set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", nullable = false)
    var group: ManagerGroup = group
        protected set
}