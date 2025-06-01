package dev.dornol.ticket.domain.converter.enums

import dev.dornol.ticket.file.domain.StorageType
import jakarta.persistence.Converter

@Converter(autoApply = false)
class StorageTypeConverter : AbstractEnumCodeConverter<StorageType, Int>(StorageType::class.java)