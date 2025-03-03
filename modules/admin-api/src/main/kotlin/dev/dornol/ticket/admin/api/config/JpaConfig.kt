package dev.dornol.ticket.admin.api.config

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@Configuration
@EntityScan(basePackages = ["dev.dornol.ticket"])
@EnableJpaAuditing
class JpaConfig {
}