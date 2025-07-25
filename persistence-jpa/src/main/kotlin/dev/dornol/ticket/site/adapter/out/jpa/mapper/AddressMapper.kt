package dev.dornol.ticket.site.adapter.out.jpa.mapper

import dev.dornol.ticket.site.adapter.out.jpa.AddressEntity
import dev.dornol.ticket.site.domain.value.Address

fun AddressEntity.toDomain(): Address {
    return Address(
        zipCode = this.zipCode,
        mainAddress = this.mainAddress,
        detailAddress = this.detailAddress,
    )
}

fun Address.toEntity() = AddressEntity(
    zipCode = this.zipCode,
    mainAddress = this.mainAddress,
    detailAddress = this.detailAddress,
)