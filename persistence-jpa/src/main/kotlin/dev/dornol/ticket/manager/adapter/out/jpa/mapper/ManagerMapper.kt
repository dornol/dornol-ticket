package dev.dornol.ticket.manager.adapter.out.jpa.mapper

import dev.dornol.ticket.DomainEntityWithIdMapper
import dev.dornol.ticket.manager.adapter.out.jpa.ManagerEntity
import dev.dornol.ticket.manager.domain.Manager
import dev.dornol.ticket.manager.domain.ManagerId
import org.mapstruct.Mapper
import org.mapstruct.NullValueMappingStrategy

@Mapper(
    componentModel = "spring",
    nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL,
    uses = [ManagerApprovalMapper::class]
)
interface ManagerMapper : DomainEntityWithIdMapper<Manager, ManagerEntity, ManagerId> {
    override fun map(id: Long?) = id?.let { ManagerId(id) }
}