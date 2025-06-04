package dev.dornol.ticket.manager.adapter.out

import dev.dornol.ticket.manager.application.port.out.PasswordEncodePort
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class PasswordEncodeService(
    private val passwordEncoder: PasswordEncoder
) : PasswordEncodePort {

    override fun encode(password: String): String = passwordEncoder.encode(password)

}