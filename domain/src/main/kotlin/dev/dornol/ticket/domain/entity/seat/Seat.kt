package dev.dornol.ticket.domain.entity.seat

import dev.dornol.ticket.domain.entity.BaseEntity
import jakarta.persistence.*
import kotlin.math.min

private const val SEAT_GROUP_ID = "seat_group_id"
private const val DISPLAY_ORDER = "display_order"

private const val IMAGE_AREA_SIZE = 600.0
private const val BOX_SIZE = 40.0

private const val TABLE_NAME = "seat"

@Table(
    name = TABLE_NAME,
)
@Entity
class Seat(
    name: String,
    group: SeatGroup,
    offset: SeatOffset = SeatOffset(),
    displayOrder: Long = 0L,
) : BaseEntity() {

    @Column(length = 30, nullable = false)
    var name: String = name
        protected set

    @Embedded
    var offset: SeatOffset = offset

    @Column(name = DISPLAY_ORDER, nullable = false)
    var displayOrder: Long = displayOrder

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = SEAT_GROUP_ID, nullable = false)
    var group: SeatGroup = group
        protected set

    fun edit(name: String, seatGroup: SeatGroup) {
        this.name = name
        this.group = seatGroup
    }

    fun moveTo(x: Double, y: Double) {
        offset = SeatOffset(x, y)
    }

    fun copy(displayOrder: Long): Seat {
        return Seat(
            name = this.name,
            group = this.group,
            offset = SeatOffset(min(IMAGE_AREA_SIZE - BOX_SIZE, this.offset.x + 10L), min(IMAGE_AREA_SIZE - BOX_SIZE, this.offset.y + 10)),
            displayOrder = displayOrder,
        )
    }
}