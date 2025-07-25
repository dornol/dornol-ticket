package dev.dornol.ticket.file.application.port.`in`


data class StoreFileCommand(
    val bytes: ByteArray,
    val name: String,
    val size: Long,
    val bucket: String,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as StoreFileCommand

        if (size != other.size) return false
        if (!bytes.contentEquals(other.bytes)) return false
        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        var result = size.hashCode()
        result = 31 * result + bytes.contentHashCode()
        result = 31 * result + name.hashCode()
        return result
    }
}
