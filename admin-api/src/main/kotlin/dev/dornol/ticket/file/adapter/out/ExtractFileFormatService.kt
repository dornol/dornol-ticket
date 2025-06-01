package dev.dornol.ticket.file.adapter.out

import dev.dornol.ticket.file.application.port.out.ExtractFileFormatPort
import dev.dornol.ticket.file.domain.FileFormat
import org.apache.tika.Tika
import org.springframework.stereotype.Service
import java.io.InputStream

@Service
class ExtractFileFormatService(
    private val tika: Tika = Tika()
) : ExtractFileFormatPort {

    override fun extractFileType(inputStream: InputStream): FileFormat {
        val mimeType = try {
            tika.detect(inputStream)
        } catch (e: Exception) {
            "application/octet-stream"
        }

        val extension = mimeType.substringAfterLast("/", "bin").lowercase()
        val mediaType = mimeType.substringBefore("/").lowercase()

        return FileFormat.of(
            mimeType = mimeType,
            extension = extension,
            mediaType = mediaType
        )
    }
}