package dev.dornol.ticket.site.port.`in`.mapper

import dev.dornol.ticket.site.domain.value.Address
import dev.dornol.ticket.site.port.`in`.dto.AddressDto

fun Address.toAddressDto(): AddressDto = AddressDto(
    zipCode = this.zipCode,
    mainAddress = this.mainAddress,
    detailAddress = this.detailAddress,
)