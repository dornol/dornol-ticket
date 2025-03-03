package dev.dornol.ticket.admin.api.security

import org.springframework.boot.context.properties.ConfigurationProperties
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey

@ConfigurationProperties(prefix = "dornol.security.jwt.rsa")
class RsaKeyProperties(
    val publicKey: RSAPublicKey,
    val privateKey: RSAPrivateKey
) {
}