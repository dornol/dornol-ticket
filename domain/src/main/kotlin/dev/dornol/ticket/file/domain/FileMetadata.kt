package dev.dornol.ticket.file.domain

import dev.dornol.ticket.common.domain.Domain
import java.util.*

class FileMetadata(
    override val id: FileMetadataId,
    val uuid: UUID,
    val name: String,
    val size: Long,
    val checksum: String,
    val format: FileFormat,
    val location: FileLocation,
) : Domain<FileMetadataId>(id) {

    init {
        require(name.isNotBlank()) { "파일 이름은 비어 있을 수 없습니다." }
        require(location.bucket.isNotBlank()) { "버킷은 비어 있을 수 없습니다." }
        require(location.objectKey.isNotBlank()) { "오브젝트키는 비어 있을 수 없습니다." }
        require(size >= 0) { "파일 크기는 0 이상이어야 합니다." }
        require(checksum.isNotBlank()) { "파일 체크섬은 필수입니다." }
    }

}