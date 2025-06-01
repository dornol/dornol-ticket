package dev.dornol.ticket.common.domain.id

import io.github.oshai.kotlinlogging.KotlinLogging
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.concurrent.locks.ReentrantLock

private val log = KotlinLogging.logger {}

class SnowFlakeIdGenerator : IdGenerator {
    companion object {
        private var nodeId: Long? = null

        private val lock = ReentrantLock()
        private var sequence = 0L
        private var lastTimestamp = -1L

        val epoch: Long = LocalDateTime.of(2025, 1, 1, 0, 0)
            .atZone(ZoneId.of("Asia/Seoul"))
            .toInstant()
            .toEpochMilli()
        const val NODE_ID_BITS: Int = 10
        const val SEQUENCE_BITS: Int = 12
        const val EPOCH_BITS: Int = 64 - 1 - NODE_ID_BITS - SEQUENCE_BITS
        const val MAX_NODE_ID = (1L shl NODE_ID_BITS) - 1
        const val MAX_SEQUENCE = (1L shl SEQUENCE_BITS) - 1
        const val MAX_EPOCH = (1L shl EPOCH_BITS) - 1

        fun initialize(nodeId: Long) {
            require(nodeId in 0..MAX_NODE_ID) { "nodeId must be between 0 and $MAX_NODE_ID" }
            this.nodeId = nodeId
        }

        private fun generateId(): Long {

            if (nodeId == null) {
                throw IllegalArgumentException("nodeId must be set")
            }

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
                        (nodeId!! shl SEQUENCE_BITS) or
                        sequence
            } finally {
                lock.unlock()
            }
        }

        private fun currentTimestamp(): Long = Instant.now().toEpochMilli()

    }

    override fun generate() = generateId()
}