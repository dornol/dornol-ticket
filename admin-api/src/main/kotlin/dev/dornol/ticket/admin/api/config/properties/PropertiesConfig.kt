package dev.dornol.ticket.admin.api.config.properties

import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration

@EnableConfigurationProperties(CloudS3Properties::class)
@Configuration
class PropertiesConfig {
}