package dev.dornol.ticket.manager.application

import dev.dornol.ticket.common.exception.BadRequestException
import dev.dornol.ticket.manager.application.port.`in`.FindManagerUseCase
import dev.dornol.ticket.manager.application.port.`in`.dto.ManagerDto
import dev.dornol.ticket.manager.application.port.out.FindManagerByIdPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
open class ManagerService(
    private val findManagerByIdPort: FindManagerByIdPort,
) : FindManagerUseCase {

    @Transactional(readOnly = true)
    override fun findManagerById(id: Long): ManagerDto {
        val manager = findManagerByIdPort.findManagerById(id) ?: throw BadRequestException()

        return ManagerDto(
            id = manager.id.get(),
            username = manager.username,
            name = manager.name,
            email = manager.email,
            phone = manager.phone,
        )
    }

}
