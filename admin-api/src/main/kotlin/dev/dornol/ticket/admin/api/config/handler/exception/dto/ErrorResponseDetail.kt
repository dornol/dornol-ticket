package dev.dornol.ticket.admin.api.config.handler.exception.dto

data class ErrorResponseDetail(
    val field: String?,
    val message: String?,
    val scope: ErrorScope = ErrorScope.GLOBAL
)