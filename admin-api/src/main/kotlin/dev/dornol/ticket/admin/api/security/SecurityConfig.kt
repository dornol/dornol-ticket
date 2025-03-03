package dev.dornol.ticket.admin.api.security

import com.fasterxml.jackson.databind.ObjectMapper
import dev.dornol.ticket.admin.api.security.filter.JsonUsernamePasswordAuthenticationFilter
import dev.dornol.ticket.domain.entity.manager.AccessRole
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod.*
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.core.authorization.OAuth2AuthorizationManagers.hasScope
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
class SecurityConfig(
    private val accessDeniedHandler: AccessDeniedHandler,
    private val authenticationEntryPoint: AuthenticationEntryPoint,
    private val authenticationSuccessHandler: AuthenticationSuccessHandler,
    private val authenticationFailureHandler: AuthenticationFailureHandler,
    private val objectMapper: ObjectMapper,

    @Value("\${dornol.security.cors.allowed-origins:}")
    private val allowedOrigins: Array<String>
) {
    companion object {
        const val LOGIN_URL = "/auth/authenticate"
        const val LOGOUT_URL = "/auth/logout"
    }

    @Bean
    fun securityFilterChain(
        http: HttpSecurity,
        jsonUsernamePasswordAuthenticationFilter: JsonUsernamePasswordAuthenticationFilter
    ): SecurityFilterChain = http.run {
        csrf { it.disable() }
        headers {
            it.contentSecurityPolicy { csp -> csp.policyDirectives("script-src 'self'") }
            it.cacheControl { cache -> cache.disable() }
            it.referrerPolicy { referrer -> referrer.policy(ReferrerPolicyHeaderWriter.ReferrerPolicy.SAME_ORIGIN) }
        }
        cors { it.configurationSource(corsConfigurationSource()) }
        authorizeHttpRequests {
            it.requestMatchers(LOGIN_URL).permitAll()
            it.requestMatchers(LOGOUT_URL).permitAll()
            it.requestMatchers("/sites/**").access(hasScope(AccessRole.SYSTEM.name))

            it.anyRequest().permitAll()
        }
        exceptionHandling {
            it.accessDeniedHandler(accessDeniedHandler)
            it.authenticationEntryPoint(authenticationEntryPoint)
        }
        sessionManagement {
            it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        }
        oauth2ResourceServer {
            it.jwt { }
        }
        addFilterAt(jsonUsernamePasswordAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
        build()
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val config = CorsConfiguration()
        config.allowCredentials = true
        config.allowedOrigins = allowedOrigins.toMutableList()
        config.allowedMethods = mutableListOf(GET, POST, PUT, PATCH, DELETE, OPTIONS, HEAD).map { it.name() }
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", config)
        return source
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

    @Bean
    fun authenticationManager(authenticationConfiguration: AuthenticationConfiguration): AuthenticationManager {
        return authenticationConfiguration.authenticationManager
    }

    @Bean
    fun jsonUsernamePasswordAuthenticationFilter(authenticationManager: AuthenticationManager): JsonUsernamePasswordAuthenticationFilter {
        val filter = JsonUsernamePasswordAuthenticationFilter(
            authenticationManager = authenticationManager,
            objectMapper = objectMapper
        )
        filter.setAuthenticationSuccessHandler(authenticationSuccessHandler)
        filter.setAuthenticationFailureHandler(authenticationFailureHandler)
        filter.setFilterProcessesUrl(LOGIN_URL)
        return filter
    }

}