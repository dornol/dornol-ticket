package dev.dornol.ticket.site.infra

import dev.dornol.ticket.common.domain.id.SnowFlakeIdGenerator
import dev.dornol.ticket.site.domain.SiteId
import org.springframework.stereotype.Component

@Component
class SiteIdGenerator {
    fun generate(): SiteId = SiteId(SnowFlakeIdGenerator().generate())
}