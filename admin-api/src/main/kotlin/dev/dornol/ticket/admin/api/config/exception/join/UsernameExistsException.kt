package dev.dornol.ticket.admin.api.config.exception.join

import dev.dornol.ticket.admin.api.config.exception.common.BadRequestException
import dev.dornol.ticket.common.exception.BaseException
import dev.dornol.ticket.common.exception.ExceptionCode
import org.springframework.http.HttpStatus

class UsernameExistsException(
    username: String
) : BadRequestException(ExceptionCode.JOIN_USERNAME_EXISTS, username)