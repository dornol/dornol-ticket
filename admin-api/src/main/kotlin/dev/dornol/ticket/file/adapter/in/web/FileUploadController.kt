package dev.dornol.ticket.file.adapter.`in`.web

import dev.dornol.ticket.file.adapter.`in`.web.dto.Bucket
import dev.dornol.ticket.file.adapter.`in`.web.dto.FileUploadResponseDto
import dev.dornol.ticket.file.application.port.`in`.StoreFileCommand
import dev.dornol.ticket.file.application.port.`in`.StoreFileUseCase
import dev.dornol.ticket.validation.file.File
import dev.dornol.ticket.validation.file.SafeMediaType.*
import jakarta.validation.constraints.NotNull
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@Validated
@RequestMapping("/files")
@RestController
class FileUploadController(
    private val storeFileUseCase: StoreFileUseCase
) {

    @PostMapping("/{bucket}")
    fun uploadFile(
        @PathVariable bucket: String,
        @NotNull @File(value = [IMAGE_JPEG, IMAGE_PNG])
        file: MultipartFile
    ): FileUploadResponseDto {
        val bucketEnum = Bucket.valueOf(bucket.uppercase())

        val metadata = storeFileUseCase.storeFile(
            StoreFileCommand(
                file.bytes,
                file.originalFilename!!,
                file.size,
                bucketEnum.bucketName
            )
        )
        return FileUploadResponseDto(metadata)
    }

}