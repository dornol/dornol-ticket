package dev.dornol.ticket.admin.api.util

import dev.dornol.ticket.admin.api.config.exception.common.AccessDeniedException

fun assertAccess(expression: Boolean) {
    if (!expression) {
        throw AccessDeniedException()
    }
}