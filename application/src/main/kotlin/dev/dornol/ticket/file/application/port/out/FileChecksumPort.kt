package dev.dornol.ticket.file.application.port.out

interface FileChecksumPort {

    fun getChecksum(bytes: ByteArray): String

}