package dev.dornol.ticket.domain.entity.reservation

enum class CancelReason {
    USER_REQUEST,
    ADMIN_CANCEL,
    SYSTEM_TIMEOUT,
    DUPLICATE,
    PAYMENT_ERROR
}