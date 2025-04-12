package dev.dornol.ticket.admin.api.config.exception.common

import dev.dornol.ticket.admin.api.config.exception.BaseException
import org.springframework.http.HttpStatus

class AccessDeniedException(
    message: String = "Access denied",
) : BaseException(HttpStatus.FORBIDDEN, message)