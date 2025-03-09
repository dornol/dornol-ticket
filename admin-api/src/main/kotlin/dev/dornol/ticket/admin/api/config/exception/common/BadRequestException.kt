package dev.dornol.ticket.admin.api.config.exception.common

import dev.dornol.ticket.admin.api.config.exception.BaseException
import org.springframework.http.HttpStatus

class BadRequestException : BaseException(HttpStatus.BAD_REQUEST, "errors.bad-request")