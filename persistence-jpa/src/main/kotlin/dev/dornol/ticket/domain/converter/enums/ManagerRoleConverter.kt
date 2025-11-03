package dev.dornol.ticket.domain.converter.enums

import dev.dornol.ticket.manager.domain.ManagerRole
import jakarta.persistence.Converter

@Converter(autoApply = false)
class ManagerRoleConverter : AbstractEnumCodeConverter<ManagerRole, Int>(ManagerRole::class.java)