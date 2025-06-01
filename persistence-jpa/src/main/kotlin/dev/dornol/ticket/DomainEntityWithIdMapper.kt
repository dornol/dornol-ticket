package dev.dornol.ticket

import dev.dornol.ticket.common.domain.id.AbstractId

interface DomainEntityWithIdMapper<D, E, ID : AbstractId> : DomainEntityMapper<D, E> {
    fun map(id: Long?): ID?
}