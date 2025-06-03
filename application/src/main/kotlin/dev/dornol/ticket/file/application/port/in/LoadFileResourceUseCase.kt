package dev.dornol.ticket.file.application.port.`in`

import dev.dornol.ticket.file.domain.FileMetadata
import org.springframework.core.io.Resource

interface LoadFileResourceUseCase {

    fun loadResource(fileMetadata: FileMetadata): Resource

}