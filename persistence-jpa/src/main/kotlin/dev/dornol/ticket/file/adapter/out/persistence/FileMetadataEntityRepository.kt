package dev.dornol.ticket.file.adapter.out.persistence

import dev.dornol.ticket.file.adapter.out.jpa.FileMetadataEntity
import dev.dornol.ticket.file.domain.FileMetadata
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface FileMetadataEntityRepository : JpaRepository<FileMetadataEntity, Long> {

    fun findByChecksum(checksum: String): FileMetadataEntity?

    fun findByUuid(uuid: UUID): FileMetadataEntity?

}