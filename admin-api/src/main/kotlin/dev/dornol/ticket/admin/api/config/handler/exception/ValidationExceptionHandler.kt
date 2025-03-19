package dev.dornol.ticket.admin.api.config.handler.exception

import com.fasterxml.jackson.databind.exc.MismatchedInputException
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
        errorResponse("errors.validation").apply {
            errors.addAll(e.constraintViolations.map {
                ErrorResponseDetail(
                    it.propertyPath.last().name,
                    it.message
                )
            })
        }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(e: MethodArgumentNotValidException) =
        errorResponse("errors.validation").apply {
            errors.addAll(e.bindingResult.globalErrors.map {
                errorResponseDetail(it)
            })
            errors.addAll(e.bindingResult.fieldErrors.map {
                ErrorResponseDetail(
                    it.field,
                    if (it.isBindingFailure) messageResolver.getMessage("errors.validation")
                    else it.defaultMessage,
                    ErrorScope.FIELD
                )
            })
        }

    @ExceptionHandler(ValidationException::class)
    fun handleValidationException(e: ValidationException): ErrorResponse =
        errorResponse("errors.validation").also {
            log.warn(e) { "resolved validation exception : $e" }
        }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleHttpMessageNotReadableException(e: HttpMessageNotReadableException): ErrorResponse {
        return when (val cause = e.cause) {
            is MismatchedInputException -> {
                errorResponse("errors.validation").apply {
                    errors.addAll(cause.path.map { errorResponseDetail(it.fieldName, it.description) })
                }
            }

            else -> errorResponse("errors.validation")
        }
    }

    fun errorResponseDetail(error: ObjectError): ErrorResponseDetail {
        return if (error.codes?.isNotEmpty() == true) {
            errorResponseDetail(
                error.objectName,
                error.defaultMessage ?: messageResolver.getMessage("errors.validation")
            )
        } else {
            errorResponseDetail(
                error.objectName,
                messageResolver.getMessage("errors.validation")
            )
        }
    }

    fun errorResponseDetail(fieldName: String, description: String) =
        ErrorResponseDetail(fieldName, description)
}