package dev.dornol.ticket.domain.entity.reservation

import dev.dornol.ticket.common.domain.Money
import dev.dornol.ticket.domain.converter.MoneyConverter
import dev.dornol.ticket.domain.converter.enums.PaymentStatusConverter
import dev.dornol.ticket.domain.entity.BaseEntity
import dev.dornol.ticket.reservation.domain.PaymentStatus
import jakarta.persistence.*

@Table(name = "reservation_payment")
@Entity
class ReservationPaymentEntity(
    id: Long,
    reservation: ReservationEntity,
    price: Money,
) : BaseEntity(id) {

    @Convert(converter = MoneyConverter::class)
    @Column(nullable = false)
    val price: Money = price

    @Convert(converter = PaymentStatusConverter::class)
    @Column(nullable = false)
    var paymentStatus: PaymentStatus = PaymentStatus.PENDING_PAYMENT

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "reservation_id", nullable = false, updatable = false)
    val reservation: ReservationEntity = reservation

}