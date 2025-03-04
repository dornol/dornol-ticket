package dev.dornol.ticket.admin.api.config.exception

import org.springframework.http.HttpStatus

open class BaseException(
    val status: HttpStatus = HttpStatus.BAD_REQUEST,
    override val message: String,
    vararg args: Any
) : RuntimeException() {
    val args = args.toList()
}