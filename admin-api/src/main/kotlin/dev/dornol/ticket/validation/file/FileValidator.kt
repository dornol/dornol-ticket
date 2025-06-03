package dev.dornol.ticket.validation.file

import dev.dornol.ticket.common.util.MessageUtil
import jakarta.validation.ConstraintValidatorContext
import org.springframework.web.multipart.MultipartFile

class FileValidator(
    mediaTypeDetector: MediaTypeDetector,
    messageUtil: MessageUtil,
) : AbstractFileValidator<MultipartFile>(mediaTypeDetector, messageUtil) {

    override fun isValid(
        value: MultipartFile?,
        context: ConstraintValidatorContext
    ): Boolean {
        if (value == null || value.isEmpty) {
            return true
        }

        return applyConstraintViolationIfNotValid(
            this.isMediaTypeInWhiteList(detectMediaType(value.inputStream)),
            context
        )
    }

}