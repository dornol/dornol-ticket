package dev.dornol.ticket.file.application.port.out

import dev.dornol.ticket.file.domain.FileMetadata

interface SaveFileMetadataPort {

    fun save(metadata: FileMetadata)

}