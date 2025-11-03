package dev.dornol.ticket.performance.adapter.`in`.web.dto

class PerformanceSearchDto(
    val searchFields: Set<String> = hashSetOf(),
    val searchText: String = ""
)
