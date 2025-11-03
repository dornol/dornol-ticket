package dev.dornol.ticket.manager.application.port.out

import dev.dornol.ticket.manager.domain.Company
import dev.dornol.ticket.manager.domain.Manager

interface SaveManagerPort {
    fun save(manager: Manager, company: Company)
}