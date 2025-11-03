package dev.dornol.ticket.common.domain

import dev.dornol.ticket.common.domain.id.AbstractId
import java.io.Serializable

abstract class Domain<T : AbstractId>(
    open val id: T
) : Serializable {

    override fun equals(other: Any?): Boolean =
        this === other || (other is Domain<*> && this.id == other.id)

    override fun hashCode(): Int = id.hashCode()

    override fun toString(): String = "${this::class.simpleName}(id=$id)"

}