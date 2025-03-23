package dev.dornol.ticket.admin.api.app.repository.manager

import dev.dornol.ticket.admin.api.app.dto.manager.ManagerListDto
import dev.dornol.ticket.admin.api.app.dto.manager.ManagerSearchDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface ManagerQueryRepository {

    fun searchManagers(search: ManagerSearchDto, pageable: Pageable): Page<ManagerListDto>

}