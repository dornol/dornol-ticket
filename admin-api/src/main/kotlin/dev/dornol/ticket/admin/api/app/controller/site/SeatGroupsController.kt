package dev.dornol.ticket.admin.api.app.controller.site

import dev.dornol.ticket.admin.api.app.dto.seat.SeatGroupDto
import dev.dornol.ticket.admin.api.app.dto.seat.request.SeatGroupAddRequestDto
import dev.dornol.ticket.admin.api.app.service.seat.SeatGroupService
import jakarta.validation.Valid
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.web.bind.annotation.*

@RequestMapping("/sites/{siteId}/seat-groups")
@RestController
class SeatGroupsController(
    private val seatGroupService: SeatGroupService
) {

    @GetMapping
    fun getSeatGroups(
        @PathVariable siteId: Long,
        @AuthenticationPrincipal jwt: Jwt
    ): List<SeatGroupDto> {
        return seatGroupService.getSeatGroups(jwt.subject.toLong(), siteId)
    }

    @PostMapping
    fun addSeatGroup(
        @PathVariable siteId: Long,
        @Valid @RequestBody dto: SeatGroupAddRequestDto,
        @AuthenticationPrincipal jwt: Jwt
    ): String {
        return seatGroupService.add(jwt.subject.toLong(), siteId, dto.name, dto.color).id.toString()
    }

    @PutMapping("/{seatGroupId}")
    fun updateSeatGroup(
        @PathVariable siteId: Long,
        @PathVariable seatGroupId: Long,
        @Valid @RequestBody dto: SeatGroupAddRequestDto,
        @AuthenticationPrincipal jwt: Jwt
    ) = seatGroupService.edit(jwt.subject.toLong(), siteId, seatGroupId, dto.name, dto.color)

    @DeleteMapping("/{seatGroupId}")
    fun deleteSeatGroup(
        @PathVariable siteId: Long,
        @PathVariable seatGroupId: Long,
        @AuthenticationPrincipal jwt: Jwt
    ) = seatGroupService.delete(jwt.subject.toLong(), siteId, seatGroupId)

}