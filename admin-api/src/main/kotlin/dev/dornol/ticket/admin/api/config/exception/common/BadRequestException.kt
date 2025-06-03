package dev.dornol.ticket.admin.api.config.exception.common

import dev.dornol.ticket.common.exception.BaseException
import dev.dornol.ticket.common.exception.ExceptionCode

open class BadRequestException(
    code: ExceptionCode = ExceptionCode.INVALID_REQUEST,
    vararg args: Any
) : BaseException(code, *args)