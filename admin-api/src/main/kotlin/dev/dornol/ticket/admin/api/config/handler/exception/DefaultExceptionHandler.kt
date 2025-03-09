package dev.dornol.ticket.admin.api.config.handler.exception

import dev.dornol.ticket.admin.api.config.message.MessageResolver
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

private val log = KotlinLogging.logger {}

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE + 3000)
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
class DefaultExceptionHandler(
    private val messageResolver: MessageResolver
) : AbstractExceptionHandler(messageResolver) {

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception) = errorResponse("errors.default").also {
        log.error(e) { "unhandled exception: $e" }
    }

}