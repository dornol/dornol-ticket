package dev.dornol.ticket.validation.file

import dev.dornol.ticket.common.exception.ExceptionCode
import dev.dornol.ticket.common.util.MessageUtil
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import java.io.InputStream

abstract class AbstractFileValidator<T>(
    protected val mediaTypeDetector: MediaTypeDetector,
    protected val messageUtil: MessageUtil,
) : ConstraintValidator<File, T> {
    lateinit var allowedMediaTypes: Set<SafeMediaType>
    lateinit var exceptionCode: ExceptionCode

    override fun initialize(constraintAnnotation: File) {
        super.initialize(constraintAnnotation)
        this.init(constraintAnnotation)
    }

    protected fun init(constraintAnnotation: File) {
        allowedMediaTypes = if (constraintAnnotation.value.isEmpty()) SafeMediaType.entries.toSet() else constraintAnnotation.value.toSet()
        exceptionCode = constraintAnnotation.code
    }

    protected fun detectMediaType(inputStream: InputStream): String {
        return mediaTypeDetector.detect(inputStream)
    }

    protected fun isMediaTypeInWhiteList(mediaType: String): Boolean {
        return allowedMediaTypes.any { it.mimeType == mediaType }
    }

    protected fun applyConstraintViolationIfNotValid(valid: Boolean, context: ConstraintValidatorContext): Boolean {
        if (valid) {
            return true
        }

        context.disableDefaultConstraintViolation()
        context.buildConstraintViolationWithTemplate(
            messageUtil.get(exceptionCode.messageCode)
        ).addConstraintViolation()
        return false
    }

}