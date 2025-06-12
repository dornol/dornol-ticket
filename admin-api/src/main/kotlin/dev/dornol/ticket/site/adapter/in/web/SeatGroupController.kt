package dev.dornol.ticket.site.adapter.`in`.web

import dev.dornol.ticket.admin.api.app.dto.seat.request.SeatGroupAddRequestDto
import dev.dornol.ticket.admin.api.app.service.seat.SeatGroupService
import dev.dornol.ticket.site.port.`in`.FindSeatGroupsUseCase
import dev.dornol.ticket.site.port.`in`.dto.SeatGroupDto
import jakarta.validation.Valid
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/sites/{siteId}/seat-groups")
@RestController
class SeatGroupController(
    private val seatGroupService: SeatGroupService,
    private val findSeatGroupsUseCase: FindSeatGroupsUseCase
) {

    @GetMapping
    fun getSeatGroups(
        @PathVariable siteId: Long,
        @AuthenticationPrincipal jwt: Jwt
    ): List<SeatGroupDto> {
        return findSeatGroupsUseCase.findSeatGroups(siteId)
    }

//    @PostMapping
//    fun addSeatGroup(
//        @PathVariable siteId: Long,
//        @Valid @RequestBody dto: SeatGroupAddRequestDto,
//        @AuthenticationPrincipal jwt: Jwt
//    ): String {
//        return seatGroupService.add(jwt.subject.toLong(), siteId, dto.name, dto.color).id.toString()
//    }
//
//    @PutMapping("/{seatGroupId}")
//    fun updateSeatGroup(
//        @PathVariable siteId: Long,
//        @PathVariable seatGroupId: Long,
//        @Valid @RequestBody dto: SeatGroupAddRequestDto,
//        @AuthenticationPrincipal jwt: Jwt
//    ) = seatGroupService.edit(jwt.subject.toLong(), siteId, seatGroupId, dto.name, dto.color)
//
//    @DeleteMapping("/{seatGroupId}")
//    fun deleteSeatGroup(
//        @PathVariable siteId: Long,
//        @PathVariable seatGroupId: Long,
//        @AuthenticationPrincipal jwt: Jwt
//    ) = seatGroupService.delete(jwt.subject.toLong(), siteId, seatGroupId)

}