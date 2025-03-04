package dev.dornol.ticket.admin.api.config.handler.exception

import dev.dornol.ticket.admin.api.config.handler.exception.dto.ErrorResponse
import dev.dornol.ticket.admin.api.config.message.MessageResolver

abstract class AbstractExceptionHandler(
    private val messageResolver: MessageResolver
) {

    fun errorResponse(message: String, vararg args: Any) =
        ErrorResponse(messageResolver.getMessage(message, *args))

}