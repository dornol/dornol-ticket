package dev.dornol.ticket.admin.api.app.dto.file

import dev.dornol.ticket.domain.entity.file.CommonFile

data class FileUploadResponseDto(
    val id: String,
    val location: String,
    val key: String,
) {
    constructor(file: CommonFile): this(file.id.toString(), file.location, file.uuid.toString())
}