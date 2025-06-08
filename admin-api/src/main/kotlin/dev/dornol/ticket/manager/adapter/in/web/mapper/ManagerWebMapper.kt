package dev.dornol.ticket.manager.adapter.`in`.web.mapper

import dev.dornol.ticket.common.adapter.`in`.mapper.toPageQuery
import dev.dornol.ticket.manager.adapter.`in`.web.dto.ManagerSearchDto
import dev.dornol.ticket.manager.application.port.`in`.ManagerSearchField
import dev.dornol.ticket.manager.application.port.`in`.SearchManagersCommand
import org.springframework.data.domain.Pageable

fun ManagerSearchDto.toSearchManagersCommand(pageable: Pageable): SearchManagersCommand = SearchManagersCommand(
    searchKeys = this.searchFields.map { ManagerSearchField.valueOf(it) }.toSet(),
    searchText = this.searchText,
    approved = this.approved,
    managerRole = this.managerRole,
    pageQuery = pageable.toPageQuery()
)