package dev.dornol.ticket.domain.entity.company

import dev.dornol.ticket.domain.entity.BaseCreationEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity

@Entity
class Company(
    name: String,
    businessNumber: String,
) : BaseCreationEntity() {

    @Column(length = 30, nullable = false)
    var name: String = name
        protected set

    @Column(length = 10, nullable = false)
    var businessNumber: String = businessNumber
        protected set

}