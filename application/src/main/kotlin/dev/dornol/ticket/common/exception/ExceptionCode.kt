package dev.dornol.ticket.common.exception

enum class ExceptionCode(
    val messageCode: String
) {

    /**
     * auth
     */
    UNAUTHORIZED("errors.auth.unauthorized"),
    FORBIDDEN("errors.auth.forbidden"),

    /**
     * validation
     */
    RESOURCE_NOT_FOUND("errors.validation.resource-not-found"),
    INVALID_REQUEST("errors.validation.invalid-request"),
    UNSUPPORTED_MEDIA_TYPE("errors.validation.file.not-supported"),
    FILE_PROCESSING_FAILED("errors.validation.file.processing-failed"),
    FILE_TOO_LARGE("errors.file.too-large"),

    /**
     * server
     */
    UNKNOWN("errors.server.unknown"),

    /**
     * business
     */
    JOIN_USERNAME_EXISTS("errors.business.join.username-exists"),

}