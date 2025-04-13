package dev.dornol.ticket.admin.api.app.controller.site

import dev.dornol.ticket.admin.api.app.dto.seat.request.SeatEditRequestDto
import dev.dornol.ticket.admin.api.app.dto.seat.request.SeatMoveRequestDto
import dev.dornol.ticket.admin.api.app.service.seat.SeatService
import jakarta.validation.Valid
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.web.bind.annotation.*

@RequestMapping("/sites/{siteId}/seat-groups/{seatGroupId}/seats")
@RestController
class SeatController(
    private val seatService: SeatService,
) {

    @PostMapping
    fun add(
        @PathVariable siteId: Long,
        @PathVariable seatGroupId: Long,
        @Valid @RequestBody dto: SeatMoveRequestDto,
        @AuthenticationPrincipal jwt: Jwt
    ) = seatService.add(jwt.subject.toLong(), siteId, seatGroupId, dto.x, dto.y).id

    @PostMapping("/{seatId}/duplicate")
    fun duplicate(
        @PathVariable siteId: Long,
        @PathVariable seatGroupId: Long,
        @PathVariable seatId: Long,
        @AuthenticationPrincipal jwt: Jwt
    ) = seatService.duplicate(jwt.subject.toLong(), siteId, seatGroupId, seatId).id

    @PutMapping("/{seatId}/move")
    fun moveSeat(
        @PathVariable siteId: Long,
        @PathVariable seatGroupId: Long,
        @PathVariable seatId: Long,
        @Valid @RequestBody dto: SeatMoveRequestDto,
        @AuthenticationPrincipal jwt: Jwt
    ) = seatService.moveSeat(jwt.subject.toLong(), siteId, seatGroupId, seatId, dto.x, dto.y)

    @PutMapping("/{seatId}")
    fun edit(
        @PathVariable siteId: Long,
        @PathVariable seatGroupId: Long,
        @PathVariable seatId: Long,
        @Valid @RequestBody dto: SeatEditRequestDto,
        @AuthenticationPrincipal jwt: Jwt
    ) = seatService.edit(jwt.subject.toLong(), siteId, seatGroupId, seatId, dto.name, dto.groupId)

    @DeleteMapping("/{seatId}")
    fun delete(
        @PathVariable siteId: Long,
        @PathVariable seatGroupId: Long,
        @PathVariable seatId: Long,
        @AuthenticationPrincipal jwt: Jwt
    ) = seatService.delete(jwt.subject.toLong(), siteId, seatGroupId, seatId)

}