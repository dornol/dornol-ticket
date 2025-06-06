package dev.dornol.ticket.validation.file

import dev.dornol.ticket.common.util.MessageUtil
import org.springframework.web.multipart.MultipartFile

class FileCollectionValidator(
    private val helper: MultipartFileValidatorHelper,
    messageUtil: MessageUtil,
) : AbstractFileValidator<Collection<MultipartFile>>(messageUtil) {

    override fun isEmpty(value: Collection<MultipartFile>) = value.isEmpty()

    override fun isValidMediaType(value: Collection<MultipartFile>) = value.all { helper.isValidMediaType(it, allowedMediaTypes) }

    override fun isValidFileSize(value: Collection<MultipartFile>) = value.all { helper.isValidFileSize(it, maxSize) }

    override fun isValidFilename(value: Collection<MultipartFile>) = value.all { helper.isValidFilename(it) }

}
