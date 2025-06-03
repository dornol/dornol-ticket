package dev.dornol.ticket.file.application.port.out

import dev.dornol.ticket.file.domain.FileMetadata
import java.util.UUID

interface FindMetadataPort {

    fun findByChecksum(checksum: String): FileMetadata?

    fun findByUuid(uuid: UUID): FileMetadata?

}