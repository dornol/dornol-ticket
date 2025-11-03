package dev.dornol.ticket.site.port.`in`.mapper

import dev.dornol.ticket.site.domain.value.SeatOffset
import dev.dornol.ticket.site.port.`in`.dto.SeatOffsetDto

fun SeatOffset.toDto(): SeatOffsetDto = SeatOffsetDto(this.x, this.y)