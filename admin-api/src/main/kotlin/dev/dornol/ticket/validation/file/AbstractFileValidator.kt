package dev.dornol.ticket.validation.file

import dev.dornol.ticket.common.util.MessageUtil
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import kotlin.properties.Delegates

abstract class AbstractFileValidator<T>(
    protected val messageUtil: MessageUtil
) : ConstraintValidator<File, T> {
    lateinit var allowedMediaTypes: Set<SafeMediaType>
    var maxSize by Delegates.notNull<Long>()

    override fun initialize(constraintAnnotation: File) {
        super.initialize(constraintAnnotation)
        this.init(constraintAnnotation)
    }

    override fun isValid(value: T?, context: ConstraintValidatorContext): Boolean {
        if (value == null || isEmpty(value)) {
            return true
        }

        return when {
            !isValidFilename(value) -> {
                applyConstraintViolation(context, "errors.validation.file.invalid-filename")
                false
            }
            !isValidFileSize(value) -> {
                applyConstraintViolation(context, "errors.validation.file.too-large")
                false
            }
            !isValidMediaType(value) -> {
                applyConstraintViolation(context, "errors.validation.file.not-supported")
                false
            }
            !isValidExtension(value) -> {
                applyConstraintViolation(context, "errors.validation.file.invalid-extension")
                false
            }
            else -> true
        }
    }

    private fun init(constraintAnnotation: File) {
        allowedMediaTypes = if (constraintAnnotation.value.isEmpty()) SafeMediaType.entries.toSet() else constraintAnnotation.value.toSet()
        maxSize = constraintAnnotation.size
    }

    private fun applyConstraintViolation(context: ConstraintValidatorContext, messageKey: String) {
        context.disableDefaultConstraintViolation()
        context.buildConstraintViolationWithTemplate(
            messageUtil.getMessage(messageKey)
        ).addConstraintViolation()
    }

    protected abstract fun isEmpty(value: T): Boolean

    protected abstract fun isValidMediaType(value: T): Boolean

    protected abstract fun isValidFileSize(value: T): Boolean

    protected abstract fun isValidFilename(value: T): Boolean

    protected abstract fun isValidExtension(value: T): Boolean

}