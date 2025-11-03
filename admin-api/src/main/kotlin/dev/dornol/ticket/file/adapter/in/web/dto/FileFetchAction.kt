package dev.dornol.ticket.file.adapter.`in`.web.dto

enum class FileFetchAction(
    val contentDisposition: String
) {
    VIEW("inline"),
    DOWNLOAD("attachment"),
}