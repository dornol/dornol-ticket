package dev.dornol.ticket.file.application.port.out

import dev.dornol.ticket.file.domain.StorageType
import org.springframework.stereotype.Component

@Component
class NamedStorageResolver(
    providers: List<NamedStorage>
) {
    private val map: Map<StorageType, NamedStorage> = providers.associateBy { it.type }

    fun resolve(type: StorageType): NamedStorage = map[type] ?: throw IllegalArgumentException("No UploadFilePort for type: $type")

}