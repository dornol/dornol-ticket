package dev.dornol.ticket.validation.file

enum class SafeMediaType(
    val mimeType: String
) {

    IMAGE_JPEG("image/jpeg"),
    IMAGE_PNG("image/png"),
    IMAGE_GIF("image/gif"),
    IMAGE_BMP("image/bmp"),
    IMAGE_TIFF("image/tiff"),
    IMAGE_WEBP("image/webp"),

    APPLICATION_PDF("application/pdf"),
    APPLICATION_XLS("application/vnd.ms-excel"),
    APPLICATION_XLSX("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"),
    APPLICATION_MSWORD("application/msword"),
    APPLICATION_MSWORD_DOCX("application/vnd.openxmlformats-officedocument.wordprocessingml.document"),

    TEXT_PLAIN("text/plain")

}