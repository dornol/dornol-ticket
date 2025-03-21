package dev.dornol.ticket.domain.constant

const val USERNAME_PATTERN = "^[a-zA-Z0-9]{4,16}$"
const val MANAGER_NAME_PATTERN = "^[가-힣]{2,10}$"
const val PHONE_NUMBER_PATTERN = "(^02.{0}|^01.{1}|[0-9]{3})([0-9]+)([0-9]{4})"

const val BUSINESS_NAME_PATTERN = "^[a-zA-Z0-9가-힣\\s]{2,20}$"
const val BUSINESS_NUMBER_PATTERN = "^[0-9]{10}$"