package dev.dornol.ticket.site.adapter.out.persistence

import com.querydsl.core.annotations.QueryProjection

data class AddressProjection @QueryProjection constructor(
    val zipCode: String?,
    val mainAddress: String,
    val detailAddress: String,
)
