package dev.dornol.ticket.manager.application.port.out

import dev.dornol.ticket.common.search.PageQuery
import dev.dornol.ticket.common.search.SearchBaseCriteria
import dev.dornol.ticket.manager.domain.ManagerRole
import dev.dornol.ticket.manager.domain.model.search.ManagerSearchField

data class SearchManagersCriteria(
    override val searchKeys: Set<ManagerSearchField>,
    override val searchText: String,
    override val pageQuery: PageQuery,
    val approved: Boolean? = null,
    val managerRole: ManagerRole? = null,
) : SearchBaseCriteria<ManagerSearchField>
