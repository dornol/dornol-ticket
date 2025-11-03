package dev.dornol.ticket.common.search

data class SortOption(
    val property: String,
    val direction: SortDirection = SortDirection.ASC,
)
