package dev.dornol.ticket.admin.api.config

import dev.dornol.ticket.admin.api.config.serializer.AbstractIdJacksonModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JacksonConfig {

    @Bean
    fun abstractIdModule() = AbstractIdJacksonModule()

}