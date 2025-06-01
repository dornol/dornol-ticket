package dev.dornol.ticket.manager.adapter.out.jpa.mapper

import dev.dornol.ticket.DomainEntityWithIdMapper
import dev.dornol.ticket.manager.adapter.out.jpa.CompanyEntity
import dev.dornol.ticket.manager.domain.Company
import dev.dornol.ticket.manager.domain.CompanyId
import org.mapstruct.Mapper
import org.mapstruct.NullValueMappingStrategy

@Mapper(
    componentModel = "spring",
    nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL,
)
interface CompanyMapper : DomainEntityWithIdMapper<Company, CompanyEntity, CompanyId> {
    override fun map(id: Long?) = id?.let { CompanyId(id) }
}