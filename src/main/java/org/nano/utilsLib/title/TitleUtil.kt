package org.nano.utilsLib.title

import net.kyori.adventure.bossbar.BossBar
import net.kyori.adventure.title.Title
import org.bukkit.entity.Player
import org.nano.utilsLib.color.ColorUtil
import java.time.Duration

object TitleUtil {

    fun sendTitle(
        player: Player,
        title: String,
        subtitle: String = "",
        fadeIn: Int = 10,
        stay: Int = 70,
        fadeOut: Int = 20
    ) {
        val times = Title.Times.times(
            Duration.ofMillis(fadeIn * 50L),
            Duration.ofMillis(stay * 50L),
            Duration.ofMillis(fadeOut * 50L)
        )
        player.showTitle(Title.title(ColorUtil.format(title), ColorUtil.format(subtitle), times))
    }

    fun clearTitle(player: Player) = player.clearTitle()

    fun sendActionBar(player: Player, text: String) {
        player.sendActionBar(ColorUtil.format(text))
    }

    fun sendBossBar(
        player: Player,
        text: String,
        color: BossBar.Color = BossBar.Color.WHITE,
        overlay: BossBar.Overlay = BossBar.Overlay.PROGRESS,
        progress: Float = 1.0f
    ): BossBar {
        val bar = BossBar.bossBar(ColorUtil.format(text), progress.coerceIn(0f, 1f), color, overlay)
        player.showBossBar(bar)
        return bar
    }

    fun hideBossBar(player: Player, bossBar: BossBar) = player.hideBossBar(bossBar)
}
