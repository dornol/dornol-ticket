package dev.dornol.ticket.admin.api.app.controller.file

import dev.dornol.ticket.admin.api.app.dto.file.FileUploadResponseDto
import dev.dornol.ticket.admin.api.app.service.file.FileFacadeService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RequestMapping("/files")
@RestController
class FileUploadController(
    private val fileFacadeService: FileFacadeService
) {

    @PostMapping
    fun uploadFile(file: MultipartFile): FileUploadResponseDto {
        return FileUploadResponseDto(fileFacadeService.saveFile(file))
    }

}