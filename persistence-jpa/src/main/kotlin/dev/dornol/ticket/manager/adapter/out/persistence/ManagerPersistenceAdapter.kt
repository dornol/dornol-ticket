package dev.dornol.ticket.manager.adapter.out.persistence

import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.core.types.dsl.StringPath
import com.querydsl.jpa.impl.JPAQueryFactory
import dev.dornol.ticket.common.*
import dev.dornol.ticket.common.search.PageMeta
import dev.dornol.ticket.common.search.PageResult
import dev.dornol.ticket.manager.application.port.out.CompanyDto
import dev.dornol.ticket.manager.application.port.out.ManagerListDto
import dev.dornol.ticket.manager.application.port.out.SearchManagersCriteria
import dev.dornol.ticket.manager.application.port.out.SearchManagersPort
import dev.dornol.ticket.manager.domain.CompanyId
import dev.dornol.ticket.manager.domain.ManagerId
import dev.dornol.ticket.manager.domain.ManagerRole
import dev.dornol.ticket.manager.domain.model.search.ManagerSearchField
import dev.dornol.ticket.manager.domain.value.ManagerApproval
import org.springframework.data.domain.Sort
import org.springframework.data.support.PageableExecutionUtils
import org.springframework.stereotype.Repository
import dev.dornol.ticket.manager.adapter.out.jpa.QCompanyEntity.companyEntity as company
import dev.dornol.ticket.manager.adapter.out.jpa.QManagerEntity.managerEntity as manager

@Repository
class ManagerPersistenceAdapter(
    private val query: JPAQueryFactory
) : SearchManagersPort {

    val mapper: Map<ManagerSearchField, List<StringPath>> = mapOf(
        ManagerSearchField.NAME to listOf(manager.name),
        ManagerSearchField.USERNAME to listOf(manager.username),
        ManagerSearchField.EMAIL to listOf(manager.email),
        ManagerSearchField.PHONE to listOf(manager.phone),
        ManagerSearchField.COMPANY_NAME to listOf(manager.company.name),
    )

    override fun searchManagers(criteria: SearchManagersCriteria): PageResult<ManagerListDto> {
        val pageable = criteria.pageQuery.toPageable()
        val condition = managerListCondition(criteria)

        val listQuery = query
            .select(
                QManagerListProjection(
                    manager.id,
                    manager.username,
                    manager.name,
                    manager.phone,
                    manager.email,
                    manager.managerRole,
                    manager.approval,
                    QCompanyProjection(
                        company.id,
                        company.name,
                        company.businessNumber
                    )
                )
            )
            .from(manager)
            .leftJoin(manager.company, company)
            .where(*condition)
            .orderBy(*sort(pageable.sort), manager.id.desc())
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())

        val countQuery = query
            .select(manager.count())
            .from(manager)
            .where(*condition)

        val page = PageableExecutionUtils.getPage(listQuery.fetch(), pageable) { countQuery.fetchOne() ?: 0 }
        return PageResult(
            content = page.content.map { ManagerListDto(
                id = ManagerId(it.id),
                username = it.username,
                email = it.email,
                phone = it.phone,
                name = it.name,
                managerRole = it.managerRole,
                approval = ManagerApproval(
                    approved = it.approval.approved,
                    approvedAt = it.approval.approvedAt,
                    approvedBy = it.approval.approvedBy?.let { by -> ManagerId(by) }
                ),
                company = CompanyDto(
                    id = CompanyId(it.company.id),
                    name = it.company.name,
                    businessNumber = it.company.businessNumber,
                )
            ) },
            page = PageMeta(
                size = page.size,
                number = page.number,
                totalElements = page.totalElements,
                totalPages = page.totalPages
            )
        )


    }

    private fun managerListCondition(criteria: SearchManagersCriteria) = arrayOf(
        approved(criteria.approved) and manager.deleted.isFalse and role(criteria.managerRole),
        criteria.textSearch(mapper),
    )

    private fun approved(approved: Boolean?): BooleanExpression? {
        return approved?.let { manager.approval.approved.eq(it) }
    }

    private fun role(role: ManagerRole?): BooleanExpression? {
        return role?.let { manager.managerRole.eq(it) }
    }

    private fun sort(sort: Sort) = sort.toOrderBy {
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