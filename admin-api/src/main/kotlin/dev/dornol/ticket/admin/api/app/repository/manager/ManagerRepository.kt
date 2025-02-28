package dev.dornol.ticket.admin.api.app.repository.manager

import dev.dornol.ticket.domain.entity.manager.Manager
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface ManagerRepository : JpaRepository<Manager, Long> {

    @Query("""
        select m.id
        from Manager m
        where m.username = :username
    """)
    fun findIdByUsername(@Param("username") username: String): Long?

}