package dev.dornol.ticket.admin.api.app.repository.file

import dev.dornol.ticket.file.adapter.out.jpa.FileMetadataEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CommonFileRepository : JpaRepository<FileMetadataEntity, Long> {

}