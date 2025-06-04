package dev.dornol.ticket.manager.adapter.out.jpa.mapper

import dev.dornol.ticket.DomainEntityMapper
import dev.dornol.ticket.manager.adapter.out.jpa.ManagerApprovalEntity
import dev.dornol.ticket.manager.domain.value.ManagerApproval
import org.mapstruct.Mapper
import org.mapstruct.NullValueMappingStrategy

@Mapper(
    componentModel = "spring",
    nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL,
)
interface ManagerApprovalMapper : DomainEntityMapper<ManagerApproval, ManagerApprovalEntity> {
}