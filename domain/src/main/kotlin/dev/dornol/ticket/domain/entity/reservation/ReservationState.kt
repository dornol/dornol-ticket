package dev.dornol.ticket.domain.entity.reservation

enum class ReservationState {
    PENDING_PAYMENT,
    PAID,
    CANCELED
}