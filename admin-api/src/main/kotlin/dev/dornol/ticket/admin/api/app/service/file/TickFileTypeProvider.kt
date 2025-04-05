package dev.dornol.ticket.admin.api.app.service.file

import dev.dornol.ticket.domain.entity.file.FileType
import org.apache.tika.Tika
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class TickFileTypeProvider(
    private val tika: Tika = Tika()
) : FileTypeProvider {

    override fun extractFileType(file: MultipartFile): FileType {
        val mimeType = try {
            tika.detect(file.inputStream)
        } catch (e: Exception) {
            "application/octet-stream"
        }

        val extension = mimeType.substringAfterLast("/", "bin").lowercase()
        val mediaType = mimeType.substringBefore("/").lowercase()

        return FileType(
            mimeType = mimeType,
            extension = extension,
            mediaType = mediaType
        )
    }
}