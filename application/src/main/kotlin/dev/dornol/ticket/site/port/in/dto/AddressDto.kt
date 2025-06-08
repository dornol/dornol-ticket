package dev.dornol.ticket.site.port.`in`.dto

data class AddressDto(
    val zipCode: String?,
    val mainAddress: String,
    val detailAddress: String,
)
