package dev.dornol.ticket.manager.application.port.`in`

interface CheckUsernameExistsUseCase {

    fun exists(username: String): Boolean

}