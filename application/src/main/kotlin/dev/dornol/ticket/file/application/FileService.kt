package dev.dornol.ticket.file.application

import dev.dornol.ticket.file.application.infra.FileMetadataIdGenerator
import dev.dornol.ticket.file.application.port.`in`.FindMetadataUseCase
import dev.dornol.ticket.file.application.port.`in`.LoadFileResourceUseCase
import dev.dornol.ticket.file.application.port.`in`.ResolveFileUriUseCase
import dev.dornol.ticket.file.domain.FileMetadata
import dev.dornol.ticket.file.domain.StorageType
import dev.dornol.ticket.file.application.port.`in`.StoreFileCommand
import dev.dornol.ticket.file.application.port.`in`.StoreFileUseCase
import dev.dornol.ticket.file.application.port.out.ExtractFileFormatPort
import dev.dornol.ticket.file.application.port.out.FindMetadataPort
import dev.dornol.ticket.file.application.port.out.FileChecksumPort
import dev.dornol.ticket.file.application.port.out.UploadFileCommand
import dev.dornol.ticket.file.application.port.out.NamedStorageResolver
import org.springframework.core.io.Resource
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class FileService(
    private val fileChecksumPort: FileChecksumPort,
    private val extractFileFormatPort: ExtractFileFormatPort,
    private val findMetadataPort: FindMetadataPort,
    private val namedStorageResolver: NamedStorageResolver,
    private val fileMetadataIdGenerator: FileMetadataIdGenerator,
    private val fileMetadataSaveService: FileMetadataSaveService,
) : StoreFileUseCase, ResolveFileUriUseCase, FindMetadataUseCase, LoadFileResourceUseCase {

    override fun storeFile(command: StoreFileCommand): FileMetadata {
        val checksum = fileChecksumPort.getChecksum(command.bytes)
        return findMetadataPort.findByChecksum(checksum) ?: let {
            val format = extractFileFormatPort.extractFileType(command.bytes.inputStream())

            val key = UUID.randomUUID()

            val storage = namedStorageResolver.resolve(StorageType.MINIO)

            val location = storage.upload(
                UploadFileCommand(
                    key = key.toString(),
                    storageType = StorageType.MINIO,
                    name = command.name,
                    bucket = command.bucket,
                    inputStream = command.bytes.inputStream(),
                    fileFormat = format
                )
            )

            FileMetadata(
                id = fileMetadataIdGenerator.generate(),
                uuid = key,
                name = command.name,
                size = command.size,
                checksum = checksum,
                format = format,
                location = location
            ).also { fileMetadataSaveService.save(it) }
        }
    }

    override fun resolveFileUri(fileMetadata: FileMetadata): String {
        val storage = namedStorageResolver.resolve(fileMetadata.location.storageType)

        return storage.resolveUri(fileMetadata)
    }

    override fun findByUuid(uuid: UUID): FileMetadata {
        return findMetadataPort.findByUuid(uuid) ?: throw IllegalArgumentException()
    }

    override fun loadResource(fileMetadata: FileMetadata): Resource {
        val storage = namedStorageResolver.resolve(fileMetadata.location.storageType)
        return storage.loadResource(fileMetadata)
    }

}