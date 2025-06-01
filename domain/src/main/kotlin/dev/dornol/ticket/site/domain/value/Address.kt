package dev.dornol.ticket.site.domain.value

data class Address(
    val zipCode: String?,
    val mainAddress: String,
    val detailAddress: String,
)