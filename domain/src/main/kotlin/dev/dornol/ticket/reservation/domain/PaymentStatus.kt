package dev.dornol.ticket.reservation.domain

import dev.dornol.ticket.common.domain.BaseEnum

enum class PaymentStatus(override val code: Int) : BaseEnum<Int> {
    PENDING_PAYMENT(100),
    PAID(200),
    CANCELED(300),
    REFUNDED(400)
}