package dev.dornol.ticket.domain.constraint

class RegExp {

    companion object {
        const val USERNAME_REGEXP = "^[a-zA-Z][a-zA-Z0-9]{4,15}$"
        const val MANAGER_NAME_REGEXP = "^[가-힣]{2,10}$"
        const val PHONE_NUMBER_REGEXP = "(^02.{0}|^01.{1}|[0-9]{3})([0-9]+)([0-9]{4})"
    }

}