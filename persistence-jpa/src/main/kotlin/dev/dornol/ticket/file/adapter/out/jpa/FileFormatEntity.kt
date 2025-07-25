package dev.dornol.ticket.file.adapter.out.jpa

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
class FileFormatEntity(
    mimeType: String,
    extension: String,
    mediaType: String
) {

    @Column(length = 50, nullable = false, updatable = false)
    val mimeType: String = mimeType.lowercase()

    @Column(length = 50, nullable = false, updatable = false)
    val extension: String = extension.lowercase()

    @Column(length = 50, nullable = false, updatable = false)
    val mediaType: String = mediaType.lowercase()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is FileFormatEntity) return false
        return mimeType == other.mimeType &&
                extension == other.extension &&
                mediaType == other.mediaType
    }

    override fun hashCode(): Int {
        return listOf(mimeType, extension, mediaType).hashCode()
    }

}