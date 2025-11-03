package dev.dornol.ticket.admin.api.config.serializer

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import dev.dornol.ticket.common.domain.id.AbstractId

class AbstractIdSerializer : JsonSerializer<AbstractId>() {
    override fun serialize(
        value: AbstractId?,
        gen: JsonGenerator,
        serializers: SerializerProvider
    ) {
        gen.writeString(value?.get().toString())
    }
}