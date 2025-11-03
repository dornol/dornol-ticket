package dev.dornol.ticket.performance.adapter.out.persistence.query

import com.querydsl.core.types.dsl.StringPath
import dev.dornol.ticket.common.sort
import dev.dornol.ticket.common.textSearch
import dev.dornol.ticket.common.toOrderBy
import dev.dornol.ticket.performance.port.`in`.PerformanceSearchField
import dev.dornol.ticket.performance.port.out.SearchPerformancesCriteria
import org.springframework.data.domain.Sort
import dev.dornol.ticket.performance.adapter.out.jpa.QPerformanceEntity.performanceEntity as performance

object PerformancePredicates {
    val mapper: Map<PerformanceSearchField, List<StringPath>> = mapOf(
        PerformanceSearchField.NAME to listOf(performance.name)
    )

    fun build(criteria: SearchPerformancesCriteria) = arrayOf(
        performance.deleted.isFalse,
        criteria.textSearch(mapper),
    )

    fun sort(sort: Sort) = sort.toOrderBy {
        when (it.property) {
            "name" -> performance.name.sort(it)
            "type" -> performance.type.sort(it)
            else -> null
        }
    }



}