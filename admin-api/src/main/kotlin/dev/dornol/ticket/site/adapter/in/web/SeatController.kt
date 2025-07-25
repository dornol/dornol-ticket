package dev.dornol.ticket.site.adapter.`in`.web

import dev.dornol.ticket.site.adapter.`in`.web.dto.SeatEditRequestDto
import dev.dornol.ticket.site.adapter.`in`.web.dto.SeatMoveRequestDto
import dev.dornol.ticket.site.port.`in`.*
import dev.dornol.ticket.site.port.`in`.command.AddSeatCommand
import dev.dornol.ticket.site.port.`in`.command.EditSeatCommand
import dev.dornol.ticket.site.port.`in`.command.MoveSeatCommand
import jakarta.validation.Valid
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.web.bind.annotation.*

@RequestMapping("/sites/{siteId}/seat-groups/{seatGroupId}/seats")
@RestController
class SeatController(
    private val addSeatUseCase: AddSeatUseCase,
    private val duplicateSeatUseCase: DuplicateSeatUseCase,
    private val moveSeatUseCase: MoveSeatUseCase,
    private val editSeatUseCase: EditSeatUseCase,
    private val deleteSeatUseCase: DeleteSeatUseCase
) {

    @PostMapping
    fun add(
        @PathVariable siteId: Long,
        @PathVariable seatGroupId: Long,
        @Valid @RequestBody dto: SeatMoveRequestDto,
        @AuthenticationPrincipal jwt: Jwt
    ) = addSeatUseCase.addSeat(
        AddSeatCommand(
            siteId = siteId,
            seatGroupId = seatGroupId,
            x = dto.x,
            y = dto.y,
        )
    ).id

    @PostMapping("/{seatId}/duplicate")
    fun duplicate(
        @PathVariable siteId: Long,
        @PathVariable seatGroupId: Long,
        @PathVariable seatId: Long,
        @AuthenticationPrincipal jwt: Jwt
    ) = duplicateSeatUseCase.duplicateSeat(seatId).id

    @PutMapping("/{seatId}/move")
    fun moveSeat(
        @PathVariable siteId: Long,
        @PathVariable seatGroupId: Long,
        @PathVariable seatId: Long,
        @Valid @RequestBody dto: SeatMoveRequestDto,
        @AuthenticationPrincipal jwt: Jwt
    ) = moveSeatUseCase.moveSeat(
        seatId = seatId,
        MoveSeatCommand(
            dto.x,
            dto.y
        )
    )

    @PutMapping("/{seatId}")
    fun edit(
        @PathVariable siteId: Long,
        @PathVariable seatGroupId: Long,
        @PathVariable seatId: Long,
        @Valid @RequestBody dto: SeatEditRequestDto,
        @AuthenticationPrincipal jwt: Jwt
    ) = editSeatUseCase.editSeat(seatId, EditSeatCommand(
        dto.groupId,
        dto.name
    ))

    @DeleteMapping("/{seatId}")
    fun delete(
        @PathVariable siteId: Long,
        @PathVariable seatGroupId: Long,
        @PathVariable seatId: Long,
        @AuthenticationPrincipal jwt: Jwt
    ) = deleteSeatUseCase.deleteSeat(seatId)


}