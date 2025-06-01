package dev.dornol.ticket.file.application.infra

import dev.dornol.ticket.common.domain.id.SnowFlakeIdGenerator
import dev.dornol.ticket.file.domain.FileMetadataId
import org.springframework.stereotype.Component

@Component
class FileMetadataIdGenerator {
    fun generate(): FileMetadataId = FileMetadataId(SnowFlakeIdGenerator().generate())
}