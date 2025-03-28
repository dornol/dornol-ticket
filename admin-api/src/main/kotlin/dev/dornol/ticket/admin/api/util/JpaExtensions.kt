package dev.dornol.ticket.admin.api.util

import com.querydsl.core.types.Expression
import com.querydsl.core.types.Order
import com.querydsl.core.types.OrderSpecifier
import org.springframework.data.domain.Sort


fun Sort.Direction.toOrder() = if (this.isAscending) Order.ASC else Order.DESC
fun <T : Comparable<T>> Expression<T>.sort(order: Sort.Order): OrderSpecifier<T> = OrderSpecifier(order.direction.toOrder(), this)
fun <T : Comparable<T>> Sort.toOrderBy(orderSpecifierSupplier: (sort: Sort.Order) -> OrderSpecifier<T>) = this.map { orderSpecifierSupplier(it) }.toList().toTypedArray()