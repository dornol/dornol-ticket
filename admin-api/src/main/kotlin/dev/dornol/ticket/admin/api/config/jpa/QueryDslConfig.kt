package dev.dornol.ticket.admin.api.config.jpa

import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class QueryDslConfig {

    @Bean
    fun queryClient(entityManager: EntityManager) = JPAQueryFactory(entityManager)

}