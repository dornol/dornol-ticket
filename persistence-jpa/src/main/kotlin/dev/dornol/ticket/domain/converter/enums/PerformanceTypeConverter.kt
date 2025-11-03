package dev.dornol.ticket.domain.converter.enums

import dev.dornol.ticket.performance.domain.PerformanceType
import jakarta.persistence.Converter

@Converter(autoApply = false)
class PerformanceTypeConverter : AbstractEnumCodeConverter<PerformanceType, Int>(PerformanceType::class.java)