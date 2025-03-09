package dev.dornol.ticket.admin.api.app.dto.user

data class UserDto(
    val userId: Long,
    val name: String,
    val username: String,
    val email: String,
    val phone: String
)