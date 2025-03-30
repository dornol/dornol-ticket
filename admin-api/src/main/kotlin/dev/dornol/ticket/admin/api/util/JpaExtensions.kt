package dev.dornol.ticket.admin.api.util

import com.querydsl.core.types.Expression
import com.querydsl.core.types.Order
import com.querydsl.core.types.OrderSpecifier
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.core.types.dsl.StringPath
import dev.dornol.ticket.admin.api.app.dto.common.request.DefaultSearchDto
import org.springframework.data.domain.Sort


fun Sort.Direction.toOrder() = if (this.isAscending) Order.ASC else Order.DESC
fun <T : Comparable<T>> Expression<T>.sort(order: Sort.Order): OrderSpecifier<T> = OrderSpecifier(order.direction.toOrder(), this)
fun <T : Comparable<T>> Sort.toOrderBy(orderSpecifierSupplier: (sort: Sort.Order) -> OrderSpecifier<T>) = this.map { orderSpecifierSupplier(it) }.toList().toTypedArray()

infix fun <T: BooleanExpression?> T.and(that: T) = this?.and(that) ?: that
infix fun <T: BooleanExpression?> T.or(that: T) = this?.or(that) ?: that

fun <T : Enum<T>> DefaultSearchDto<T>.textSearch(mapper: Map<T, List<StringPath>>): BooleanExpression? {
    if (this.searchText.isBlank()) {
        return null
    }
    return this.searchFields
        .mapNotNull { mapper[it] }
        .flatMap { it.map { path -> path.contains(searchText) } }
        .reduceOrNull(BooleanExpression::or)
}