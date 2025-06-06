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
    INVALID_FILENAME("errors.validation.file.invalid-filename"),
    FILE_TOO_LARGE("errors.validation.file.too-large"),

    /**
     * server
     */
    UNKNOWN("errors.server.unknown"),

    /**
     * business
     */
    FILE_PROCESSING_FAILED("errors.business.file.processing-failed"),
    JOIN_USERNAME_EXISTS("errors.business.join.username-exists"),

}