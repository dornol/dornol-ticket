package dev.dornol.ticket.admin.api.config.jpa

import dev.dornol.ticket.domain.generator.SnowFlakeIdGenerator
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@Configuration
@EntityScan(basePackages = ["dev.dornol.ticket"])
@EnableJpaAuditing
class JpaConfig(
    @Value("\${node-id}")
    private val nodeId: Long
) {

    @PostConstruct
    fun initSnowFlakeGenerator() {
        SnowFlakeIdGenerator.initialize(nodeId)
    }

}