package dev.dornol.ticket.file.adapter.out.jpa

import dev.dornol.ticket.domain.converter.enums.StorageTypeConverter
import dev.dornol.ticket.file.domain.StorageType
import jakarta.persistence.Column
import jakarta.persistence.Convert
import jakarta.persistence.Embeddable

@Embeddable
data class FileLocationEntity(
    @Column(length = 255, nullable = false, updatable = false)
    val bucket: String,
    @Column(length = 512, nullable = false, updatable = false)
    val objectKey: String,
    @Convert(converter = StorageTypeConverter::class)
    val storageType: StorageType
)