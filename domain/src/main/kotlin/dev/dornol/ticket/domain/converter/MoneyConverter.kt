package dev.dornol.ticket.domain.converter

import dev.dornol.ticket.domain.entity.common.Money
import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter
import java.math.BigDecimal

@Converter(autoApply = true)
class MoneyConverter : AttributeConverter<Money, BigDecimal> {
    override fun convertToDatabaseColumn(attribute: Money?): BigDecimal? {
        return attribute?.amount
    }

    override fun convertToEntityAttribute(dbData: BigDecimal?): Money? {
        return dbData?.let { Money(it) }
    }
}