package dev.dornol.ticket.validation.file

import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER,
    AnnotationTarget.FIELD,
    AnnotationTarget.VALUE_PARAMETER
)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [FileValidator::class, FileCollectionValidator::class, FileArrayValidator::class])
@MustBeDocumented
annotation class File(
    val message: String = "Invalid file",
    vararg val value: SafeMediaType = [],
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = [],
    val size: Long = 0
)
