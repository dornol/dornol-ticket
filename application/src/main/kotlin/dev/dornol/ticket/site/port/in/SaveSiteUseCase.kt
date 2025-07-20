package dev.dornol.ticket.site.port.`in`

import dev.dornol.ticket.site.port.`in`.command.SaveSiteCommand

interface SaveSiteUseCase {

    fun save(command: SaveSiteCommand)

}