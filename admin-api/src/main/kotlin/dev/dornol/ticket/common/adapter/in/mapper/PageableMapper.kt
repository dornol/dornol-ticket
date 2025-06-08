package dev.dornol.ticket.common.adapter.`in`.mapper

import dev.dornol.ticket.common.search.PageQuery
import dev.dornol.ticket.common.search.SortDirection
import dev.dornol.ticket.common.search.SortOption
import org.springframework.data.domain.Pageable

fun Pageable.toPageQuery(): PageQuery = PageQuery(
    page = this.pageNumber,
    pageSize = this.pageSize,
    sort = this.sort
        .map { SortOption(it.property, SortDirection.valueOf(it.direction.toString())) }
        .toList(),
)