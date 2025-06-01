package dev.dornol.ticket.file.domain

data class FileLocation(
    val bucket: String,
    val objectKey: String,
    val storageType: StorageType
) {

    fun makeFullPath(baseUrl: String) = "$baseUrl/$bucket/$objectKey"

}
