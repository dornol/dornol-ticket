package dev.dornol.ticket.file.adapter.out.persistence

import dev.dornol.ticket.file.adapter.out.jpa.FileMetadataEntity
import org.springframework.data.jpa.repository.JpaRepository

interface FileMetadataEntityRepository : JpaRepository<FileMetadataEntity, Long> {

    fun findByChecksum(checksum: String): FileMetadataEntity?

}