package dev.dornol.ticket.file.application.port.`in`

import dev.dornol.ticket.file.domain.FileMetadata

interface StoreFileUseCase {

    fun storeFile(command: StoreFileCommand): FileMetadata

}