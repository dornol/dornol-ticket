package dev.dornol.ticket.file.adapter.out

import dev.dornol.ticket.file.application.port.out.FileChecksumPort
import org.springframework.stereotype.Service
import org.springframework.util.DigestUtils

@Service
class FileChecksumService : FileChecksumPort {
    override fun getChecksum(bytes: ByteArray): String {
        return DigestUtils.md5DigestAsHex(bytes)
    }
}