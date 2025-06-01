package dev.dornol.ticket.admin.api.app.service.site

import dev.dornol.ticket.admin.api.app.dto.common.SearchContext
import dev.dornol.ticket.admin.api.app.dto.site.request.SiteAddRequestDto
import dev.dornol.ticket.admin.api.app.dto.site.request.SiteEditRequestDto
import dev.dornol.ticket.admin.api.app.dto.site.request.SiteSearchDto
import dev.dornol.ticket.admin.api.app.dto.site.response.AddressDto
import dev.dornol.ticket.admin.api.app.dto.site.response.SiteDto
import dev.dornol.ticket.admin.api.app.dto.site.response.SiteListDto
import dev.dornol.ticket.admin.api.app.repository.file.CommonFileRepository
import dev.dornol.ticket.admin.api.app.repository.manager.ManagerRepository
import dev.dornol.ticket.admin.api.app.repository.site.SiteRepository
import dev.dornol.ticket.admin.api.config.exception.common.BadRequestException
import dev.dornol.ticket.domain.entity.site.SiteEntity
import dev.dornol.ticket.file.adapter.out.jpa.mapper.FileMetadataMapper
import dev.dornol.ticket.file.application.port.`in`.ResolveFileUriUseCase
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SiteService(
    private val siteRepository: SiteRepository,
    private val managerRepository: ManagerRepository,
    private val commonFileRepository: CommonFileRepository,
    private val resolveFileUriUseCase: ResolveFileUriUseCase,
    private val fileMetadataMapper: FileMetadataMapper
) {

    @Transactional(readOnly = false)
    fun search(search: SearchContext<SiteSearchDto>, pageable: Pageable): Page<SiteListDto> {
        return siteRepository.search(search, pageable)
    }

    @Transactional(readOnly = true)
    fun get(userId: Long, id: Long): SiteDto {
        val manager = managerRepository.findByIdOrNull(userId) ?: throw BadRequestException()
        return siteRepository.findWithSeatingMapById(id)?.takeIf { it.company.id == manager.company.id }
            ?.let {
                SiteDto(
                    it.id!!,
                    it.name,
                    AddressDto(it.address.zipCode, it.address.mainAddress, it.address.detailAddress),
                    resolveFileUriUseCase.resolveFileUri(fileMetadataMapper.toDomain(it.seatingMapFile))
                )
            }
            ?: throw BadRequestException()
    }

    @Transactional
    fun save(userId: Long, site: SiteAddRequestDto): SiteEntity {
        val manager = managerRepository.findByIdOrNull(userId) ?: throw BadRequestException()
        val seatingMapFile = commonFileRepository.findByIdOrNull(site.seatingMapFileId) ?: throw BadRequestException()
        return SiteEntity(site.name, site.address.toEntity(), manager.company, seatingMapFile).also { siteRepository.save(it) }
    }

    @Transactional
    fun edit(userId: Long, id: Long, newSite: SiteEditRequestDto): SiteEntity {
        val manager = managerRepository.findByIdOrNull(userId) ?: throw BadRequestException()
        val seatingMapFile = newSite.seatingMapFileId?.let { commonFileRepository.findByIdOrNull(it) }
        val site = siteRepository.findByIdOrNull(id)?.takeIf { it.company.id == manager.company.id }
            ?: throw BadRequestException()

        return site.also { it.edit(newSite.name, newSite.address.toEntity(), seatingMapFile) }
    }

}