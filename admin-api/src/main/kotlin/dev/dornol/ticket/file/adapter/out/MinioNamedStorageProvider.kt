package dev.dornol.ticket.file.adapter.out

import dev.dornol.ticket.admin.api.config.properties.CloudS3Properties
import dev.dornol.ticket.file.application.port.out.NamedStorage
import dev.dornol.ticket.file.application.port.out.UploadFileCommand
import dev.dornol.ticket.file.domain.FileLocation
import dev.dornol.ticket.file.domain.FileMetadata
import dev.dornol.ticket.file.domain.StorageType
import org.springframework.stereotype.Component
import software.amazon.awssdk.core.sync.RequestBody
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.PutObjectRequest

@Component
class MinioNamedStorageProvider(
    private val s3Client: S3Client,
    private val s3Properties: CloudS3Properties
) : NamedStorage {
    override val type = StorageType.MINIO
    private val bucket: String = "my-bucket"

    override fun upload(command: UploadFileCommand): FileLocation {

        val key = "${command.key}_${command.name}"
        val putObjectRequest = PutObjectRequest.builder()
            .bucket(bucket)
            .key(key)
            .contentType(command.fileFormat.mimeType)
            .build()

        val inputStream = command.inputStream

        s3Client.putObject(
            putObjectRequest,
            RequestBody.fromInputStream(inputStream, inputStream.available().toLong())
        )

        return FileLocation(
            bucket = bucket,
            objectKey = key,
            storageType = type
        )
    }

    override fun resolveUri(fileMetadata: FileMetadata): String {
        return fileMetadata.location.makeFullPath(s3Properties.baseUrl)
    }

}