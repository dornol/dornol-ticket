package dev.dornol.ticket.admin.api.config

import dev.dornol.ticket.admin.api.config.security.RsaKeyProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(RsaKeyProperties::class)
class PropertyConfig {
}