package dev.dornol.ticket.file.adapter.out.jpa.mapper

import dev.dornol.ticket.DomainEntityWithIdMapper
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
interface FileMetadataMapper : DomainEntityWithIdMapper<FileMetadata, FileMetadataEntity, FileMetadataId>  {
    override fun map(id: Long?) = id?.let { FileMetadataId(id) }
}