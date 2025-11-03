package dev.dornol.ticket.site.infra

import dev.dornol.ticket.common.domain.id.SnowFlakeIdGenerator
import dev.dornol.ticket.site.domain.SeatId
import org.springframework.stereotype.Component

@Component
class SeatIdGenerator {
    fun generate(): SeatId = SeatId(SnowFlakeIdGenerator().generate())
}