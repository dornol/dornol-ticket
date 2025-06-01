package dev.dornol.ticket.common.search

interface SearchBaseCommand<T> {
    val searchKeys: Set<T>
    val searchText: String
    val pageQuery: PageQuery
}