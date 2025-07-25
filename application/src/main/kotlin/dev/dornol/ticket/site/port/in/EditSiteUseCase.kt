package dev.dornol.ticket.site.port.`in`

import dev.dornol.ticket.site.port.`in`.command.EditSiteCommand

interface EditSiteUseCase {

    fun edit(id: Long, command: EditSiteCommand)

}