package dev.dornol.ticket.manager.application.port.`in`

data class ManagerJoinCommand(
    val username: String,

    val password: String,

    val email: String,

    val phone: String,

    val name: String,

    val companyName: String,

    val businessNumber: String,
)