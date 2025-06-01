package dev.dornol.ticket.domain.converter.enums

import dev.dornol.ticket.reservation.domain.CancelReason
import jakarta.persistence.Converter

@Converter(autoApply = false)
class CancelReasonConverter : AbstractEnumCodeConverter<CancelReason, Int>(CancelReason::class.java)