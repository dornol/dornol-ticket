package dev.dornol.ticket.domain.entity.file

import dev.dornol.ticket.domain.entity.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.Table
import java.util.*

private const val TABLE_NAME = "common_file"
private const val FILE_HASH = "file_hash"

@Table(
    name = TABLE_NAME,
)
@Entity
class CommonFile(
    uuid: UUID = UUID.randomUUID(),
    name: String,
    location: String,
    size: Long,
    hash: String,
    fileType: FileType,
) : BaseEntity() {

    @Column(nullable = false, updatable = false, unique = true)
    val uuid: UUID = uuid

    @Column(length = 255, nullable = false, updatable = false)
    val name: String = name

    @Column(length = 512, nullable = false, updatable = false)
    val location: String = location

    @Column(nullable = false, updatable = false)
    val size: Long = size

    @Embedded
    val fileType: FileType = fileType

    @Column(name = FILE_HASH, nullable = false, updatable = false, unique = true)
    val hash: String = hash

}