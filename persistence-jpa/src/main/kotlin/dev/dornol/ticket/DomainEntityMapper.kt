package dev.dornol.ticket

interface DomainEntityMapper<D, E> {
    fun toEntity(domain: D): E
    fun toDomain(entity: E): D
}