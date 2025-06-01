package dev.dornol.ticket.manager.adapter.out.persistence

import com.querydsl.core.annotations.QueryProjection

data class CompanyProjection @QueryProjection constructor(
    val id: Long,
    val name: String,
    val businessNumber: String
)
