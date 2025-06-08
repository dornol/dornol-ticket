package dev.dornol.ticket.site.port.`in`

interface EditSiteUseCase {

    fun edit(id: Long, command: EditSiteCommand)

}