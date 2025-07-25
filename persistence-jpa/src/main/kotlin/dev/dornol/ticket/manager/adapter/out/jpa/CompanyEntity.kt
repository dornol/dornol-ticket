package dev.dornol.ticket.manager.adapter.out.jpa

import dev.dornol.ticket.domain.entity.BaseCreationEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Table(name = "company")
@Entity
class CompanyEntity(
    id: Long,
    name: String,
    businessNumber: String,
) : BaseCreationEntity(id) {

    @Column(length = 30, nullable = false)
    var name: String = name
        protected set

    @Column(length = 10, nullable = false)
    var businessNumber: String = businessNumber
        protected set

}