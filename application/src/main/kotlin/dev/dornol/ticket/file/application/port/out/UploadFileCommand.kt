package dev.dornol.ticket.file.application.port.out

import dev.dornol.ticket.file.domain.FileFormat
import dev.dornol.ticket.file.domain.StorageType
import java.io.InputStream

data class UploadFileCommand(
    val key: String,
    val storageType: StorageType,
    val inputStream: InputStream,
    val name: String,
    val fileFormat: FileFormat
)
