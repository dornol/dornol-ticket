package dev.dornol.ticket.admin.api.config.handler.exception

import dev.dornol.ticket.admin.api.config.handler.exception.dto.ErrorResponse
import dev.dornol.ticket.admin.api.config.message.MessageResolver
import dev.dornol.ticket.common.exception.BaseException
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE + 1000)
class BaseExceptionHandler(
    private val messageResolver: MessageResolver
) : AbstractExceptionHandler(messageResolver) {

    @ExceptionHandler(BaseException::class)
    fun handleBaseException(e: BaseException): ErrorResponse {
        val message = messageResolver.getMessage(e.message, e.args)
        return ErrorResponse(message)
    }

}