package org.nano.utilsLib.scheduler

import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitTask

object SchedulerUtil {

    fun runLater(plugin: JavaPlugin, delayTicks: Long, task: Runnable): BukkitTask =
        plugin.server.scheduler.runTaskLater(plugin, task, delayTicks)

    fun runTimer(plugin: JavaPlugin, delayTicks: Long, periodTicks: Long, task: Runnable): BukkitTask =
        plugin.server.scheduler.runTaskTimer(plugin, task, delayTicks, periodTicks)

    fun runAsync(plugin: JavaPlugin, task: Runnable): BukkitTask =
        plugin.server.scheduler.runTaskAsynchronously(plugin, task)

    fun runAsyncLater(plugin: JavaPlugin, delayTicks: Long, task: Runnable): BukkitTask =
        plugin.server.scheduler.runTaskLaterAsynchronously(plugin, task, delayTicks)

    fun runAsyncTimer(plugin: JavaPlugin, delayTicks: Long, periodTicks: Long, task: Runnable): BukkitTask =
        plugin.server.scheduler.runTaskTimerAsynchronously(plugin, task, delayTicks, periodTicks)

    fun cancel(task: BukkitTask) = task.cancel()

    fun cancel(plugin: JavaPlugin) = plugin.server.scheduler.cancelTasks(plugin)
}
