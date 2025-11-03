package dev.dornol.ticket.file.adapter.`in`.web

import dev.dornol.ticket.file.adapter.`in`.web.dto.FileFetchAction
import dev.dornol.ticket.file.application.exception.ResourceNotFoundException
import dev.dornol.ticket.file.application.port.`in`.FindMetadataUseCase
import dev.dornol.ticket.file.application.port.`in`.LoadFileResourceUseCase
import dev.dornol.ticket.file.application.port.`in`.ResolveFileUriUseCase
import org.springframework.core.io.Resource
import org.springframework.http.CacheControl
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriUtils
import java.nio.charset.StandardCharsets
import java.util.*
import java.util.concurrent.TimeUnit

@RequestMapping("/files")
@RestController
class FileServeController(
    private val findMetadataUseCase: FindMetadataUseCase,
    private val resolveFileUriUseCase: ResolveFileUriUseCase,
    private val loadFileResourceUseCase: LoadFileResourceUseCase
) {

    @GetMapping("/{action}/{uuid}")
    fun serveFile(
        @PathVariable action: String,
        @PathVariable uuid: UUID
    ): ResponseEntity<Resource> {
        val fileMetadata = findMetadataUseCase.findByUuid(uuid)
        val resource = loadFileResourceUseCase.loadResource(fileMetadata)

        if (!resource.exists()) {
            throw ResourceNotFoundException()
        }
        val mimeType = fileMetadata.format.mimeType

        val contentDisposition = FileFetchAction.valueOf(action.uppercase()).contentDisposition
        val encodedFilename = UriUtils.encode(fileMetadata.name, StandardCharsets.UTF_8)
        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "$contentDisposition;filename=\"$encodedFilename\"")
            .header(HttpHeaders.CONTENT_TYPE, mimeType)
            .header(HttpHeaders.CACHE_CONTROL, CacheControl.maxAge(365, TimeUnit.DAYS).toString())
            .body(resource)
    }

}