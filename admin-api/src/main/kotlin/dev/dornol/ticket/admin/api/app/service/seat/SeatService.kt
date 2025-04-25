package dev.dornol.ticket.admin.api.app.service.seat

import dev.dornol.ticket.admin.api.app.repository.manager.ManagerRepository
import dev.dornol.ticket.admin.api.app.repository.seat.SeatGroupRepository
import dev.dornol.ticket.admin.api.app.repository.seat.SeatRepository
import dev.dornol.ticket.admin.api.app.repository.site.SiteRepository
import dev.dornol.ticket.admin.api.config.exception.common.AccessDeniedException
import dev.dornol.ticket.admin.api.config.exception.common.BadRequestException
import dev.dornol.ticket.admin.api.util.alive
import dev.dornol.ticket.admin.api.util.assertAccess
import dev.dornol.ticket.domain.entity.seat.Seat
import dev.dornol.ticket.domain.entity.seat.SeatOffset
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SeatService(
    private val siteRepository: SiteRepository,
    private val seatGroupRepository: SeatGroupRepository,
    private val managerRepository: ManagerRepository,
    private val seatRepository: SeatRepository
) {

    @Transactional
    fun add(toLong: Long, siteId: Long, seatGroupId: Long, x: Double, y:Double): Seat {
        val site = siteRepository.findByIdOrNull(siteId)?.alive() ?: throw BadRequestException()
        val seatGroup = seatGroupRepository.findByIdOrNull(seatGroupId)?.alive() ?: throw BadRequestException()
        val maxDisplayOrder = seatRepository.maxDisplayOrderByGroupId(seatGroupId) ?: 0L
        assertAccess(site.id == seatGroup.site.id)

        return Seat("new seat", seatGroup, SeatOffset(x, y), maxDisplayOrder + 1).also { seatRepository.save(it) }
    }

    @Transactional
    fun duplicate(userId: Long, siteId: Long, seatGroupId: Long, seatId: Long): Seat {
        val seat = this.validateAndGetSeat(userId, siteId, seatGroupId, seatId)
        val maxDisplayOrder = seatRepository.maxDisplayOrderByGroupId(seatGroupId) ?: 0L
        return seat.copy(maxDisplayOrder + 1).also { seatRepository.save(it) }
    }

    @Transactional
    fun moveSeat(userId: Long, siteId: Long, seatGroupId: Long, seatId: Long, x: Double, y: Double) {
        val seat = this.validateAndGetSeat(userId, siteId, seatGroupId, seatId)
        seat.moveTo(x, y)
    }

    @Transactional
    fun edit(userId: Long, siteId: Long, seatGroupId: Long, seatId: Long, name: String, groupId: Long) {
        val seat = this.validateAndGetSeat(userId, siteId, seatGroupId, seatId)
        val group = seatGroupRepository.findByIdOrNull(groupId)?.alive() ?: throw AccessDeniedException()
        assertAccess(siteId == group.site.id)
        seat.edit(name, group)
    }

    @Transactional
    fun delete(userId: Long, siteId: Long, seatGroupId: Long, seatId: Long) {
        val seat = this.validateAndGetSeat(userId, siteId, seatGroupId, seatId)
        seat.delete(userId)
    }

    private fun validateAndGetSeat(userId: Long, siteId: Long, seatGroupId: Long, seatId: Long): Seat {
        val site = siteRepository.findByIdOrNull(siteId)?.alive() ?: throw BadRequestException()
        val seatGroup = seatGroupRepository.findByIdOrNull(seatGroupId)?.alive() ?: throw BadRequestException()
        val seat = seatRepository.findByIdOrNull(seatId)?.alive() ?: throw BadRequestException()
        val manager = managerRepository.findByIdOrNull(userId)?.alive() ?: throw AccessDeniedException()
        assertAccess(site.id == seatGroup.site.id)
        assertAccess(seatGroup.id == seat.group.id)
        assertAccess(manager.company.id == site.company.id)

        return seat
    }

}