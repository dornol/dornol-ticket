package dev.dornol.ticket.admin.api.app.dto.performance.response

import com.querydsl.core.annotations.QueryProjection
import dev.dornol.ticket.domain.entity.performance.PerformanceType
import dev.dornol.ticket.domain.entity.site.Site

data class PerformanceListDto(
    val id: String,
    val name: String,
    val type: PerformanceType,
    val site: SiteSimpleDto
) {
    @QueryProjection
    constructor(
        id: Long,
        name: String,
        type: PerformanceType,
        site: Site
    ) : this(id.toString(), name, type, SiteSimpleDto(site.id.toString(), site.name, site.address))
}

