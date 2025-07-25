package dev.dornol.ticket.manager.application.port.`in`

import dev.dornol.ticket.manager.application.port.`in`.dto.ManagerDto

interface FindManagerUseCase {

    fun findManagerById(id: Long): ManagerDto

}