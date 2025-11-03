package dev.dornol.ticket.common.search

data class PageMeta(
    val size: Int,
    val number: Int,
    val totalElements: Long,
    val totalPages: Int
)
