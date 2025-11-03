package dev.dornol.ticket.file.application.port.out

import dev.dornol.ticket.file.domain.FileFormat
import dev.dornol.ticket.file.domain.StorageType
import java.io.InputStream

data class UploadFileCommand(
    val key: String,
    val storageType: StorageType,
    val bucket: String,
    val name: String,
    val inputStream: InputStream,
    val fileFormat: FileFormat
)
