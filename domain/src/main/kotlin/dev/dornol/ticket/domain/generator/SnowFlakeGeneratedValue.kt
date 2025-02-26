package dev.dornol.ticket.domain.generator

import org.hibernate.annotations.IdGeneratorType

@IdGeneratorType(SnowFlakeIdGenerator::class)
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class SnowFlakeGeneratedValue(
    val name: String = "SnowFlakeGenerator",
)