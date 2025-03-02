package dev.dornol.ticket.admin.api.security.filter

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.MediaType
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

class JsonUsernamePasswordAuthenticationFilter(
    authenticationManager: AuthenticationManager,
    private val objectMapper: ObjectMapper,
    private val usernamePropertyName: String = "username",
    private val passwordPropertyName: String = "password"
) : UsernamePasswordAuthenticationFilter(authenticationManager) {

    private val mapTypeReference: TypeReference<Map<String, String>> = object : TypeReference<Map<String, String>>() {}

    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {

        if (!request.contentType.startsWith(MediaType.APPLICATION_JSON_VALUE)) {
            return super.attemptAuthentication(request, response)
        }

        val requestBody: Map<String, String> = objectMapper.readValue(request.inputStream, mapTypeReference)

        val username = obtainUsername(requestBody)
        val password = obtainPassword(requestBody)

        val authRequest = UsernamePasswordAuthenticationToken.unauthenticated(username, password)
        setDetails(request, authRequest)
        return this.authenticationManager.authenticate(authRequest)
    }

    private fun obtainUsername(requestBody: Map<String, String>): String {
        return requestBody[usernamePropertyName]?.trim() ?: ""
    }

    private fun obtainPassword(requestBody: Map<String, String>): String {
        return requestBody[passwordPropertyName]?.trim() ?: ""
    }

}