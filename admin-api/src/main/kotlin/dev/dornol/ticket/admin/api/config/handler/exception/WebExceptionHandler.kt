package dev.dornol.ticket.admin.api.config.handler.exception

import dev.dornol.ticket.admin.api.config.handler.exception.dto.ErrorResponse
import dev.dornol.ticket.admin.api.config.message.MessageResolver
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MissingRequestCookieException
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.NoHandlerFoundException

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE + 500)
class WebExceptionHandler(
    private val messageResolver: MessageResolver
) : AbstractExceptionHandler(messageResolver) {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException::class)
    fun handleNoHandlerFoundException(e: NoHandlerFoundException) =
        ErrorResponse(e.localizedMessage)

    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
    fun handleHttpRequestMethodNotSupportedException(e: HttpRequestMethodNotSupportedException) =
        ErrorResponse(e.localizedMessage)

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingRequestCookieException::class)
    fun handlerMissingRequestCookieException(e: MissingRequestCookieException) =
        ErrorResponse(e.localizedMessage)

}