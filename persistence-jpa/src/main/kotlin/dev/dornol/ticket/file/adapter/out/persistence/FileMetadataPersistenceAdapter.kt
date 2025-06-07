package dev.dornol.ticket.file.adapter.out.persistence

import dev.dornol.ticket.file.adapter.out.jpa.mapper.toDomain
import dev.dornol.ticket.file.adapter.out.jpa.mapper.toEntity
import dev.dornol.ticket.file.application.port.out.FindMetadataPort
import dev.dornol.ticket.file.application.port.out.SaveFileMetadataPort
import dev.dornol.ticket.file.domain.FileMetadata
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class FileMetadataPersistenceAdapter(
    private val entityRepository: FileMetadataEntityRepository,
) : FindMetadataPort, SaveFileMetadataPort {

    override fun findByChecksum(checksum: String): FileMetadata? {
        return entityRepository.findByChecksum(checksum)?.toDomain()
    }

    override fun save(metadata: FileMetadata) {
        metadata.toEntity().also { entityRepository.save(it) }
    }

    override fun findByUuid(uuid: UUID): FileMetadata? {
        return entityRepository.findByUuid(uuid)?.toDomain()
    }
}