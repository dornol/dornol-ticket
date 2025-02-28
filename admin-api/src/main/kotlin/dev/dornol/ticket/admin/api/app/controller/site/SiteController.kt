package dev.dornol.ticket.admin.api.app.controller.site

import dev.dornol.ticket.admin.api.app.dto.site.SiteDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RequestMapping("/sites")
@RestController
class SiteController {

    @GetMapping
    fun getSites(): SiteDto {
        return SiteDto(UUID.randomUUID().toString())
    }

}