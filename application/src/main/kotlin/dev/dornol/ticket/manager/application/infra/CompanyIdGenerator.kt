package dev.dornol.ticket.manager.application.infra

import dev.dornol.ticket.common.domain.id.SnowFlakeIdGenerator
import dev.dornol.ticket.manager.domain.CompanyId
import org.springframework.stereotype.Component

@Component
class CompanyIdGenerator {
    fun generate(): CompanyId = CompanyId(SnowFlakeIdGenerator().generate())
}