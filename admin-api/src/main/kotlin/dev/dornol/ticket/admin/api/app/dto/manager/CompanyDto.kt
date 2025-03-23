package dev.dornol.ticket.admin.api.app.dto.manager

import com.querydsl.core.annotations.QueryProjection

data class CompanyDto @QueryProjection constructor(
    val name: String,
    val businessNumber: String,
)