package dev.dornol.ticket.domain.entity.file

import jakarta.persistence.Embeddable

@Embeddable
class FileType(
    mimeType: String,
    extension: String,
    mediaType: String
) {

    val mimeType: String = mimeType.lowercase()
    val extension: String = extension.lowercase()
    val mediaType: String = mediaType.lowercase()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is FileType) return false
        return mimeType == other.mimeType &&
                extension == other.extension &&
                mediaType == other.mediaType
    }

    override fun hashCode(): Int {
        return listOf(mimeType, extension, mediaType).hashCode()
    }

}