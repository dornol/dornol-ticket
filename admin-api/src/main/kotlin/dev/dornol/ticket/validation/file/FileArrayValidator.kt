package dev.dornol.ticket.validation.file

import dev.dornol.ticket.common.util.MessageUtil
import jakarta.validation.ConstraintValidatorContext
import org.springframework.web.multipart.MultipartFile

class FileArrayValidator(
    mediaTypeDetector: MediaTypeDetector,
    messageUtil: MessageUtil,
) : AbstractFileValidator<Array<MultipartFile>>(mediaTypeDetector, messageUtil) {

    override fun isValid(
        value: Array<MultipartFile>?,
        context: ConstraintValidatorContext
    ): Boolean {
        if (value == null || value.isEmpty()) {
            return true
        }

        return applyConstraintViolationIfNotValid(
            value.all { isMediaTypeInWhiteList(detectMediaType(it.inputStream)) },
            context
        )
    }

}
