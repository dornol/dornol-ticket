package dev.dornol.ticket.admin.api.app.service.manager

import dev.dornol.ticket.admin.api.app.dto.manager.request.JoinRequestDto
import dev.dornol.ticket.admin.api.app.dto.manager.request.ManagerSearchDto
import dev.dornol.ticket.admin.api.app.dto.user.UserDto
import dev.dornol.ticket.admin.api.app.repository.company.CompanyRepository
import dev.dornol.ticket.admin.api.app.repository.manager.ManagerRepository
import dev.dornol.ticket.admin.api.config.exception.common.BadRequestException
import dev.dornol.ticket.admin.api.config.exception.join.UsernameExistsException
import dev.dornol.ticket.domain.entity.company.Company
import dev.dornol.ticket.domain.entity.manager.Manager
import dev.dornol.ticket.domain.entity.manager.ManagerRole
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class ManagerService(
    private val managerRepository: ManagerRepository,
    private val companyRepository: CompanyRepository,
    private val passwordEncoder: PasswordEncoder
) {

    @Transactional
    fun join(joinDto: JoinRequestDto) {
        if (existsUsername(joinDto.username)) {
            throw UsernameExistsException(joinDto.username)
        }
        val company = Company(joinDto.company.name, joinDto.company.businessNumber)
        val manager = Manager(
            password = passwordEncoder.encode(joinDto.password),
            username = joinDto.username,
            name = joinDto.name,
            email = joinDto.email,
            phone = joinDto.phone,
            company = company,
            role = ManagerRole.BUSINESS_ADMIN,
        )

        companyRepository.save(company)
        managerRepository.save(manager)
    }

    @Transactional(readOnly = true)
    fun existsUsername(username: String): Boolean {
        val manager = managerRepository.findByUsername(username)
        return manager != null
    }

    @Transactional(readOnly = true)
    fun getUserDataById(id: Long) = managerRepository.findByIdOrNull(id)
        ?.let { UserDto(it.id, it.name, it.username, it.email, it.phone) }
        ?: throw BadRequestException()

    @Transactional(readOnly = true)
    fun searchManagers(search: ManagerSearchDto, page: Pageable) = managerRepository.searchManagers(search, page)

    @Transactional
    fun approveManager(id: Long, approvedBy: Long) {
        managerRepository.findByIdOrNull(id)?.takeIf { !it.deleted }?.apply { approve(approvedBy, LocalDateTime.now()) }
    }

}