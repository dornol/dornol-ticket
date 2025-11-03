package dev.dornol.ticket.common

import dev.dornol.ticket.common.search.PageQuery
import dev.dornol.ticket.common.search.SortOption
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort

fun List<SortOption>.toSort(): Sort = Sort.by(
    this.map { Sort.Order(Sort.Direction.fromString(it.direction.toString()), it.property) }
)

fun PageQuery.toPageable(): Pageable = PageRequest.of(this.page, this.pageSize, this.sort.toSort())