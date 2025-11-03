package dev.dornol.ticket.manager.application.port.out

interface PasswordEncodePort {
    fun encode(password: String): String
}