package dev.dornol.ticket.admin.api.app.dto.common.request

import dev.dornol.ticket.admin.api.app.dto.common.SearchContext
import org.springframework.security.oauth2.jwt.Jwt

open class DefaultSearchDto<T : Enum<T>>(
    val searchFields: Set<T>,
    val searchText: String = ""
)

inline fun <reified T: DefaultSearchDto<*>> T.toContext(jwt: Jwt) =
    SearchContext(jwt.subject.toLong(), jwt.getClaimAsString("companyId").toLong(), this)