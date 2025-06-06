package dev.dornol.ticket.domain.entity

import dev.dornol.ticket.domain.generator.SnowFlakeGeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import org.hibernate.Hibernate

@MappedSuperclass
abstract class BaseIdEntity(
    @Id
    @SnowFlakeGeneratedValue
    val id: Long? = null
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null) return false
        // 프록시 클래스까지 포함해서 클래스 비교
        if (Hibernate.getClass(this) != Hibernate.getClass(other)) return false

        other as BaseIdEntity

        // ID가 아직 할당되지 않았다면 동등하지 않다고 판단
        if (id == null || other.id == null) return false

        return id == other.id
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: super.hashCode()
    }

}