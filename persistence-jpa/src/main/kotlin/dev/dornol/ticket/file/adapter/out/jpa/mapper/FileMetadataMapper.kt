package dev.dornol.ticket.file.adapter.out.jpa.mapper

import dev.dornol.ticket.file.adapter.out.jpa.FileMetadataEntity
import dev.dornol.ticket.file.domain.FileMetadata
import dev.dornol.ticket.file.domain.FileMetadataId
import org.mapstruct.Mapper
import org.mapstruct.NullValueMappingStrategy


@Mapper(
    componentModel = "spring",
    nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL,
    uses = [FileFormatMapper::class, FileLocationMapper::class]
)
interface FileMetadataMapper {
    fun toEntity(fileMetadata: FileMetadata): FileMetadataEntity
    fun toDomain(fileMetadataEntity: FileMetadataEntity): FileMetadata
//    fun map(id: FileMetadataId?): Long? {
//        return id?.get()
//    }
    fun map(id: Long?): FileMetadataId? {
        return id?.let { FileMetadataId(id) }
    }
}