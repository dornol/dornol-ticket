package dev.dornol.ticket.file.adapter.`in`.web.dto

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer
import dev.dornol.ticket.file.domain.FileMetadata

data class FileUploadResponseDto(
    @JsonSerialize(using = ToStringSerializer::class)
    val id: Long,
    val location: String,
    val key: String,
) {
    constructor(metadata: FileMetadata, location: String): this(metadata.id.get(), location, metadata.uuid.toString())
}