package dev.dornol.ticket.domain.entity.manager

import dev.dornol.ticket.domain.entity.BaseCreationEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity

@Entity
class ManagerGroup(
    name: String
) : BaseCreationEntity() {

    @Column(length = 10, nullable = false)
    var name: String = name
        protected set
}