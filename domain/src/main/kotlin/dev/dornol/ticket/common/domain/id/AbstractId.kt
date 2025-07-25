package dev.dornol.ticket.common.domain.id

abstract class AbstractId(protected val value: Long) {
    companion object {
        inline fun <reified T : AbstractId> from(id: Long): T {
            val constructor = T::class.constructors.first()
            return constructor.call(id)
        }
    }

    init {
        require(value > 0) { "${this::class.simpleName} must be positive. Was: $value" }
    }

    fun get(): Long = value

    override fun toString(): String = value.toString()

    override fun equals(other: Any?): Boolean =
        this === other || (other is AbstractId && this.value == other.value)

    override fun hashCode(): Int = value.hashCode()
}
