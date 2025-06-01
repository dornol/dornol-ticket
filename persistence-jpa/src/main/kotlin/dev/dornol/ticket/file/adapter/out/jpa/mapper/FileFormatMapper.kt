package dev.dornol.ticket.file.adapter.out.jpa.mapper

import dev.dornol.ticket.DomainEntityMapper
import dev.dornol.ticket.file.adapter.out.jpa.FileFormatEntity
import dev.dornol.ticket.file.domain.FileFormat
import org.mapstruct.Mapper
import org.mapstruct.NullValueMappingStrategy

@Mapper(
    componentModel = "spring",
    nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL
)
interface FileFormatMapper : DomainEntityMapper<FileFormat, FileFormatEntity> {
}