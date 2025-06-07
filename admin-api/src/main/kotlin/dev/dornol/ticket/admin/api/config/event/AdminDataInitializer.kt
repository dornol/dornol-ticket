package dev.dornol.ticket.admin.api.config.event

import dev.dornol.ticket.admin.api.app.repository.company.CompanyRepository
import dev.dornol.ticket.admin.api.app.repository.manager.ManagerRepository
import dev.dornol.ticket.manager.adapter.out.jpa.CompanyEntity
import dev.dornol.ticket.manager.adapter.out.jpa.ManagerApprovalEntity
import dev.dornol.ticket.manager.adapter.out.jpa.ManagerEntity
import dev.dornol.ticket.manager.application.infra.CompanyIdGenerator
import dev.dornol.ticket.manager.application.infra.ManagerIdGenerator
import dev.dornol.ticket.manager.domain.ManagerRole
import org.springframework.boot.context.event.ApplicationStartedEvent
import org.springframework.context.ApplicationListener
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class AdminDataInitializer(
    private val managerRepository: ManagerRepository,
    private val companyRepository: CompanyRepository,
    private val passwordEncoder: PasswordEncoder,
    private val managerIdGenerator: ManagerIdGenerator,
    private val companyIdGenerator: CompanyIdGenerator,
) : ApplicationListener<ApplicationStartedEvent> {

    @Transactional
    override fun onApplicationEvent(event: ApplicationStartedEvent) {
        val initialDataExists = managerRepository.existsByEmail("dhkim@dornol.dev")
        if (!initialDataExists) {
            val company = CompanyEntity(
                companyIdGenerator.generate().get(),
                "돌놀컴퍼니",
                "0000000000"
            )
            companyRepository.save(company)
            managerRepository.save(ManagerEntity(
                id = managerIdGenerator.generate().get(),
                username = "dhkim",
                password = passwordEncoder.encode("1234"),
                name = "김동혁",
                phone = "01012341234",
                email = "dhkim@dornol.dev",
                role = ManagerRole.SYSTEM_ADMIN,
                approval = ManagerApprovalEntity(true),
                company = company
            ))
        }
    }
}