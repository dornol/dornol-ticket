package dev.dornol.ticket.validation.file

import dev.dornol.ticket.common.util.MessageUtil
import org.springframework.web.multipart.MultipartFile

class FileValidator(
    private val helper: MultipartFileValidatorHelper,
    messageUtil: MessageUtil,
) : AbstractFileValidator<MultipartFile>(messageUtil) {

    override fun isEmpty(value: MultipartFile) = false

    override fun isValidMediaType(value: MultipartFile) = helper.isValidMediaType(value, super.allowedMediaTypes)

    override fun isValidFileSize(value: MultipartFile) = helper.isValidFileSize(value, super.maxSize)

    override fun isValidFilename(value: MultipartFile) = helper.isValidFilename(value)

}