package dev.dornol.ticket.admin.api.app.dto.performance.response

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer
import com.querydsl.core.annotations.QueryProjection
import dev.dornol.ticket.domain.entity.performance.Performance
import dev.dornol.ticket.domain.entity.performance.PerformanceType

data class PerformanceDetailDto @QueryProjection constructor(
    @JsonSerialize(using = ToStringSerializer::class)
    val id: Long,
    val name: String,
    val type: PerformanceType
) {
    constructor(performance: Performance) : this(performance.id!!, performance.name, performance.type)
}