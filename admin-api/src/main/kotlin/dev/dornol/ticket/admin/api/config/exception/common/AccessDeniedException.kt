package dev.dornol.ticket.admin.api.config.exception.common

import dev.dornol.ticket.common.exception.BaseException
import dev.dornol.ticket.common.exception.ExceptionCode
import org.springframework.http.HttpStatus

class AccessDeniedException() : BaseException(ExceptionCode.FORBIDDEN)