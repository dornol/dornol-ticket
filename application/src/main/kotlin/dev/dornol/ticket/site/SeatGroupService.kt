package dev.dornol.ticket.site

import dev.dornol.ticket.common.application.infra.DomainIdGenerator
import dev.dornol.ticket.common.application.out.CurrentUserPort
import dev.dornol.ticket.site.domain.SeatGroup
import dev.dornol.ticket.site.domain.SeatGroupId
import dev.dornol.ticket.site.domain.SiteId
import dev.dornol.ticket.site.port.`in`.*
import dev.dornol.ticket.site.port.`in`.command.AddSeatGroupCommand
import dev.dornol.ticket.site.port.`in`.command.EditSeatGroupCommand
import dev.dornol.ticket.site.port.`in`.dto.SeatGroupDto
import dev.dornol.ticket.site.port.out.*
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service(value = "seatGroupService2")
open class SeatGroupService(
    private val findSeatGroupsPort: FindSeatGroupsPort,
    private val findSeatGroupPort: FindSeatGroupPort,
    private val addSeatGroupPort: AddSeatGroupPort,
    private val editSeatGroupPort: EditSeatGroupPort,
    private val deleteSeatGroupPort: DeleteSeatGroupPort,
    private val findMaxSeatGroupDisplayOrderPort: FindMaxSeatGroupDisplayOrderPort,
    private val findSitePort: FindSitePort,

    private val domainIdGenerator: DomainIdGenerator,
    private val currentUserPort: CurrentUserPort,
) : FindSeatGroupsUseCase, AddSeatGroupUseCase, EditSeatGroupUseCase, DeleteSeatGroupUseCase {

    @Transactional(readOnly = true)
    override fun findSeatGroups(siteId: Long): List<SeatGroupDto> {
        val findById = findSitePort.findById(siteId) ?: throw IllegalArgumentException()
        currentUserPort.matchCompanyId(findById.companyId.get())

        return findSeatGroupsPort.findSeatGroups(siteId)
    }

    @Transactional
    override fun addSeatGroup(command: AddSeatGroupCommand): SeatGroup {
        val findById = findSitePort.findById(command.siteId) ?: throw IllegalArgumentException()
        currentUserPort.matchCompanyId(findById.companyId.get())

        val maxDisplayOrder =
            findMaxSeatGroupDisplayOrderPort.findMaxDisplayOrderBySiteId(SiteId(command.siteId))

        return SeatGroup(
            id = domainIdGenerator.generate(),
            name = command.name,
            siteId = SiteId(command.siteId),
            color = command.color,
            displayOrder = maxDisplayOrder
        ).also { addSeatGroupPort.addSeatGroup(it) }
    }

    @Transactional
    override fun editSeatGroup(id: Long, command: EditSeatGroupCommand) {
        val findById = findSitePort.findById(command.siteId) ?: throw IllegalArgumentException()
        currentUserPort.matchCompanyId(findById.companyId.get())

        val seatGroup = findSeatGroupPort.findSeatGroup(SeatGroupId(id)) ?: throw IllegalArgumentException()

        seatGroup.edit(
            name = command.name,
            color = command.color,
            displayOrder = command.displayOrder
        )

        editSeatGroupPort.editSeatGroup(seatGroup)
    }

    @Transactional
    override fun deleteSeatGroup(id: Long) {
        val seatGroupId = SeatGroupId(id)
        val seatGroup = findSeatGroupPort.findSeatGroup(seatGroupId) ?: throw IllegalArgumentException()
        val findById = findSitePort.findById(seatGroup.siteId.get()) ?: throw IllegalArgumentException()
        currentUserPort.matchCompanyId(findById.companyId.get())

        deleteSeatGroupPort.deleteSeatGroup(seatGroup, currentUserPort.getCurrentUserId())
    }

}