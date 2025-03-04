package dev.dornol.ticket.admin.api.config.handler.exception

import dev.dornol.ticket.admin.api.config.exception.BaseException
import dev.dornol.ticket.admin.api.config.handler.exception.dto.ErrorResponse
import dev.dornol.ticket.admin.api.config.message.MessageResolver
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE + 1000)
class BaseExceptionHandler(
    private val messageResolver: MessageResolver
) : AbstractExceptionHandler(messageResolver) {

    @ExceptionHandler(BaseException::class)
    fun handleBaseException(e: BaseException): ResponseEntity<ErrorResponse> {
        val message = messageResolver.getMessage(e.message, e.args)
        return ResponseEntity(ErrorResponse(message), e.status)
    }

}