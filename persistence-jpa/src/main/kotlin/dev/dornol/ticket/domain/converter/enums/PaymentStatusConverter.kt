package dev.dornol.ticket.domain.converter.enums

import dev.dornol.ticket.reservation.domain.PaymentStatus
import jakarta.persistence.Converter

@Converter(autoApply = false)
class PaymentStatusConverter : AbstractEnumCodeConverter<PaymentStatus, Int>(PaymentStatus::class.java)