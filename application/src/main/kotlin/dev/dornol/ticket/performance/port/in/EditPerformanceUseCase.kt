package dev.dornol.ticket.performance.port.`in`

interface EditPerformanceUseCase {

    fun edit(id: Long, command: EditPerformanceCommand)

}