package dev.dornol.ticket.file.application.port.out

import dev.dornol.ticket.file.domain.FileFormat
import java.io.InputStream

interface ExtractFileFormatPort {

    fun extractFileType(inputStream: InputStream): FileFormat

}