package dev.dornol.ticket.admin.api.config.redis

import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories

@EnableRedisRepositories(basePackages = ["dev.dornol.ticket.admin.api.app.redisrepository"])
@Configuration
class RedisConfig {
}