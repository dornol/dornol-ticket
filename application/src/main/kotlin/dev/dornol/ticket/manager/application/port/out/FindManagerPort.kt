package dev.dornol.ticket.manager.application.port.out

import dev.dornol.ticket.manager.domain.Manager

interface FindManagerPort {

    fun findByUsername(username: String): Manager?

}