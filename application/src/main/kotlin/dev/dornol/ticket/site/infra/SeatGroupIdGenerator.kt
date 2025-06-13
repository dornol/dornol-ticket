package dev.dornol.ticket.site.infra

import dev.dornol.ticket.common.domain.id.SnowFlakeIdGenerator
import dev.dornol.ticket.site.domain.SeatGroupId
import org.springframework.stereotype.Component

@Component
class SeatGroupIdGenerator {
    fun generate(): SeatGroupId = SeatGroupId(SnowFlakeIdGenerator().generate())
}