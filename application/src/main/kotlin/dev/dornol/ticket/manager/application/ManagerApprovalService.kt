package dev.dornol.ticket.manager.application

import dev.dornol.ticket.common.application.out.CurrentUserPort
import dev.dornol.ticket.file.application.exception.ResourceNotFoundException
import dev.dornol.ticket.manager.application.port.`in`.ApproveManagerUseCase
import dev.dornol.ticket.manager.application.port.out.ApproveManagerPort
import dev.dornol.ticket.manager.application.port.out.FindManagerByIdPort
import dev.dornol.ticket.manager.domain.ManagerId
import org.springframework.stereotype.Service

@Service
internal class ManagerApprovalService(
    private val findManagerByIdPort: FindManagerByIdPort,
    private val approveManagerPort: ApproveManagerPort,
    private val currentUserPort: CurrentUserPort,
) : ApproveManagerUseCase {
    override fun approveManager(id: Long) {
        val manager = findManagerByIdPort.findManagerById(id) ?: throw ResourceNotFoundException()

        manager.approve(ManagerId(currentUserPort.getCurrentUserId()))

        approveManagerPort.approveManager(manager)
    }
}