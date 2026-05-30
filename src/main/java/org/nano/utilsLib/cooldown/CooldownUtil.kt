package org.nano.utilsLib.cooldown

import java.util.UUID
import java.util.concurrent.ConcurrentHashMap

object CooldownUtil {

    private val cooldowns = ConcurrentHashMap<UUID, ConcurrentHashMap<String, Long>>()

    fun set(uuid: UUID, key: String, durationMs: Long) {
        cooldowns.getOrPut(uuid) { ConcurrentHashMap() }[key] = System.currentTimeMillis() + durationMs
    }

    fun remaining(uuid: UUID, key: String): Long {
        val expiry = cooldowns[uuid]?.get(key) ?: return 0L
        return maxOf(0L, expiry - System.currentTimeMillis())
    }

    fun isOnCooldown(uuid: UUID, key: String): Boolean = remaining(uuid, key) > 0L

    fun clear(uuid: UUID, key: String) {
        cooldowns[uuid]?.remove(key)
    }

    fun clearAll(uuid: UUID) {
        cooldowns.remove(uuid)
    }
}
