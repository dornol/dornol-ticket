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

        if (!isValidMediaType(value)) {
            applyConstraintViolation(context, "errors.validation.file.not-supported")
            return false
        } else if (!isValidFileSize(value)) {
            applyConstraintViolation(context, "errors.validation.file.too-large")
            return false
        } else if (!isValidFilename(value)) {
            applyConstraintViolation(context, "errors.validation.file.invalid-filename")
            return false
        }

        return true
    }

    private fun init(constraintAnnotation: File) {
        allowedMediaTypes = if (constraintAnnotation.value.isEmpty()) SafeMediaType.entries.toSet() else constraintAnnotation.value.toSet()
        maxSize = constraintAnnotation.size
    }

    private fun applyConstraintViolation(context: ConstraintValidatorContext, messageKey: String) {
        context.disableDefaultConstraintViolation()
        context.buildConstraintViolationWithTemplate(
            messageUtil.get(messageKey)
        ).addConstraintViolation()
    }

    protected abstract fun isEmpty(value: T): Boolean

    protected abstract fun isValidMediaType(value: T): Boolean

    protected abstract fun isValidFileSize(value: T): Boolean

    protected abstract fun isValidFilename(value: T): Boolean

}