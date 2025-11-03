package dev.dornol.ticket.file.domain

@ConsistentCopyVisibility
data class FileFormat internal constructor(
    val mimeType: String,
    val extension: String,
    val mediaType: String
) {
    init {
        require(mimeType.isNotBlank())
        require(extension.isNotBlank())
        require(mediaType.isNotBlank())
    }

    companion object {
        fun of(mimeType: String, extension: String, mediaType: String): FileFormat {
            return FileFormat(
                mimeType.lowercase(),
                extension.lowercase(),
                mediaType.lowercase()
            )
        }
    }
}