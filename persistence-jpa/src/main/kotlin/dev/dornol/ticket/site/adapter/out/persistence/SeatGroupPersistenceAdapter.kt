package dev.dornol.ticket.site.adapter.out.persistence

import dev.dornol.ticket.common.alive
import dev.dornol.ticket.site.adapter.out.jpa.mapper.toDomain
import dev.dornol.ticket.site.adapter.out.jpa.mapper.toEntity
import dev.dornol.ticket.site.adapter.out.persistence.mapper.toDto
import dev.dornol.ticket.site.adapter.out.persistence.query.SeatGroupQueryDslSupport
import dev.dornol.ticket.site.domain.SeatGroup
import dev.dornol.ticket.site.domain.SeatGroupId
import dev.dornol.ticket.site.domain.SiteId
import dev.dornol.ticket.site.port.`in`.FindMaxSeatGroupDisplayOrderPort
import dev.dornol.ticket.site.port.`in`.dto.SeatGroupDto
import dev.dornol.ticket.site.port.out.*
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class SeatGroupPersistenceAdapter(
    private val seatGroupEntityRepository: SeatGroupEntityRepository,
    private val seatGroupQueryDslSupport: SeatGroupQueryDslSupport,
    private val siteEntityRepository: SiteEntityRepository,

    ) : FindSeatGroupsPort, FindSeatGroupPort, AddSeatGroupPort, EditSeatGroupPort, DeleteSeatGroupPort,
    FindMaxSeatGroupDisplayOrderPort {

    override fun findSeatGroups(siteId: Long): List<SeatGroupDto> {

        val (seatGroups, seats) = seatGroupQueryDslSupport.getSeatGroupsAndSeats(siteId)

        val seatGroupDtos = seatGroups.map { it.toDto() }

        val groupBy = seatGroupDtos.groupBy({ it.id.get() }, { it.seats })

        seats.forEach {
            groupBy[it.groupId]?.get(0)?.add(it.toDto())
        }

        return seatGroupDtos
    }

    override fun addSeatGroup(seatGroup: SeatGroup) {
        val siteEntity =
            siteEntityRepository.findByIdOrNull(seatGroup.siteId.get())?.alive() ?: throw IllegalArgumentException()

        seatGroupEntityRepository.save(seatGroup.toEntity(siteEntity))
    }

    override fun editSeatGroup(seatGroup: SeatGroup) {
        val seatGroupEntity =
            seatGroupEntityRepository.findByIdOrNull(seatGroup.id.get())?.alive() ?: throw IllegalArgumentException()

        seatGroupEntity.update(
            name = seatGroup.name,
            color = seatGroup.color,
        )

        seatGroupEntityRepository.save(seatGroupEntity)
    }

    override fun deleteSeatGroup(id: SeatGroupId) {
        val seatGroupEntity =
            seatGroupEntityRepository.findByIdOrNull(id.get())?.alive() ?: throw IllegalArgumentException()

        seatGroupEntity.delete()
    }

    override fun findSeatGroup(id: SeatGroupId): SeatGroup? {
        val seatGroupEntity =
            seatGroupEntityRepository.findByIdOrNull(id.get())?.alive() ?: throw IllegalArgumentException()
        return seatGroupEntity.toDomain()
    }

    override fun findMaxDisplayOrderBySiteId(siteId: SiteId): Long {
        return seatGroupEntityRepository.findMaxDisplayOrderBySiteId(siteId.get()) ?: 0L
    }

}