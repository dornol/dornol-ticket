package dev.dornol.ticket.domain.entity.site.address

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
data class AddressEntity(
    @Column(length = 5)
    val zipCode: String?,
    @Column(length = 255)
    val mainAddress: String,
    @Column(length = 255)
    val detailAddress: String,
)