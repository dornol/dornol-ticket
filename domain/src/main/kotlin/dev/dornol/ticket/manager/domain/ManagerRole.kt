package dev.dornol.ticket.manager.domain

import dev.dornol.ticket.common.domain.BaseEnum

enum class ManagerRole(override val code: Int) : BaseEnum<Int> {
    SYSTEM_ADMIN(100),
    BUSINESS_ADMIN(200),
    BUSINESS_STAFF(300),
}