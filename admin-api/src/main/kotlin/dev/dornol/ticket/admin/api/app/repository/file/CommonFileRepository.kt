package dev.dornol.ticket.admin.api.app.repository.file

import dev.dornol.ticket.domain.entity.file.CommonFile
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CommonFileRepository : JpaRepository<CommonFile, Long> {

    fun findByHash(hash: String): CommonFile?

}