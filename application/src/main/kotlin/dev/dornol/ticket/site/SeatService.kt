package dev.dornol.ticket.site

import dev.dornol.ticket.common.application.out.CurrentUserPort
import dev.dornol.ticket.common.exception.BadRequestException
import dev.dornol.ticket.site.domain.Seat
import dev.dornol.ticket.site.domain.SeatGroupId
import dev.dornol.ticket.site.domain.value.SeatOffset
import dev.dornol.ticket.site.infra.SeatIdGenerator
import dev.dornol.ticket.site.port.`in`.AddSeatUseCase
import dev.dornol.ticket.site.port.`in`.DuplicateSeatUseCase
import dev.dornol.ticket.site.port.`in`.FindMaxSeatGroupDisplayOrderPort
import dev.dornol.ticket.site.port.`in`.command.AddSeatCommand
import dev.dornol.ticket.site.port.out.AddSeatPort
import dev.dornol.ticket.site.port.out.FindSeatGroupPort
import dev.dornol.ticket.site.port.out.FindSeatPort
import dev.dornol.ticket.site.port.out.FindSitePort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
open class SeatService(
    private val findSitePort: FindSitePort,
    private val findSeatGroupPort: FindSeatGroupPort,
    private val maxSeatGroupDisplayOrderPort: FindMaxSeatGroupDisplayOrderPort,
    private val findSeatPort: FindSeatPort,
    private val addSeatPort: AddSeatPort,

    private val seatIdGenerator: SeatIdGenerator,

    private val currentUserPort: CurrentUserPort
) : AddSeatUseCase, DuplicateSeatUseCase {

    @Transactional
    override fun addSeat(command: AddSeatCommand): Seat {
        val site = findSitePort.findById(command.siteId) ?: throw BadRequestException()
        val seatGroup = findSeatGroupPort.findSeatGroup(SeatGroupId(command.seatGroupId)) ?: throw BadRequestException()
        val maxDisplayOrder = maxSeatGroupDisplayOrderPort.findMaxDisplayOrderBySiteId(site.id)
        assert(site.id == seatGroup.siteId)
        currentUserPort.matchCompanyId(site.companyId.get())

        return Seat(
            id = seatIdGenerator.generate(),
            name = "new seat",
            seatGroup.id,
            SeatOffset(command.x, command.y),
            maxDisplayOrder + 1
        ).also { addSeatPort.addSeat(it) }
    }

    @Transactional
    override fun duplicateSeat(seatId: Long): Seat {
        val seat = findSeatPort.findSeat(seatId) ?: throw BadRequestException()
        val seatGroup = findSeatGroupPort.findSeatGroup(seat.groupId) ?: throw BadRequestException()
        val site = findSitePort.findById(seatGroup.siteId.get()) ?: throw BadRequestException()
        currentUserPort.matchCompanyId(site.companyId.get())

        val maxDisplayOrder = maxSeatGroupDisplayOrderPort.findMaxDisplayOrderBySiteId(site.id)

        return seat.copy(seatIdGenerator.generate(), maxDisplayOrder + 1)
            .also { addSeatPort.addSeat(it) }
    }
}