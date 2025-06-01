package dev.dornol.ticket.file.adapter.`in`.web

import dev.dornol.ticket.file.adapter.`in`.web.dto.FileUploadResponseDto
import dev.dornol.ticket.file.application.port.`in`.ResolveFileUriUseCase
import dev.dornol.ticket.file.application.port.`in`.StoreFileCommand
import dev.dornol.ticket.file.application.port.`in`.StoreFileUseCase
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RequestMapping("/files")
@RestController
class FileUploadController(
    private val storeFileUseCase: StoreFileUseCase,
    private val resolveFileUriUseCase: ResolveFileUriUseCase
) {

    @PostMapping
    fun uploadFile(file: MultipartFile): FileUploadResponseDto {
        require(file.originalFilename != null) { "original file must not be null." }
        require(file.size > 0) { "file must not be empty." }
        val metadata = storeFileUseCase.storeFile(
            StoreFileCommand(
                file.bytes,
                file.originalFilename!!,
                file.size
            )
        )
        val uri = resolveFileUriUseCase.resolveFileUri(metadata)
        return FileUploadResponseDto(metadata, uri)
    }

}