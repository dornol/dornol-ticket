package dev.dornol.ticket.file.application.port.out

import dev.dornol.ticket.file.domain.FileLocation
import dev.dornol.ticket.file.domain.FileMetadata
import dev.dornol.ticket.file.domain.StorageType

interface NamedStorage {
    val type: StorageType
    fun upload(command: UploadFileCommand): FileLocation
    fun resolveUri(fileMetadata: FileMetadata): String
}
