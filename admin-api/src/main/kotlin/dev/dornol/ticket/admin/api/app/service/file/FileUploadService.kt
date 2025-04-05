package dev.dornol.ticket.admin.api.app.service.file

import dev.dornol.ticket.admin.api.config.properties.CloudS3Properties
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import software.amazon.awssdk.core.sync.RequestBody
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.PutObjectRequest
import java.util.*

@Service
class FileUploadService(
    private val s3Client: S3Client,
    private val s3Properties: CloudS3Properties
) {
    private val bucket: String = "my-bucket"

    fun uploadFile(uuid: UUID, multipartFile: MultipartFile): String {
        val key = "${uuid}_${multipartFile.originalFilename}"
        val putObjectRequest = PutObjectRequest.builder()
            .bucket(bucket)
            .key(key)
            .contentType(multipartFile.contentType)
            .build()

        val inputStream = multipartFile.inputStream
        s3Client.putObject(
            putObjectRequest,
            RequestBody.fromInputStream(inputStream, inputStream.available().toLong())
        )

        return "${s3Properties.endpoint}/$bucket/$key"
    }

}