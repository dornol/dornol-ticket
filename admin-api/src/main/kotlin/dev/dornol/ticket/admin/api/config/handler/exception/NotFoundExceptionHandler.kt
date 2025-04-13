package dev.dornol.ticket.admin.api.config.handler.exception

import dev.dornol.ticket.admin.api.app.constants.ERRORS_NOT_FOUND
import dev.dornol.ticket.admin.api.config.message.MessageResolver
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.resource.NoResourceFoundException

private val log = KotlinLogging.logger {}

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE + 2000)
@ResponseStatus(HttpStatus.NOT_FOUND)
class NotFoundExceptionHandler(
    messageResolver: MessageResolver
) : AbstractExceptionHandler(messageResolver) {

    @ExceptionHandler(NoResourceFoundException::class)
    fun handleException(e: Exception) = errorResponse(ERRORS_NOT_FOUND).also {
        log.debug(e) { "no resource found exception: $e" }
    }
}