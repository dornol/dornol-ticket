package dev.dornol.ticket.validation.file

import dev.dornol.ticket.common.util.MessageUtil
import org.springframework.web.multipart.MultipartFile

class FileArrayValidator(
    private val helper: MultipartFileValidatorHelper,
    messageUtil: MessageUtil
) : AbstractFileValidator<Array<MultipartFile>>(messageUtil) {

    override fun isEmpty(value: Array<MultipartFile>) = value.isEmpty()

    override fun isValidMediaType(value: Array<MultipartFile>) = value.all { helper.isValidMediaType(it, super.allowedMediaTypes) }

    override fun isValidFileSize(value: Array<MultipartFile>) = value.all { helper.isValidFileSize(it, super.maxSize) }

    override fun isValidFilename(value: Array<MultipartFile>) = value.all { helper.isValidFilename(it) }

    override fun isValidExtension(value: Array<MultipartFile>) = value.all { helper.isValidExtension(it) }

}
