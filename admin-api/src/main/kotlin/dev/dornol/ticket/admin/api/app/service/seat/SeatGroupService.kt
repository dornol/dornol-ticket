package dev.dornol.ticket.admin.api.app.service.seat

import dev.dornol.ticket.admin.api.app.dto.seat.SeatGroupDto
import dev.dornol.ticket.admin.api.app.repository.manager.ManagerRepository
import dev.dornol.ticket.admin.api.app.repository.seat.SeatGroupRepository
import dev.dornol.ticket.admin.api.app.repository.seat.SeatRepository
import dev.dornol.ticket.admin.api.app.repository.site.SiteRepository
import dev.dornol.ticket.admin.api.config.exception.common.AccessDeniedException
import dev.dornol.ticket.common.exception.BadRequestException
import dev.dornol.ticket.admin.api.util.alive
import dev.dornol.ticket.admin.api.util.assertAccess
import dev.dornol.ticket.domain.entity.seat.SeatGroupEntity
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.util.Assert

@Service
class SeatGroupService(
    private val managerRepository: ManagerRepository,
    private val siteRepository: SiteRepository,
    private val seatGroupRepository: SeatGroupRepository,
    private val seatRepository: SeatRepository
) {

    fun getSeatGroups(userId: Long, siteId: Long): List<SeatGroupDto> {
        val site = siteRepository.findByIdOrNull(siteId)?.alive() ?: throw BadRequestException()
        val manager = managerRepository.findByIdOrNull(userId)?.alive() ?: throw AccessDeniedException()
        assertAccess(manager.company.id == site.company.id)

        val (seatGroups, seats) = seatGroupRepository.getSeatGroupsAndSeats(siteId)

        val groupBy = seatGroups.groupBy({ it.id }, { it.seats })

        seats.forEach {
            groupBy[it.groupId]?.get(0)?.add(it)
        }

        return seatGroups
    }

    @Transactional
    fun add(userId: Long, siteId: Long, name: String, color: String): SeatGroupEntity {
        val site = siteRepository.findByIdOrNull(siteId)?.alive() ?: throw BadRequestException()
        val manager = managerRepository.findByIdOrNull(userId)?.alive() ?: throw AccessDeniedException()
        assertAccess(manager.company.id == site.company.id)

        val maxDisplayOrder = seatGroupRepository.findMaxDisplayOrderBySiteId(siteId) ?: 0L
        val seatGroup = SeatGroupEntity(name, site, color, maxDisplayOrder + 1L).also { seatGroupRepository.save(it) }
        return seatGroup
    }

    @Transactional
    fun edit(userId: Long, siteId: Long, seatGroupId: Long, name: String, color: String) {
        val site = siteRepository.findByIdOrNull(siteId)?.alive() ?: throw BadRequestException()
        val manager = managerRepository.findByIdOrNull(userId)?.alive() ?: throw AccessDeniedException()
        assertAccess(manager.company.id == site.company.id)

        val seatGroup = seatGroupRepository.findByIdOrNull(seatGroupId) ?: throw BadRequestException()
        Assert.isTrue(site.id == seatGroup.site.id, "Site with id $seatGroupId not found")
        seatGroup.update(name, color)
    }

    @Transactional
    fun delete(userId: Long, siteId: Long, seatGroupId: Long) {
        val site = siteRepository.findByIdOrNull(siteId)?.alive() ?: throw BadRequestException()
        val manager = managerRepository.findByIdOrNull(userId)?.alive() ?: throw AccessDeniedException()
        assertAccess(manager.company.id == site.company.id)

        val seatGroup = seatGroupRepository.findByIdOrNull(seatGroupId) ?: throw BadRequestException()
        val seats = seatRepository.findAllByGroupId(seatGroupId)

        seats.forEach { it.delete(userId) }

        seatGroup.delete(userId)
    }

}