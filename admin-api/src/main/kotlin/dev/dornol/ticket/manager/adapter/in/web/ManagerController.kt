package dev.dornol.ticket.manager.adapter.`in`.web

import dev.dornol.ticket.admin.api.app.constants.DEFAULT_SORT_ORDER
import dev.dornol.ticket.admin.api.app.dto.manager.request.ManagerSearchDto
import dev.dornol.ticket.common.search.PageQuery
import dev.dornol.ticket.common.search.SortDirection
import dev.dornol.ticket.common.search.SortOption
import dev.dornol.ticket.manager.application.port.`in`.ApproveManagerUseCase
import dev.dornol.ticket.manager.application.port.`in`.SearchManagersCommand
import dev.dornol.ticket.manager.application.port.`in`.SearchManagersUseCase
import dev.dornol.ticket.manager.domain.model.search.ManagerSearchField
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.web.bind.annotation.*

@RequestMapping("/managers")
@RestController
class ManagerController(
    private val searchManagersUseCase: SearchManagersUseCase,
    private val approveManagerUseCase: ApproveManagerUseCase,
) {

    @GetMapping
    fun getManagers(
        search: ManagerSearchDto,
        @PageableDefault(sort = [DEFAULT_SORT_ORDER]) page: Pageable
    ) = searchManagersUseCase.searchManagers(
        SearchManagersCommand(
            searchKeys = search.searchFields.map { ManagerSearchField.valueOf(it.name) }.toSet(),
            searchText = search.searchText,
            pageQuery = PageQuery(
                page = page.pageNumber,
                pageSize = page.pageSize,
                sort = page.sort
                    .map { SortOption(it.property, SortDirection.valueOf(it.direction.toString())) }
                    .toList(),
            )
        )
    )

    @PostMapping("/{id}/approve")
    fun approveManager(
        @PathVariable id: Long,
    ) {
        approveManagerUseCase.approveManager(id)
    }

}