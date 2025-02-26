package dev.dornol.ticket.domain.generator

import io.github.oshai.kotlinlogging.KotlinLogging
import org.hibernate.engine.spi.SharedSessionContractImplementor
import org.hibernate.id.IdentifierGenerator
import org.springframework.stereotype.Component
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.concurrent.locks.ReentrantLock

private val log = KotlinLogging.logger {}

@Component
final class SnowFlakeIdGenerator(
    private val nodeId: Long = 0L,
) : IdentifierGenerator {

    companion object {
        val epoch: Long = LocalDateTime.of(2024, 1, 1, 0, 0)
            .atZone(ZoneId.of("Asia/Seoul"))
            .toInstant()
            .toEpochMilli()
        const val NODE_ID_BITS: Int = 10
        const val SEQUENCE_BITS: Int = 12
        const val EPOCH_BITS: Int = 64 - 1 - NODE_ID_BITS - SEQUENCE_BITS
        const val MAX_NODE_ID = (1L shl NODE_ID_BITS) - 1
        const val MAX_SEQUENCE = (1L shl SEQUENCE_BITS) - 1
        const val MAX_EPOCH = (1L shl EPOCH_BITS) - 1
    }

    private val lock = ReentrantLock()
    private var sequence = 0L
    private var lastTimestamp = -1L

    init {
        require(nodeId in 0..MAX_NODE_ID) { "nodeId must be between 0 and $MAX_NODE_ID" }
    }

    private fun generateId(): Long {

        lock.lock()

        try {
            var timestamp = currentTimestamp()

            require(timestamp >= lastTimestamp) { "failed to generate id. Clock moved backwards." }
            require(timestamp <= MAX_EPOCH) { "failed to generate id" }

            if (timestamp == lastTimestamp) {
                sequence = (sequence + 1) and MAX_SEQUENCE
                if (sequence == 0L) {
                    while (timestamp == lastTimestamp) {
                        Thread.sleep(1)
                        timestamp = currentTimestamp()
                    }
                }
            } else {
                sequence = 0L
            }

            lastTimestamp = timestamp

            return ((timestamp - epoch) shl (NODE_ID_BITS + SEQUENCE_BITS)) or
                    (nodeId shl SEQUENCE_BITS) or
                    sequence
        } finally {
            lock.unlock()
        }
    }

    private fun currentTimestamp(): Long = Instant.now().toEpochMilli()

    override fun generate(p0: SharedSessionContractImplementor?, p1: Any?): Any {
        return this.generateId()
    }
}