package dev.dornol.ticket.validation.file

import java.io.InputStream

interface MediaTypeDetector {

    fun detect(inputStream: InputStream): String

}