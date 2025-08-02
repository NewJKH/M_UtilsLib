package org.nano.utilsLib.player

import org.bukkit.Bukkit
import org.bukkit.entity.Player

object PlayerUtil {
    fun getByPlayer(playername: String): Player {
        return Bukkit.getOnlinePlayers().stream()
            .filter { it.name.equals(playername, ignoreCase = true) }
            .findFirst()
            .orElseThrow { IllegalStateException("존재하는 플레이어가 없습니다.") }
    }
}
