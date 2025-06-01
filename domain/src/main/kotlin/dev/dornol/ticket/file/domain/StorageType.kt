package dev.dornol.ticket.file.domain

import dev.dornol.ticket.common.domain.BaseEnum

enum class StorageType(override val code: Int) : BaseEnum<Int> {
    LOCAL(100),
    MINIO(200),
}