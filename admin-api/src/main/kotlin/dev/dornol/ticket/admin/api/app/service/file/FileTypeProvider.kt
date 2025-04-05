package dev.dornol.ticket.admin.api.app.service.file

import dev.dornol.ticket.domain.entity.file.FileType
import org.springframework.web.multipart.MultipartFile

interface FileTypeProvider {

    fun extractFileType(file: MultipartFile): FileType

}