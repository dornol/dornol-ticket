package dev.dornol.ticket.admin.api.app.service.file

import dev.dornol.ticket.admin.api.app.repository.file.CommonFileRepository
import dev.dornol.ticket.domain.entity.file.CommonFile
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommonFileService(
    private val commonFileRepository: CommonFileRepository
) {

    @Transactional
    fun saveFile(file: CommonFile): CommonFile {
        return commonFileRepository.save(file)
    }

    @Transactional(readOnly = true)
    fun existsByHash(hash: String): CommonFile? = commonFileRepository.findByHash(hash)

}