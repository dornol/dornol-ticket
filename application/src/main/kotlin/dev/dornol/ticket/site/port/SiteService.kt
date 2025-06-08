package dev.dornol.ticket.site.port

import dev.dornol.ticket.common.application.out.CurrentUserPort
import dev.dornol.ticket.common.exception.BadRequestException
import dev.dornol.ticket.common.search.PageResult
import dev.dornol.ticket.manager.domain.CompanyId
import dev.dornol.ticket.site.domain.Site
import dev.dornol.ticket.site.domain.value.Address
import dev.dornol.ticket.site.infra.SiteIdGenerator
import dev.dornol.ticket.site.port.`in`.EditSiteCommand
import dev.dornol.ticket.site.port.`in`.EditSiteUseCase
import dev.dornol.ticket.site.port.`in`.FindSiteUseCase
import dev.dornol.ticket.site.port.`in`.SaveSiteCommand
import dev.dornol.ticket.site.port.`in`.SaveSiteUseCase
import dev.dornol.ticket.site.port.`in`.SearchSitesCommand
import dev.dornol.ticket.site.port.`in`.SearchSitesUseCase
import dev.dornol.ticket.site.port.`in`.dto.SiteDto
import dev.dornol.ticket.site.port.`in`.dto.SiteListDto
import dev.dornol.ticket.site.port.`in`.mapper.toAddressDto
import dev.dornol.ticket.site.port.out.EditSitePort
import dev.dornol.ticket.site.port.out.FindSitePort
import dev.dornol.ticket.site.port.out.SaveSitePort
import dev.dornol.ticket.site.port.out.SearchSitesCriteria
import dev.dornol.ticket.site.port.out.SearchSitesPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service("siteService2")
internal open class SiteService(
    private val searchSitesPort: SearchSitesPort,
    private val findSitePort: FindSitePort,
    private val currentUserPort: CurrentUserPort,
    private val saveSitePort: SaveSitePort,
    private val editSitePort: EditSitePort,

    private val siteIdGenerator: SiteIdGenerator
) : SearchSitesUseCase, FindSiteUseCase, SaveSiteUseCase, EditSiteUseCase {

    @Transactional(readOnly = true)
    override fun searchSites(command: SearchSitesCommand): PageResult<SiteListDto> {
        return searchSitesPort.searchSites(SearchSitesCriteria(
            searchKeys = command.searchKeys,
            searchText = command.searchText,
            pageQuery = command.pageQuery,
            companyId = currentUserPort.getCurrentUserCompanyId()
        ))
    }

    @Transactional(readOnly = true)
    override fun findById(id: Long): SiteDto {
        val site = findSitePort.findById(id) ?: throw BadRequestException()
        currentUserPort.matchCompanyId(site.companyId.get())

        return SiteDto(
            id = site.id.get(),
            name = site.name,
            address = site.address.toAddressDto(),
            seatingMapFileUuid = site.seatingMapFileUuid
        )
    }

    @Transactional
    override fun save(command: SaveSiteCommand) {
        val companyId = currentUserPort.getCurrentUserCompanyId()

        val site = Site(
            id = siteIdGenerator.generate(),
            name = command.name,
            address = Address(
                zipCode = command.address.zipCode,
                mainAddress = command.address.mainAddress,
                detailAddress = command.address.detailAddress
            ),
            seatingMapFileUuid = command.seatingMapFileUuid,
            companyId = CompanyId(companyId)
        )

        saveSitePort.save(site)
    }

    @Transactional
    override fun edit(id: Long, command: EditSiteCommand) {
        val site = findSitePort.findById(id) ?: throw BadRequestException()
        currentUserPort.matchCompanyId(site.companyId.get())

        site.edit(
            name = command.name,
            address = Address(
                zipCode = command.address.zipCode,
                mainAddress = command.address.mainAddress,
                detailAddress = command.address.detailAddress
            ),
            seatingMapFileUuid = command.seatingMapFileUuid
        )

        editSitePort.edit(site)
    }
}