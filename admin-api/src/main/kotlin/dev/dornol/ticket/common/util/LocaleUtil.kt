package dev.dornol.ticket.common.util

import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.stereotype.Component
import java.util.Locale

@Component
class LocaleUtil {

    fun getLocale(): Locale = LocaleContextHolder.getLocale()

    fun getServerLocale(): Locale = Locale.getDefault()

}