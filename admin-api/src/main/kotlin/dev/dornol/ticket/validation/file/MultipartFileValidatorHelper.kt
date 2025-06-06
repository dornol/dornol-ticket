package dev.dornol.ticket.validation.file

import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile

@Component
class MultipartFileValidatorHelper(
    private val mediaTypeDetector: MediaTypeDetector
) {

    fun isValidMediaType(value: MultipartFile, allowed: Set<SafeMediaType>): Boolean {
        val detected = mediaTypeDetector.detect(value.inputStream)
        return allowed.any { it.mimeType == detected }
    }

    fun isValidFileSize(value: MultipartFile, maxSize: Long): Boolean {
        return value.size > 0 && (if (maxSize > 0) value.size <= maxSize else true)
    }

    fun isValidFilename(value: MultipartFile): Boolean {
        val name = value.originalFilename ?: return false

        // 1. 빈 파일명 금지
        if (name.isBlank()) {
            return false
        }

        // 2. 경로 포함 금지
        if (name.contains("..") || name.contains("/") || name.contains("\\")) {
            return false
        }
        return true
    }

}