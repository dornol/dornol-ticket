package dev.dornol.ticket.admin.api.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "dornol.properties.cloud.s3")
data class CloudS3Properties(
    val endpoint: String,
    val accessKey: String,
    val secretKey: String,
)