package dev.dornol.ticket.common.exception

open class BaseException(
    val code: ExceptionCode,
    vararg args: Any
) : RuntimeException() {
    override val message = code.messageCode
    val args = args.toList()
}