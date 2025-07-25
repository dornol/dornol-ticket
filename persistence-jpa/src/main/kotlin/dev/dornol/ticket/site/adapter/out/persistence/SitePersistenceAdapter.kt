package dev.dornol.ticket.site.adapter.out.persistence

import dev.dornol.ticket.common.alive
import dev.dornol.ticket.common.search.PageResult
import dev.dornol.ticket.common.toPageResult
import dev.dornol.ticket.file.adapter.out.persistence.FileMetadataEntityRepository
import dev.dornol.ticket.manager.adapter.out.persistence.CompanyEntityRepository
import dev.dornol.ticket.site.adapter.out.jpa.mapper.toDomain
import dev.dornol.ticket.site.adapter.out.jpa.mapper.toEntity
import dev.dornol.ticket.site.adapter.out.persistence.mapper.toSiteListDto
import dev.dornol.ticket.site.adapter.out.persistence.query.SiteQueryDslSupport
import dev.dornol.ticket.site.domain.Site
import dev.dornol.ticket.site.port.`in`.dto.SiteListDto
import dev.dornol.ticket.site.port.out.EditSitePort
import dev.dornol.ticket.site.port.out.FindSitePort
import dev.dornol.ticket.site.port.out.SaveSitePort
import dev.dornol.ticket.site.port.out.SearchSitesCriteria
import dev.dornol.ticket.site.port.out.SearchSitesPort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import java.lang.IllegalStateException

@Repository
class SitePersistenceAdapter(
    private val siteEntityRepository: SiteEntityRepository,
    private val siteQueryDslSupport: SiteQueryDslSupport,

    private val companyEntityRepository: CompanyEntityRepository,
    private val fileMetadataEntityRepository: FileMetadataEntityRepository,
) : SearchSitesPort, FindSitePort, SaveSitePort, EditSitePort {

    override fun searchSites(criteria: SearchSitesCriteria): PageResult<SiteListDto> {
        val page = siteQueryDslSupport.search(criteria)
        return page.toPageResult { it.toSiteListDto() }
    }

    override fun findById(id: Long): Site? {
        return siteEntityRepository.findWithFileByIdOrNull(id)?.alive()?.toDomain()
    }

    override fun save(site: Site) {
        val companyEntity = companyEntityRepository.findByIdOrNull(site.companyId.get()) ?: throw IllegalStateException()
        val seatingMapFileEntity = fileMetadataEntityRepository.findByUuid(site.seatingMapFileUuid) ?: throw IllegalStateException()

        siteEntityRepository.save(site.toEntity(companyEntity, seatingMapFileEntity))
    }

    override fun edit(site: Site) {
        val siteEntity = siteEntityRepository.findByIdOrNull(site.id.get()) ?: throw IllegalStateException()
        val seatingMapFileEntity = fileMetadataEntityRepository.findByUuid(site.seatingMapFileUuid)
        siteEntity.edit(
            name = site.name,
            seatingMapFile = seatingMapFileEntity,
            address = site.address.toEntity()
        )

        siteEntityRepository.save(siteEntity)
    }

}