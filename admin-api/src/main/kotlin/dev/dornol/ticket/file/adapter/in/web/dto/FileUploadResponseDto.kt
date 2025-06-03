package dev.dornol.ticket.file.adapter.`in`.web.dto

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer
import dev.dornol.ticket.file.domain.FileMetadata

data class FileUploadResponseDto(
    @JsonSerialize(using = ToStringSerializer::class)
    val id: Long,
    val uuid: String,
) {
    constructor(metadata: FileMetadata): this(metadata.id.get(), metadata.uuid.toString())
}