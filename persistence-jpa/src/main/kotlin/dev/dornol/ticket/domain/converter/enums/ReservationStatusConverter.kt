package dev.dornol.ticket.domain.converter.enums

import dev.dornol.ticket.reservation.domain.ReservationStatus
import jakarta.persistence.Converter

@Converter(autoApply = false)
class ReservationStatusConverter : AbstractEnumCodeConverter<ReservationStatus, Int>(ReservationStatus::class.java)