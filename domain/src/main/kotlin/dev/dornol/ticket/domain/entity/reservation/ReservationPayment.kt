package dev.dornol.ticket.domain.entity.reservation

import dev.dornol.ticket.domain.entity.BaseEntity
import dev.dornol.ticket.domain.entity.common.Money
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

private const val TABLE_NAME = "reservation_payment"

private const val RESERVATION_ID = "reservation_id"

@Table(name = TABLE_NAME)
@Entity
class ReservationPayment(
    reservation: Reservation,
    price: Money,
) : BaseEntity() {

    val price: Money = price

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    var paymentState: PaymentState = PaymentState.PENDING_PAYMENT

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = RESERVATION_ID, nullable = false, updatable = false)
    val reservation: Reservation = reservation

}