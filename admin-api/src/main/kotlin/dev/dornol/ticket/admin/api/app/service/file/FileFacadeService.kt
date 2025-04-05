package dev.dornol.ticket.admin.api.app.service.file

import dev.dornol.ticket.domain.entity.file.CommonFile
import org.springframework.stereotype.Service
import org.springframework.util.DigestUtils
import org.springframework.web.multipart.MultipartFile
import java.util.*

@Service
class FileFacadeService(
    private val fileUploadService: FileUploadService,
    private val commonFileService: CommonFileService,
    private val fileTypeProvider: FileTypeProvider
) {

    fun saveFile(file: MultipartFile): CommonFile {
        val uuid = UUID.randomUUID()

        val location = fileUploadService.uploadFile(uuid, file)
        val hash = DigestUtils.md5DigestAsHex(file.bytes)

        return commonFileService.existsByHash(hash) ?: let {
            val fileType = fileTypeProvider.extractFileType(file)

            val commonFile = CommonFile(
                uuid = uuid,
                name = file.originalFilename ?: uuid.toString(),
                location = location,
                file.size,
                hash = hash,
                fileType = fileType
            )
            return commonFileService.saveFile(commonFile)
        }
    }

}