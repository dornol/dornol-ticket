package dev.dornol.ticket.admin.api.app.controller.site

import dev.dornol.ticket.admin.api.app.dto.site.SiteAddRequestDto
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

private val log = KotlinLogging.logger {}

@RequestMapping("/sites")
@RestController
class SiteController {

    @PostMapping
    fun addSite(
        @RequestBody @Validated request: SiteAddRequestDto
    ) {
        log.info { "Adding site '${request}'" }
    }

}