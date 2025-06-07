package dev.dornol.ticket.manager.adapter.out.persistence.query

import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.core.types.dsl.StringPath
import dev.dornol.ticket.common.and
import dev.dornol.ticket.common.sort
import dev.dornol.ticket.common.textSearch
import dev.dornol.ticket.common.toOrderBy
import dev.dornol.ticket.manager.application.port.out.SearchManagersCriteria
import dev.dornol.ticket.manager.domain.ManagerRole
import dev.dornol.ticket.manager.domain.model.search.ManagerSearchField
import org.springframework.data.domain.Sort
import dev.dornol.ticket.manager.adapter.out.jpa.QCompanyEntity.companyEntity as company
import dev.dornol.ticket.manager.adapter.out.jpa.QManagerEntity.managerEntity as manager

object ManagerPredicates {

    val mapper: Map<ManagerSearchField, List<StringPath>> = mapOf(
        ManagerSearchField.NAME to listOf(manager.name),
        ManagerSearchField.USERNAME to listOf(manager.username),
        ManagerSearchField.EMAIL to listOf(manager.email),
        ManagerSearchField.PHONE to listOf(manager.phone),
        ManagerSearchField.COMPANY_NAME to listOf(manager.company.name),
    )

    fun build(criteria: SearchManagersCriteria) = arrayOf(
        approved(criteria.approved) and manager.deleted.isFalse and role(criteria.managerRole),
        criteria.textSearch(mapper),
    )

    fun approved(approved: Boolean?): BooleanExpression? {
        return approved?.let { manager.approval.approved.eq(it) }
    }

    fun role(role: ManagerRole?): BooleanExpression? {
        return role?.let { manager.role.eq(it) }
    }

    fun sort(sort: Sort) = sort.toOrderBy {
        when (it.property) {
            "username" -> manager.username.sort(it)
            "name" -> manager.name.sort(it)
            "email" -> manager.email.sort(it)
            "businessName" -> company.name.sort(it)
            "businessNumber" -> company.businessNumber.sort(it)
            else -> null
        }
    }

}