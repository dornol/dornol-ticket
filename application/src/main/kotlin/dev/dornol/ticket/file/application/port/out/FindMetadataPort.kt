package dev.dornol.ticket.file.application.port.out

import dev.dornol.ticket.file.domain.FileMetadata

interface FindMetadataPort {

    fun findByChecksum(checksum: String): FileMetadata?

}