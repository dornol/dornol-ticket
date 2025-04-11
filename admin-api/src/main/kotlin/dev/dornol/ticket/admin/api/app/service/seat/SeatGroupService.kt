package dev.dornol.ticket.admin.api.app.service.seat

import dev.dornol.ticket.admin.api.app.dto.seat.SeatGroupDto
import dev.dornol.ticket.admin.api.app.repository.seat.SeatGroupRepository
import dev.dornol.ticket.admin.api.app.repository.site.SiteRepository
import dev.dornol.ticket.admin.api.config.exception.common.BadRequestException
import dev.dornol.ticket.domain.entity.seat.SeatGroup
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.util.Assert

@Service
class SeatGroupService(
    private val siteRepository: SiteRepository,
    private val seatGroupRepository: SeatGroupRepository
) {

    fun getSeatGroups(siteId: Long): List<SeatGroupDto> {
        val (seatGroups, seats) = seatGroupRepository.getSeatGroupsAndSeats(siteId)

        val groupBy = seatGroups.groupBy({ it.id }, { it.seats })

        seats.forEach {
            groupBy[it.groupId]?.get(0)?.add(it)
        }

        return seatGroups
    }

    @Transactional
    fun add(siteId: Long, name: String, color: String): SeatGroup {
        val site = siteRepository.findByIdOrNull(siteId) ?: throw BadRequestException()
        val maxDisplayOrder = seatGroupRepository.findMaxDisplayOrderBySiteId(siteId) ?: 0L
        return SeatGroup(name, site, color, maxDisplayOrder + 1L).also { seatGroupRepository.save(it) }
    }

    @Transactional
    fun edit(siteId: Long, seatGroupId: Long, name: String, color: String) {
        val site = siteRepository.findByIdOrNull(siteId) ?: throw BadRequestException()
        val seatGroup = seatGroupRepository.findByIdOrNull(seatGroupId) ?: throw BadRequestException()
        Assert.isTrue(site.id == seatGroup.site.id, "Site with id $seatGroupId not found")
        seatGroup.update(name, color)
    }

}