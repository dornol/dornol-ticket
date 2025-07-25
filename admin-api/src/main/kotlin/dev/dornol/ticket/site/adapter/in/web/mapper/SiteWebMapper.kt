package dev.dornol.ticket.site.adapter.`in`.web.mapper

import dev.dornol.ticket.common.adapter.`in`.mapper.toPageQuery
import dev.dornol.ticket.site.adapter.`in`.web.dto.SiteSearchDto
import dev.dornol.ticket.site.port.`in`.command.SearchSitesCommand
import dev.dornol.ticket.site.port.`in`.SiteSearchField
import org.springframework.data.domain.Pageable

fun SiteSearchDto.toSearchSiteCommand(pageable: Pageable) = SearchSitesCommand(
    searchKeys = this.searchFields.map { SiteSearchField.valueOf(it) }.toSet(),
    searchText = this.searchText,
    pageQuery = pageable.toPageQuery()
)
