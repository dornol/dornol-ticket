package dev.dornol.ticket.admin.api.app.controller.site

import dev.dornol.ticket.admin.api.app.dto.site.request.SiteAddRequestDto
import dev.dornol.ticket.admin.api.app.dto.site.request.SiteSearchDto
import dev.dornol.ticket.admin.api.app.service.site.SiteService
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

private val log = KotlinLogging.logger {}

@RequestMapping("/sites")
@RestController
class SiteController(
    private val siteService: SiteService
) {

    @GetMapping
    fun sites(
        search: SiteSearchDto,
        @PageableDefault(sort = ["default"]) pageable: Pageable
    ) = siteService.search(search, pageable)

    @PostMapping
    fun addSite(
        @RequestBody @Validated request: SiteAddRequestDto,
        @AuthenticationPrincipal jwt: Jwt
    ) = siteService.save(jwt.subject.toLong(), request).id.toString()

}