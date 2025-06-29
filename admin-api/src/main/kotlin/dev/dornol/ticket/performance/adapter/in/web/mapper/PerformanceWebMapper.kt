package dev.dornol.ticket.performance.adapter.`in`.web.mapper

import dev.dornol.ticket.performance.adapter.`in`.web.dto.PerformanceSearchDto
import dev.dornol.ticket.common.adapter.`in`.mapper.toPageQuery
import dev.dornol.ticket.performance.port.`in`.PerformanceSearchField
import dev.dornol.ticket.performance.port.`in`.SearchPerformancesCommand
import org.springframework.data.domain.Pageable

fun PerformanceSearchDto.toSearchPerformancesCommand(pageable: Pageable) =
    SearchPerformancesCommand(
        searchKeys = this.searchFields.map { PerformanceSearchField.valueOf(it) }.toSet(),
        searchText = this.searchText,
        pageQuery = pageable.toPageQuery(),
    )