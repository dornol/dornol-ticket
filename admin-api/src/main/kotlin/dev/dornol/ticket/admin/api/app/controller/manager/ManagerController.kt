package dev.dornol.ticket.admin.api.app.controller.manager

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/managers")
@RestController
class ManagerController {

    @GetMapping
    fun getManagers() {

    }

}