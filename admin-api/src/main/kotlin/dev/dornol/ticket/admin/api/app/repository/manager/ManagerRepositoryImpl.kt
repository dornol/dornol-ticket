package dev.dornol.ticket.admin.api.app.repository.manager

import com.querydsl.core.types.Order
import com.querydsl.core.types.OrderSpecifier
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import dev.dornol.ticket.admin.api.app.dto.manager.ManagerListDto
import dev.dornol.ticket.admin.api.app.dto.manager.ManagerSearchDto
import dev.dornol.ticket.admin.api.app.dto.manager.ManagerSearchType
import dev.dornol.ticket.admin.api.app.dto.manager.QManagerListDto
import dev.dornol.ticket.domain.entity.company.QCompany.company
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

    override fun searchManagers(search: ManagerSearchDto, pageable: Pageable): Page<ManagerListDto> {
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
            .where(
                approved(search.approved),
                text(search.searchType, search.searchText)
            )
            .orderBy(*sort(pageable.sort))
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())

        val countQuery = query
            .select(manager.count())
            .from(manager)
            .where(
                approved(search.approved),
                text(search.searchType, search.searchText)
            )

        return PageableExecutionUtils.getPage(listQuery.fetch(), pageable) { countQuery.fetchOne() ?: 0 }
    }

    private fun approved(approved: Boolean?): BooleanExpression? {
        return approved?.let { manager.approval.approved.eq(it) }
    }

    private fun text(searchType: ManagerSearchType?, searchText: String?) =
        searchText?.takeIf { StringUtils.hasText(it) }?.let {
            when (searchType) {
                ManagerSearchType.NAME -> manager.name.contains(searchText)
                ManagerSearchType.EMAIL -> manager.email.contains(searchText)
                ManagerSearchType.PHONE -> manager.phone.contains(searchText)
                ManagerSearchType.USERNAME -> manager.username.contains(searchText)
                ManagerSearchType.COMPANY_NAME -> manager.company.name.contains(searchText)
                else -> manager.name.contains(searchText)
                    .or(manager.email.contains(searchText))
                    .or(manager.phone.contains(searchText))
                    .or(manager.username.contains(searchText))
                    .or(manager.company.name.contains(searchText))
            }
        }

    private fun sort(sort: Sort) = sort.map {
        log.info { "direction: ${it.direction.name} ${it.direction.isAscending}" }
        when (it.property) {
            "name" -> OrderSpecifier(order(it.direction.isAscending), manager.name)
            "username" -> OrderSpecifier(order(it.direction.isAscending), manager.username)
            else -> OrderSpecifier(order(it.direction.isAscending), manager.name)
        }
    }.toList().toTypedArray()

    private fun order(isAscending: Boolean): Order {
        if (isAscending) {
            return Order.ASC
        }
        return Order.DESC
    }

}