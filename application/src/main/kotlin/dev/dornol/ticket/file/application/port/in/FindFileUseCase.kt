package dev.dornol.ticket.file.application.port.`in`

import dev.dornol.ticket.file.domain.FileMetadata
import java.util.UUID

interface FindMetadataUseCase {

    fun findByUuid(uuid: UUID): FileMetadata

}