package dev.dornol.ticket.admin.api.config.handler.exception.dto

class ErrorResponse(
    val message: String,
    val errors: MutableList<ErrorResponseDetail> = mutableListOf()
) {
}