package dev.dornol.ticket.validation.file

enum class SafeMediaType(
    val mimeType: String,
    val extensions: Set<String>
) {

    IMAGE_JPEG("image/jpeg", setOf("jpg", "jpeg")),
    IMAGE_PNG("image/png", setOf("png")),
    IMAGE_GIF("image/gif", setOf("gif")),
    IMAGE_BMP("image/bmp", setOf("bmp")),
    IMAGE_TIFF("image/tiff", setOf("tiff", "tif")),
    IMAGE_WEBP("image/webp", setOf("webp")),

    APPLICATION_PDF("application/pdf", setOf("pdf")),
    APPLICATION_XLS("application/vnd.ms-excel", setOf("xls")),
    APPLICATION_XLSX("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", setOf("xlsx")),
    APPLICATION_MSWORD("application/msword", setOf("doc")),
    APPLICATION_MSWORD_DOCX("application/vnd.openxmlformats-officedocument.wordprocessingml.document", setOf("docx")),

    TEXT_PLAIN("text/plain", setOf("txt", "log", "csv"))

}