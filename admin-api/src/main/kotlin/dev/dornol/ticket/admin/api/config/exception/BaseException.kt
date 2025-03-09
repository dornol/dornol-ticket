package dev.dornol.ticket.admin.api.config.exception

import org.springframework.http.HttpStatus

open class BaseException(
    val status: HttpStatus = HttpStatus.INTERNAL_SERVER_ERROR,
    override val message: String,
    vararg args: Any
) : RuntimeException() {
    val args = args.toList()
}