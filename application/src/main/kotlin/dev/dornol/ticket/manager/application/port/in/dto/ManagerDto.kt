package dev.dornol.ticket.manager.application.port.`in`.dto

data class ManagerDto(
    val id: Long,
    val name: String,
    val username: String,
    val email: String,
    val phone: String
)
