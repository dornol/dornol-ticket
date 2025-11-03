package dev.dornol.ticket.common.util

import org.springframework.context.MessageSource
import org.springframework.stereotype.Component

@Component
class MessageUtil(
    private val messageSource: MessageSource,
    private val localeUtil: LocaleUtil
) {

    fun getMessage(code: String): String {
        return messageSource.getMessage(code, null, code, localeUtil.getLocale())!!
    }

    fun getMessage(code: String, vararg args: Any): String {
        return messageSource.getMessage(code, args, code, localeUtil.getLocale())!!
    }

}