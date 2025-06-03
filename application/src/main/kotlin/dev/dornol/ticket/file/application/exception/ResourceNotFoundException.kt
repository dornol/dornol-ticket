package dev.dornol.ticket.file.application.exception

import dev.dornol.ticket.common.exception.BaseException
import dev.dornol.ticket.common.exception.ExceptionCode

class ResourceNotFoundException : BaseException(ExceptionCode.RESOURCE_NOT_FOUND)