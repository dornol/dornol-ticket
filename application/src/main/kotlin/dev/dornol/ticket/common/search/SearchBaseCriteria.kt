package dev.dornol.ticket.common.search

interface SearchBaseCriteria<T> {
    val searchKeys: Set<T>
    val searchText: String
    val pageQuery: PageQuery
}