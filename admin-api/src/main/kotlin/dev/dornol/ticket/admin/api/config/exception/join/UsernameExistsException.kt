package dev.dornol.ticket.admin.api.config.exception.join

import dev.dornol.ticket.admin.api.config.exception.BaseException
import org.springframework.http.HttpStatus

class UsernameExistsException(
    username: String
) : BaseException(HttpStatus.BAD_REQUEST, "errors.join.username-exists", username) {
}