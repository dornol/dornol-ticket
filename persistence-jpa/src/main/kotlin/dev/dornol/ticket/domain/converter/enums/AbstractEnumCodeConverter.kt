package dev.dornol.ticket.domain.converter.enums

import dev.dornol.ticket.common.domain.BaseEnum
import jakarta.persistence.AttributeConverter

abstract class AbstractEnumCodeConverter<E, DB>(
    private val enumClass: Class<E>,
) : AttributeConverter<E, DB> where E : Enum<E>, E : BaseEnum<DB> {

    override fun convertToDatabaseColumn(attribute: E?): DB? =
        attribute?.code

    override fun convertToEntityAttribute(dbData: DB?): E? =
        dbData?.let {
            enumClass.enumConstants.first { it.code == dbData }
        }
}