package dev.dornol.ticket.admin.api.app.repository.manager

import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.core.types.dsl.StringPath
import com.querydsl.jpa.impl.JPAQueryFactory
import dev.dornol.ticket.admin.api.app.dto.manager.request.ManagerSearchDto
import dev.dornol.ticket.admin.api.app.dto.manager.request.ManagerSearchField
import dev.dornol.ticket.admin.api.app.dto.manager.response.ManagerListDto
import dev.dornol.ticket.admin.api.app.dto.manager.response.QManagerListDto
import dev.dornol.ticket.admin.api.util.and
import dev.dornol.ticket.admin.api.util.sort
import dev.dornol.ticket.admin.api.util.textSearch
import dev.dornol.ticket.admin.api.util.toOrderBy
import dev.dornol.ticket.domain.entity.company.QCompany.company
import dev.dornol.ticket.domain.entity.manager.ManagerRole
import dev.dornol.ticket.domain.entity.manager.QManager.manager
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.support.PageableExecutionUtils
import org.springframework.stereotype.Repository
import org.springframework.util.StringUtils

private val log = KotlinLogging.logger {}

@Repository
class ManagerRepositoryImpl(
    private val query: JPAQueryFactory
) : ManagerQueryRepository {

    val mapper: Map<ManagerSearchField, List<StringPath>> = mapOf(
        ManagerSearchField.NAME to listOf(manager.name),
        ManagerSearchField.USERNAME to listOf(manager.username),
        ManagerSearchField.EMAIL to listOf(manager.email),
        ManagerSearchField.PHONE to listOf(manager.phone),
        ManagerSearchField.COMPANY_NAME to listOf(manager.company.name),
    )

    override fun searchManagers(search: ManagerSearchDto, pageable: Pageable): Page<ManagerListDto> {
        val condition = managerListCondition(search)

        val listQuery = query
            .select(
                QManagerListDto(
                    manager.id,
                    manager.username,
                    manager.name,
                    manager.phone,
                    manager.email,
                    manager.managerRole,
                    manager.approval,
                    company
                )
            )
            .from(manager)
            .leftJoin(manager.company, company)
            .where(*condition)
            .orderBy(*sort(pageable.sort))
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())

        val countQuery = query
            .select(manager.count())
            .from(manager)
            .where(*condition)

        return PageableExecutionUtils.getPage(listQuery.fetch(), pageable) { countQuery.fetchOne() ?: 0 }
    }

    private fun managerListCondition(search: ManagerSearchDto) = arrayOf(
        approved(search.approved) and manager.deleted.isFalse and role(search.managerRole),
        search.textSearch(mapper),
        text(search.searchFields, search.searchText)
    )

    private fun approved(approved: Boolean?): BooleanExpression? {
        return approved?.let { manager.approval.approved.eq(it) }
    }

    private fun text(searchFields: Set<ManagerSearchField>?, searchText: String?): BooleanExpression? =
        searchText?.takeIf { StringUtils.hasText(it) }?.let {
            val flatten = searchFields?.map { type ->
                when (type) {
                    ManagerSearchField.NAME -> listOf(manager.name)
                    ManagerSearchField.EMAIL -> listOf(manager.email)
                    ManagerSearchField.PHONE -> listOf(manager.phone)
                    ManagerSearchField.USERNAME -> listOf(manager.username)
                    ManagerSearchField.COMPANY_NAME -> listOf(manager.company.name)
                }
            }?.flatten()
            var expression: BooleanExpression? = null
            flatten?.forEach { path -> expression = if (expression == null) path.contains(searchText) else expression!!.or(path.contains(searchText)) }
            expression
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
            else -> manager.id.sort(it)
        }
    }

}