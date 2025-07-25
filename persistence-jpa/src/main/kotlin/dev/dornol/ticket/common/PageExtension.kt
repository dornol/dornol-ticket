package dev.dornol.ticket.common

import dev.dornol.ticket.common.search.PageMeta
import dev.dornol.ticket.common.search.PageResult
import org.springframework.data.domain.Page

fun <T, R> Page<T>.toPageResult(mapper: (T) -> R): PageResult<R> {
    return PageResult(
        content = this.content.map(mapper),
        page = PageMeta(
            size = this.size,
            number = this.number,
            totalElements = this.totalElements,
            totalPages = this.totalPages
        )
    )
}