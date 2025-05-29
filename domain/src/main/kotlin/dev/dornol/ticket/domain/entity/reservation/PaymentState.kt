package dev.dornol.ticket.domain.entity.reservation

enum class PaymentState {
    PENDING_PAYMENT,
    PAID,
    CANCELED,
    REFUNDED
}