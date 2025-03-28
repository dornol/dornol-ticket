package dev.dornol.ticket.admin.api.config.message

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.context.MessageSource
import org.springframework.context.NoSuchMessageException
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.stereotype.Component

private val log = KotlinLogging.logger {}

@Component
class MessageResolver(
    private val messageSource: MessageSource
) {

    fun getMessage(key: String, vararg args: Any): String =
        messageSource.getMessage(key, args, key, LocaleContextHolder.getLocale())
            ?: let {
                log.warn { "No message found for key $key" }
                key
            }

    fun getMessage(key: String, args: Collection<Any>): String =
        messageSource.getMessage(key, args.toTypedArray(), key, LocaleContextHolder.getLocale())
            ?: let {
                log.warn { "No message found for key $key" }
                key
            }

    fun getMessage(codes: Array<out String>?, args: Array<out Any>?): String? {
        val locale = LocaleContextHolder.getLocale()
        if (codes != null) {
            for (code in codes) {
                try {
                    return messageSource.getMessage(code, args, locale)
                } catch (e: NoSuchMessageException) {
                    log.trace { "No message found for code $code" }
                }
            }
        }
        return null
    }
}