package dev.dornol.ticket.site

import dev.dornol.ticket.common.application.out.CurrentUserPort
import dev.dornol.ticket.site.port.`in`.FindSeatGroupsUseCase
import dev.dornol.ticket.site.port.`in`.dto.SeatGroupDto
import dev.dornol.ticket.site.port.out.FindSeatGroupsPort
import dev.dornol.ticket.site.port.out.FindSitePort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service(value = "seatGroupService2")
open class SeatGroupService(
    private val findSeatGroupsPort: FindSeatGroupsPort,
    private val findSitePort: FindSitePort,
    private val currentUserPort: CurrentUserPort
) : FindSeatGroupsUseCase {

    @Transactional(readOnly = true)
    override fun findSeatGroups(siteId: Long): List<SeatGroupDto> {
        val findById = findSitePort.findById(siteId) ?: throw IllegalArgumentException()
        currentUserPort.matchCompanyId(findById.companyId.get())

        return findSeatGroupsPort.findSeatGroups(siteId)
    }

}