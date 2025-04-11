package dev.dornol.ticket.admin.api.app.controller.site

import dev.dornol.ticket.admin.api.app.dto.seat.SeatGroupDto
import dev.dornol.ticket.admin.api.app.dto.seat.request.SeatGroupAddRequestDto
import dev.dornol.ticket.admin.api.app.service.seat.SeatGroupService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RequestMapping("/sites/{siteId}/seat-groups")
@RestController
class SeatGroupsController(
    private val seatGroupService: SeatGroupService
) {

    @GetMapping
    fun getSeatGroups(@PathVariable siteId: Long): List<SeatGroupDto> {
        return seatGroupService.getSeatGroups(siteId)
    }

    @PostMapping
    fun addSeatGroup(
        @PathVariable siteId: Long,
        @Valid @RequestBody dto: SeatGroupAddRequestDto
    ): String {
        return seatGroupService.add(siteId, dto.name, dto.color).id.toString()
    }

    @PutMapping("/{seatGroupId}")
    fun updateSeatGroup(
        @PathVariable siteId: Long,
        @PathVariable seatGroupId: Long,
        @Valid @RequestBody dto: SeatGroupAddRequestDto
    ) {
        seatGroupService.edit(siteId, seatGroupId, dto.name, dto.color)
    }

}