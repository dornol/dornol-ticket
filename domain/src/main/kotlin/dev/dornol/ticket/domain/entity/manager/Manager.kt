package dev.dornol.ticket.domain.entity.manager

import dev.dornol.ticket.domain.entity.BaseEntity
import dev.dornol.ticket.domain.entity.company.Company
import jakarta.persistence.*

@Entity
class Manager(
    username: String,
    password: String,
    name: String,
    phone: String,
    email: String,
    role: ManagerRole,
    approval: ManagerApproval = ManagerApproval(),
    company: Company? = null,
) : BaseEntity() {

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

    @Embedded
    val approval: ManagerApproval = approval

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    var managerRole: ManagerRole = role

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", updatable = false)
    val company: Company? = company
}