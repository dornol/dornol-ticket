package dev.dornol.ticket.file.adapter.out.jpa.mapper

import dev.dornol.ticket.file.adapter.out.jpa.FileFormatEntity
import dev.dornol.ticket.file.adapter.out.jpa.FileLocationEntity
import dev.dornol.ticket.file.adapter.out.jpa.FileMetadataEntity
import dev.dornol.ticket.file.domain.FileFormat
import dev.dornol.ticket.file.domain.FileLocation
import dev.dornol.ticket.file.domain.FileMetadata
import dev.dornol.ticket.file.domain.FileMetadataId

fun FileMetadataEntity.toDomain(): FileMetadata {
    return FileMetadata(
        id = FileMetadataId(this.id),
        uuid = this.uuid,
        name = this.name,
        size = this.size,
        checksum = this.checksum,
        location = this.location.toDomain(),
        format = this.format.toDomain(),
    )
}

fun FileMetadata.toEntity(): FileMetadataEntity {
    return FileMetadataEntity(
        id = this.id.get(),
        uuid = this.uuid,
        name = this.name,
        size = this.size,
        checksum = this.checksum,
        location = this.location.toEntity(),
        format = this.format.toEntity(),
    )
}


fun FileFormat.toEntity(): FileFormatEntity {
    return FileFormatEntity(
        mimeType = this.mimeType,
        extension = this.extension,
        mediaType = this.mediaType,
    )
}

fun FileFormatEntity.toDomain(): FileFormat {
    return FileFormat.of(
        mimeType = mimeType,
        extension = this.extension,
        mediaType = mediaType,
    )
}

fun FileLocation.toEntity(): FileLocationEntity {
    return FileLocationEntity(
        bucket = this.bucket,
        objectKey = this.objectKey,
        storageType = this.storageType,
    )
}

fun FileLocationEntity.toDomain(): FileLocation {
    return FileLocation(
        bucket = this.bucket,
        objectKey = this.objectKey,
        storageType = this.storageType,
    )
}