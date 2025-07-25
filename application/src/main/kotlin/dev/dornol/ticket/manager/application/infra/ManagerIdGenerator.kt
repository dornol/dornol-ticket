package dev.dornol.ticket.manager.application.infra

import dev.dornol.ticket.common.domain.id.SnowFlakeIdGenerator
import dev.dornol.ticket.manager.domain.ManagerId
import org.springframework.stereotype.Component

@Component
class ManagerIdGenerator {
    fun generate(): ManagerId = ManagerId(SnowFlakeIdGenerator().generate())
}