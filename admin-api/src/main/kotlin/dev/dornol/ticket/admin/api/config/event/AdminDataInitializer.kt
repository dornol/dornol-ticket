package dev.dornol.ticket.admin.api.config.event

import dev.dornol.ticket.admin.api.app.repository.company.CompanyRepository
import dev.dornol.ticket.admin.api.app.repository.manager.ManagerRepository
import dev.dornol.ticket.domain.entity.company.Company
import dev.dornol.ticket.domain.entity.manager.Manager
import dev.dornol.ticket.domain.entity.manager.ManagerApproval
import dev.dornol.ticket.domain.entity.manager.ManagerRole
import org.springframework.boot.context.event.ApplicationStartedEvent
import org.springframework.context.ApplicationListener
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class AdminDataInitializer(
    private val managerRepository: ManagerRepository,
    private val companyRepository: CompanyRepository,
    private val passwordEncoder: PasswordEncoder
) : ApplicationListener<ApplicationStartedEvent> {

    @Transactional
    override fun onApplicationEvent(event: ApplicationStartedEvent) {
        val initialDataExists = managerRepository.existsByEmail("dhkim@dornol.dev")
        if (!initialDataExists) {
            val company = Company(
                "돌놀컴퍼니",
                "0000000000"
            )
            companyRepository.save(company)
            managerRepository.save(Manager(
                username = "dhkim",
                password = passwordEncoder.encode("1234"),
                name = "김동혁",
                phone = "01012341234",
                email = "dhkim@dornol.dev",
                role = ManagerRole.SYSTEM_ADMIN,
                approval = ManagerApproval(true),
                company = company
            ))
        }
    }
}