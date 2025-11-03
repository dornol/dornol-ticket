package dev.dornol.ticket.common.search

data class PageQuery(
    val page: Int = 0,
    val pageSize: Int = 10,
    val sort: List<SortOption> = emptyList()
)
