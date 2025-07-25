package dev.dornol.ticket.manager.application.exception

import dev.dornol.ticket.common.exception.BadRequestException
import dev.dornol.ticket.common.exception.ExceptionCode

class UsernameExistsException(
    username: String
) : BadRequestException(ExceptionCode.JOIN_USERNAME_EXISTS, username)