package dev.dornol.ticket.admin.api.config.serializer

import com.fasterxml.jackson.databind.module.SimpleModule
import dev.dornol.ticket.common.domain.id.AbstractId

class AbstractIdJacksonModule : SimpleModule() {

    init {
        addSerializer(AbstractId::class.java, AbstractIdSerializer())
    }

}