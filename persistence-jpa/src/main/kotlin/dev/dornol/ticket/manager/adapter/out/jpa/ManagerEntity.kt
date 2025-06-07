package dev.dornol.ticket.manager.adapter.out.jpa

import dev.dornol.ticket.domain.converter.enums.ManagerRoleConverter
import dev.dornol.ticket.domain.entity.BaseEntity
import dev.dornol.ticket.manager.domain.ManagerRole
import jakarta.persistence.*

@Table(name = "manager")
@Entity
class ManagerEntity(
    id: Long,
    username: String,
    password: String,
    name: String,
    phone: String,
    email: String,
    role: ManagerRole,
    approval: ManagerApprovalEntity = ManagerApprovalEntity(),
    company: CompanyEntity,
) : BaseEntity(id) {

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
    var approval: ManagerApprovalEntity = approval

    @Convert(converter = ManagerRoleConverter::class)
    @Column(nullable = false)
    var role: ManagerRole = role

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false, updatable = false)
    val company: CompanyEntity = company

    fun update(
        password: String,
        name: String,
        phone: String,
        email: String,
        approval: ManagerApprovalEntity,
        role: ManagerRole
    ) {
        this.password = password
        this.name = name
        this.phone = phone
        this.email = email
        this.approval = approval
        this.role = role
    }
}