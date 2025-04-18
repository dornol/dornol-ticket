package dev.dornol.ticket.admin.api.config.handler.exception

import com.fasterxml.jackson.databind.exc.MismatchedInputException
import dev.dornol.ticket.admin.api.app.constants.ERRORS_VALIDATION
import dev.dornol.ticket.admin.api.config.handler.exception.dto.ErrorResponse
import dev.dornol.ticket.admin.api.config.handler.exception.dto.ErrorResponseDetail
import dev.dornol.ticket.admin.api.config.handler.exception.dto.ErrorScope
import dev.dornol.ticket.admin.api.config.message.MessageResolver
import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.validation.ConstraintViolationException
import jakarta.validation.ValidationException
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.FieldError
import org.springframework.validation.ObjectError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

private val log = KotlinLogging.logger {}

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE + 100)
@ResponseStatus(HttpStatus.BAD_REQUEST)
class ValidationExceptionHandler(
    private val messageResolver: MessageResolver
) : AbstractExceptionHandler(messageResolver) {

    @ExceptionHandler(ConstraintViolationException::class)
    fun handleValidationException(e: ConstraintViolationException) =
        errorResponse(ERRORS_VALIDATION).apply {
            errors.addAll(e.constraintViolations.map {
                ErrorResponseDetail(
                    it.propertyPath.last().name,
                    it.message
                )
            })
        }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(e: MethodArgumentNotValidException) =
        errorResponse(ERRORS_VALIDATION).apply {
            errors.addAll(e.bindingResult.globalErrors.map {
                errorResponseDetail(it, e.parameter.parameterName)
            })
            errors.addAll(e.bindingResult.fieldErrors.map {
                ErrorResponseDetail(
                    it.field,
                    resolveFieldErrorMessage(it),
                    ErrorScope.FIELD
                )
            })
        }

    @ExceptionHandler(ValidationException::class)
    fun handleValidationException(e: ValidationException): ErrorResponse =
        errorResponse(ERRORS_VALIDATION).also {
            log.warn(e) { "resolved validation exception : $e" }
        }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleHttpMessageNotReadableException(e: HttpMessageNotReadableException): ErrorResponse {
        return when (val cause = e.cause) {
            is MismatchedInputException -> {
                errorResponse(ERRORS_VALIDATION).apply {
                    errors.add(ErrorResponseDetail(
                        cause.path.joinToString(".") { it.fieldName },
                        messageResolver.getMessage("NotBlank"),
                        ErrorScope.FIELD
                    ))
                }
            }

            else -> errorResponse(ERRORS_VALIDATION)
        }
    }

    private fun resolveFieldErrorMessage(error: FieldError): String? {
        if (!error.isBindingFailure) {
            return error.defaultMessage
        }
        if (error.codes != null && error.codes!!.isNotEmpty()) {
            return messageResolver.getMessage(error.codes, error.arguments)
        }
        return messageResolver.getMessage(ERRORS_VALIDATION)
    }

    fun errorResponseDetail(error: ObjectError, parameterName: String?): ErrorResponseDetail {
        return if (error.codes?.isNotEmpty() == true) {
            log.info { "error: $error" }
            errorResponseDetail(
                parameterName ?: error.objectName,
                error.defaultMessage ?: messageResolver.getMessage(ERRORS_VALIDATION)
            )
        } else {
            log.info { "error: $error" }
            errorResponseDetail(
                parameterName ?: error.objectName,
                messageResolver.getMessage(ERRORS_VALIDATION)
            )
        }
    }

    fun errorResponseDetail(fieldName: String, description: String) =
        ErrorResponseDetail(fieldName, description)
}