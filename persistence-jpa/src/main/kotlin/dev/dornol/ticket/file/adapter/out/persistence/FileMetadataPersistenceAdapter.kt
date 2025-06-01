package dev.dornol.ticket.file.adapter.out.persistence

import dev.dornol.ticket.file.adapter.out.jpa.mapper.FileMetadataMapper
import dev.dornol.ticket.file.application.port.out.FindMetadataPort
import dev.dornol.ticket.file.application.port.out.SaveFileMetadataPort
import dev.dornol.ticket.file.domain.FileMetadata
import org.springframework.stereotype.Repository

@Repository
class FileMetadataPersistenceAdapter(
    private val entityRepository: FileMetadataEntityRepository,
    private val fileMetadataMapper: FileMetadataMapper
) : FindMetadataPort, SaveFileMetadataPort {

    override fun findByChecksum(checksum: String): FileMetadata? {
        return entityRepository.findByChecksum(checksum)?.let { fileMetadataMapper.toDomain(it) }
    }

    override fun save(metadata: FileMetadata) {
        fileMetadataMapper.toEntity(metadata).let { entityRepository.save(it) }
    }
}