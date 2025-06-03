package dev.dornol.ticket.admin.api.config.handler.exception

import dev.dornol.ticket.admin.api.config.exception.common.BadRequestException
import dev.dornol.ticket.admin.api.config.handler.exception.dto.ErrorResponse
import dev.dornol.ticket.admin.api.config.message.MessageResolver
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@ResponseStatus(HttpStatus.BAD_REQUEST)
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE + 1000)
class BadRequestExceptionHandler(
    private val messageResolver: MessageResolver
) : AbstractExceptionHandler(messageResolver) {

    @ExceptionHandler(BadRequestException::class)
    fun handleBaseException(e: BadRequestException): ErrorResponse {
        val message = messageResolver.getMessage(e.message, e.args)
        return ErrorResponse(message)
    }

}