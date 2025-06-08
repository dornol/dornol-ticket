package dev.dornol.ticket.site.adapter.`in`.web

import dev.dornol.ticket.site.adapter.`in`.web.dto.SiteAddRequestDto
import dev.dornol.ticket.site.adapter.`in`.web.dto.SiteEditRequestDto
import dev.dornol.ticket.site.adapter.`in`.web.dto.SiteSearchDto
import dev.dornol.ticket.site.adapter.`in`.web.mapper.toSearchSiteCommand
import dev.dornol.ticket.site.port.`in`.EditSiteCommand
import dev.dornol.ticket.site.port.`in`.EditSiteUseCase
import dev.dornol.ticket.site.port.`in`.FindSiteUseCase
import dev.dornol.ticket.site.port.`in`.SaveSiteCommand
import dev.dornol.ticket.site.port.`in`.SaveSiteUseCase
import dev.dornol.ticket.site.port.`in`.SearchSitesUseCase
import dev.dornol.ticket.site.port.`in`.dto.AddressDto
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/sites")
@RestController("siteController2")
class SiteController(
    private val searchSitesUseCase: SearchSitesUseCase,
    private val findSiteUseCase: FindSiteUseCase,
    private val saveSiteUseCase: SaveSiteUseCase,
    private val editSiteUseCase: EditSiteUseCase
) {

    @GetMapping
    fun sites(
        search: SiteSearchDto,
        @PageableDefault(sort = ["default"]) pageable: Pageable
    ) = searchSitesUseCase.searchSites(search.toSearchSiteCommand(pageable))

    @GetMapping("/{id}")
    fun site(
        @PathVariable id: Long,
    ) = findSiteUseCase.findById(id)

    @PostMapping
    fun addSite(
        @RequestBody @Validated request: SiteAddRequestDto
    ) = saveSiteUseCase.save(SaveSiteCommand(
        name = request.name,
        address = AddressDto(
            zipCode = request.address.zipCode,
            mainAddress = request.address.mainAddress,
            detailAddress = request.address.detailAddress,
        ),
        seatingMapFileUuid = request.seatingMapFileUuid
    ))

    @PutMapping("/{id}/edit")
    fun editSite(
        @PathVariable id: Long,
        @RequestBody @Validated request: SiteEditRequestDto,
        @AuthenticationPrincipal jwt: Jwt
    ) = editSiteUseCase.edit(id, EditSiteCommand(
        name = request.name,
        address = AddressDto(
            zipCode = request.address.zipCode,
            mainAddress = request.address.mainAddress,
            detailAddress = request.address.detailAddress,
        ),
        seatingMapFileUuid = request.seatingMapFileUuid
    ))

}