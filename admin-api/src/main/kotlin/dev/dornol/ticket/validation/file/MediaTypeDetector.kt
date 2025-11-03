package dev.dornol.ticket.validation.file

import java.io.InputStream

interface MediaTypeDetector {

    fun detect(originalFilename: String, inputStream: InputStream): String

}