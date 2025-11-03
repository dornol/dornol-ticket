package dev.dornol.ticket.file.application.port.`in`

import dev.dornol.ticket.file.domain.FileMetadata

interface ResolveFileUriUseCase {

    fun resolveFileUri(fileMetadata: FileMetadata): String

}