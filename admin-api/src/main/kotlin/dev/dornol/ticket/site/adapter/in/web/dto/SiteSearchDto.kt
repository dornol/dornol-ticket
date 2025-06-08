package dev.dornol.ticket.site.adapter.`in`.web.dto

class SiteSearchDto(
    val searchFields: Set<String> = setOf(),
    val searchText: String = ""
)