package dev.dornol.ticket.admin.api.app.service.common

interface SecurityService {

    fun getUserId(): Long

    fun getCompanyId(): Long

    fun assertUserId(userId: Long)

    fun assertCompanyId(companyId: Long)

}