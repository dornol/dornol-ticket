package dev.dornol.ticket.common.exception

open class BadRequestException(
    code: ExceptionCode = ExceptionCode.INVALID_REQUEST,
    vararg args: Any
) : BaseException(code, *args)