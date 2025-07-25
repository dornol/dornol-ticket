package dev.dornol.ticket.site.adapter.out.persistence

import dev.dornol.ticket.common.alive
import dev.dornol.ticket.site.adapter.out.jpa.SeatEntity
import dev.dornol.ticket.site.adapter.out.jpa.SeatOffsetEntity
import dev.dornol.ticket.site.adapter.out.jpa.mapper.toDomain
import dev.dornol.ticket.site.domain.Seat
import dev.dornol.ticket.site.domain.SeatGroupId
import dev.dornol.ticket.site.domain.SeatId
import dev.dornol.ticket.site.port.out.AddSeatPort
import dev.dornol.ticket.site.port.out.DeleteSeatPort
import dev.dornol.ticket.site.port.out.EditSeatPort
import dev.dornol.ticket.site.port.out.FindSeatPort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class SeatPersistenceAdapter(
    private val seatEntityRepository: SeatEntityRepository,
    private val seatGroupEntityRepository: SeatGroupEntityRepository,
) : AddSeatPort, FindSeatPort, EditSeatPort, DeleteSeatPort {

    override fun addSeat(seat: Seat) {
        val seatEntity = SeatEntity(
            id = seat.id.get(),
            name = seat.name,
            group = seatGroupEntityRepository.findById(seat.groupId.get()).get(),
            offset = SeatOffsetEntity(
                seat.offset.x,
                seat.offset.y,
            ),
            displayOrder = seat.displayOrder
        )
        seatEntityRepository.save(seatEntity)
    }

    override fun findSeat(seatId: Long): Seat? {
        return seatEntityRepository.findByIdOrNull(seatId)?.alive()?.let {
            Seat(
                id = SeatId(it.id),
                name = it.name,
                groupId = SeatGroupId(it.seatGroup.id),
                offset = it.offset.toDomain(),
                displayOrder = it.displayOrder,
            )
        }
    }

    override fun editSeat(seat: Seat) {
        val seatEntity = seatEntityRepository.findByIdOrNull(seat.id.get())?.alive()
            ?: throw IllegalStateException()
        seatEntity.apply(
            seat = seat,
            seatGroup = seatGroupEntityRepository.findById(seat.groupId.get()).get(),
        )
    }

    override fun deleteSeat(id: SeatId) {
        val seatEntity = seatEntityRepository.findByIdOrNull(id.get())?.alive()
            ?: throw IllegalStateException()
        seatEntity.delete()
    }

}