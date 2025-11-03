package dev.dornol.ticket.common.search

data class PageResult<T>(
    val content: List<T>,
    val page: PageMeta
)
