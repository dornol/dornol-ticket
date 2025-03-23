package dev.dornol.ticket.admin.api.app.dto.user

data class UserWithAuthDto(
    val user: UserDto,
    val authorities: Collection<String>
)