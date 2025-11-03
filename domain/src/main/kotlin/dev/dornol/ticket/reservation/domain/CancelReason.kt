package dev.dornol.ticket.reservation.domain

import dev.dornol.ticket.common.domain.BaseEnum

enum class CancelReason(override val code: Int) : BaseEnum<Int> {
    USER_REQUEST(100),
    ADMIN_CANCEL(200),
    SYSTEM_TIMEOUT(300),
    DUPLICATE(400),
    PAYMENT_ERROR(500)
}