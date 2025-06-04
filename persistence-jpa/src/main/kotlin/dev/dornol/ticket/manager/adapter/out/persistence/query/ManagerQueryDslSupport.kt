package dev.dornol.ticket.manager.adapter.out.persistence.query

import com.querydsl.jpa.impl.JPAQueryFactory
import dev.dornol.ticket.common.toPageable
import dev.dornol.ticket.manager.adapter.out.persistence.ManagerListProjection
import dev.dornol.ticket.manager.adapter.out.persistence.QCompanyProjection
import dev.dornol.ticket.manager.adapter.out.persistence.QManagerListProjection
import dev.dornol.ticket.manager.application.port.out.SearchManagersCriteria
import org.springframework.data.domain.Page
import org.springframework.data.support.PageableExecutionUtils
import org.springframework.stereotype.Component
import dev.dornol.ticket.manager.adapter.out.jpa.QCompanyEntity.companyEntity as company
import dev.dornol.ticket.manager.adapter.out.jpa.QManagerEntity.managerEntity as manager

@Component
class ManagerQueryDslSupport(
    private val query: JPAQueryFactory
) {

    fun searchManagers(criteria: SearchManagersCriteria): Page<ManagerListProjection> {
        val pageable = criteria.pageQuery.toPageable()
        val condition = ManagerPredicates.build(criteria)

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
            .orderBy(*ManagerPredicates.sort(pageable.sort), manager.id.desc())
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())

        val countQuery = query
            .select(manager.count())
            .from(manager)
            .where(*condition)

        return PageableExecutionUtils.getPage(listQuery.fetch(), pageable) { countQuery.fetchOne() ?: 0 }
    }



}