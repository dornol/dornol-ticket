package dev.dornol.ticket.manager.application.port.`in`

import dev.dornol.ticket.common.search.PageQuery
import dev.dornol.ticket.common.search.SearchBaseCommand
import dev.dornol.ticket.manager.domain.ManagerRole
import dev.dornol.ticket.manager.domain.model.search.ManagerSearchField

class SearchManagersCommand(
    override val searchKeys: Set<ManagerSearchField>,
    override val searchText: String,
    override val pageQuery: PageQuery,
    val approved: Boolean? = null,
    val managerRole: ManagerRole? = null,
) : SearchBaseCommand<ManagerSearchField>