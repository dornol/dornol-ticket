package dev.dornol.ticket.admin.api.app.service.manager

import dev.dornol.ticket.admin.api.app.dto.manager.JoinRequestDto
import dev.dornol.ticket.admin.api.app.dto.user.UserDto
import dev.dornol.ticket.admin.api.app.repository.manager.ManagerRepository
import dev.dornol.ticket.admin.api.config.exception.common.BadRequestException
import dev.dornol.ticket.admin.api.config.exception.join.UsernameExistsException
import dev.dornol.ticket.domain.entity.manager.AccessRole
import dev.dornol.ticket.domain.entity.manager.Manager
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ManagerService(
    private val managerRepository: ManagerRepository,
    private val passwordEncoder: PasswordEncoder
) {

    @Transactional
    fun join(joinDto: JoinRequestDto) {
        if (existsUsername(joinDto.username)) {
            throw UsernameExistsException(joinDto.username)
        }
        val manager = Manager(
            password = passwordEncoder.encode(joinDto.password),
            username = joinDto.username,
            name = joinDto.name,
            email = joinDto.email,
            phone = joinDto.phone,
            role = AccessRole.MANAGER
        )
        managerRepository.save(manager)
    }

    @Transactional(readOnly = true)
    fun existsUsername(username: String): Boolean {
        val manager = managerRepository.findByUsername(username)
        return manager != null
    }

    @Transactional(readOnly = true)
    fun getUserDataByUsername(username: String) = managerRepository.findByUsername(username)
        ?.let { UserDto(it.id, it.name, it.username, it.email, it.phone) }
        ?: throw BadRequestException()

}