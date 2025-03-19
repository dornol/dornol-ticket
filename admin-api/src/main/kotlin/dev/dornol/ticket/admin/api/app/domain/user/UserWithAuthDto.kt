package dev.dornol.ticket.admin.api.app.domain.user

import dev.dornol.ticket.admin.api.app.dto.user.UserDto

data class UserWithAuthDto(
    val user: UserDto,
    val authorities: Collection<String>
)