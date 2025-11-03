package dev.dornol.ticket.file.adapter.out.jpa

import dev.dornol.ticket.domain.entity.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.Table
import java.util.*

@Table(name = "file_metadata")
@Entity
class FileMetadataEntity(
    id: Long,
    uuid: UUID = UUID.randomUUID(),
    name: String,
    size: Long,
    checksum: String,
    location: FileLocationEntity,
    format: FileFormatEntity,
) : BaseEntity(id) {

    @Column(nullable = false, updatable = false, unique = true)
    val uuid: UUID = uuid

    @Column(length = 255, nullable = false, updatable = false)
    val name: String = name

    @Column(nullable = false, updatable = false)
    val size: Long = size

    @Column(nullable = false, updatable = false, unique = true)
    val checksum: String = checksum

    @Embedded
    val location: FileLocationEntity = location

    @Embedded
    val format: FileFormatEntity = format

}
