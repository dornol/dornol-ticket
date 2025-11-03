package dev.dornol.ticket.validation.file

import org.apache.tika.Tika
import org.springframework.stereotype.Component
import java.io.InputStream

@Component
class TikaMediaTypeDetector(
    private val tika: Tika = Tika()
) : MediaTypeDetector {
    override fun detect(originalFilename: String, inputStream: InputStream): String = tika.detect(inputStream, originalFilename)
}