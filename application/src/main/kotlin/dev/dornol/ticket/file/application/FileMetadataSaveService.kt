package dev.dornol.ticket.file.application

import dev.dornol.ticket.file.application.port.out.SaveFileMetadataPort
import dev.dornol.ticket.file.domain.FileMetadata
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
open class FileMetadataSaveService(
    private val saveFileMetadataPort: SaveFileMetadataPort
) {

    @Transactional
    open fun save(fileMetadata: FileMetadata) {
        saveFileMetadataPort.save(fileMetadata)
    }

}
