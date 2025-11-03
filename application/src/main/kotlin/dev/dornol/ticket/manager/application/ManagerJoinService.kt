package dev.dornol.ticket.manager.application

import dev.dornol.ticket.manager.application.exception.UsernameExistsException
import dev.dornol.ticket.manager.application.infra.CompanyIdGenerator
import dev.dornol.ticket.manager.application.infra.ManagerIdGenerator
import dev.dornol.ticket.manager.application.port.`in`.CheckUsernameExistsUseCase
import dev.dornol.ticket.manager.application.port.`in`.ManagerJoinCommand
import dev.dornol.ticket.manager.application.port.`in`.ManagerJoinUseCase
import dev.dornol.ticket.manager.application.port.out.FindManagerByUsernamePort
import dev.dornol.ticket.manager.application.port.out.PasswordEncodePort
import dev.dornol.ticket.manager.application.port.out.SaveManagerPort
import dev.dornol.ticket.manager.domain.Company
import dev.dornol.ticket.manager.domain.Manager
import dev.dornol.ticket.manager.domain.ManagerRole
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
internal open class ManagerJoinService(
    private val findManagerByUsernamePort: FindManagerByUsernamePort,
    private val passwordEncodePort: PasswordEncodePort,

    private val saveManagerPort: SaveManagerPort,

    private val companyIdGenerator: CompanyIdGenerator,
    private val managerIdGenerator: ManagerIdGenerator,
) : CheckUsernameExistsUseCase, ManagerJoinUseCase {

    @Transactional(readOnly = true)
    override fun exists(username: String): Boolean {
        val manager = findManagerByUsernamePort.findByUsername(username)
        return manager != null
    }

    @Transactional
    override fun join(command: ManagerJoinCommand) {
        if (this.exists(command.username)) {
            throw UsernameExistsException(command.username)
        }
        val company = Company(
            id = companyIdGenerator.generate(),
            name = command.companyName,
            businessNumber = command.businessNumber,
        )
        val manager = Manager(
            id = managerIdGenerator.generate(),
            password = passwordEncodePort.encode(command.password),
            username = command.username,
            name = command.name,
            email = command.email,
            phone = command.phone,
            companyId = company.id,
            role = ManagerRole.BUSINESS_ADMIN,
        )

        saveManagerPort.save(manager, company)
    }
}