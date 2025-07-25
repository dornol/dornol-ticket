package dev.dornol.ticket.site.adapter.`in`.web

import dev.dornol.ticket.site.adapter.`in`.web.dto.SeatGroupAddRequestDto
import dev.dornol.ticket.site.adapter.`in`.web.dto.SeatGroupEditRequestDto
import dev.dornol.ticket.site.port.`in`.AddSeatGroupUseCase
import dev.dornol.ticket.site.port.`in`.DeleteSeatGroupUseCase
import dev.dornol.ticket.site.port.`in`.EditSeatGroupUseCase
import dev.dornol.ticket.site.port.`in`.FindSeatGroupsUseCase
import dev.dornol.ticket.site.port.`in`.command.AddSeatGroupCommand
import dev.dornol.ticket.site.port.`in`.command.EditSeatGroupCommand
import dev.dornol.ticket.site.port.`in`.dto.SeatGroupDto
import jakarta.validation.Valid
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.web.bind.annotation.*

@RequestMapping("/sites/{siteId}/seat-groups")
@RestController
class SeatGroupController(
    private val findSeatGroupsUseCase: FindSeatGroupsUseCase,
    private val addSeatGroupUseCase: AddSeatGroupUseCase,
    private val editSeatGroupUseCase: EditSeatGroupUseCase,
    private val deleteSeatGroupUseCase: DeleteSeatGroupUseCase,
) {

    @GetMapping
    fun getSeatGroups(
        @PathVariable siteId: Long,
        @AuthenticationPrincipal jwt: Jwt
    ): List<SeatGroupDto> {
        return findSeatGroupsUseCase.findSeatGroups(siteId)
    }

    @PostMapping
    fun addSeatGroup(
        @PathVariable siteId: Long,
        @Valid @RequestBody dto: SeatGroupAddRequestDto,
        @AuthenticationPrincipal jwt: Jwt
    ): String {
        val seatGroup = addSeatGroupUseCase.addSeatGroup(AddSeatGroupCommand(siteId, dto.name, dto.color))
        return seatGroup.name
    }

    @PutMapping("/{seatGroupId}")
    fun updateSeatGroup(
        @PathVariable siteId: Long,
        @PathVariable seatGroupId: Long,
        @Valid @RequestBody dto: SeatGroupEditRequestDto,
        @AuthenticationPrincipal jwt: Jwt
    ) = editSeatGroupUseCase.editSeatGroup(
        seatGroupId,
        EditSeatGroupCommand(siteId, dto.name, dto.color, dto.displayOrder)
    )

    @DeleteMapping("/{seatGroupId}")
    fun deleteSeatGroup(
        @PathVariable siteId: Long,
        @PathVariable seatGroupId: Long,
        @AuthenticationPrincipal jwt: Jwt
    ) = deleteSeatGroupUseCase.deleteSeatGroup(seatGroupId)

}