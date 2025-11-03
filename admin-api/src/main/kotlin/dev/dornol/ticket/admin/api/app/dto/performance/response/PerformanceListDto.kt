package dev.dornol.ticket.admin.api.app.dto.performance.response

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer
import com.querydsl.core.annotations.QueryProjection
import dev.dornol.ticket.performance.domain.PerformanceType

data class PerformanceListDto @QueryProjection constructor(
    @JsonSerialize(using = ToStringSerializer::class)
    val id: Long,
    val name: String,
    val type: PerformanceType
)
