package dev.dornol.ticket.domain.entity.seat

import dev.dornol.ticket.domain.entity.BaseEntity
import jakarta.persistence.*
import kotlin.math.min

private const val IMAGE_AREA_SIZE = 600.0
private const val BOX_SIZE = 40.0

@Table(name = "seat")
@Entity
class SeatEntity(
    name: String,
    group: SeatGroupEntity,
    offset: SeatOffsetEntity = SeatOffsetEntity(),
    displayOrder: Long = 0L,
) : BaseEntity() {

    @Column(length = 30, nullable = false)
    var name: String = name
        protected set

    @Embedded
    var offset: SeatOffsetEntity = offset

    @Column(nullable = false)
    var displayOrder: Long = displayOrder

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seat_group_id", nullable = false)
    var seatGroup: SeatGroupEntity = group
        protected set

    fun edit(name: String, seatGroup: SeatGroupEntity) {
        this.name = name
        this.seatGroup = seatGroup
    }

    fun moveTo(x: Double, y: Double) {
        offset = SeatOffsetEntity(x, y)
    }

    fun copy(displayOrder: Long): SeatEntity {
        return SeatEntity(
            name = this.name,
            group = this.seatGroup,
            offset = SeatOffsetEntity(min(IMAGE_AREA_SIZE - BOX_SIZE, this.offset.x + 10L), min(IMAGE_AREA_SIZE - BOX_SIZE, this.offset.y + 10)),
            displayOrder = displayOrder,
        )
    }
}