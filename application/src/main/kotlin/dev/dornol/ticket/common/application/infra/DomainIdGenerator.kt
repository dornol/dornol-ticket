package dev.dornol.ticket.common.application.infra

import dev.dornol.ticket.common.domain.id.AbstractId
import dev.dornol.ticket.common.domain.id.SnowFlakeIdGenerator
import org.springframework.stereotype.Component

@Component
class DomainIdGenerator {
    inline fun <reified T : AbstractId> generate(): T {
        val id = SnowFlakeIdGenerator.generate()
        return AbstractId.from<T>(id)
    }
}