package org.nano.utilsLib.packet

import com.comphenix.protocol.PacketType
import com.comphenix.protocol.ProtocolLibrary
import com.comphenix.protocol.events.PacketAdapter
import com.comphenix.protocol.events.PacketContainer
import com.comphenix.protocol.events.PacketEvent
import org.bukkit.Location
import org.bukkit.block.data.BlockData
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

object PacketUtil {

    private val manager get() = ProtocolLibrary.getProtocolManager()

    fun sendFakeBlock(player: Player, location: Location, blockData: BlockData) {
        player.sendBlockChange(location, blockData)
    }

    fun resetFakeBlock(player: Player, location: Location) {
        player.sendBlockChange(location, location.block.blockData)
    }

    fun createPacket(type: PacketType): PacketContainer = manager.createPacket(type)

    fun sendPacket(player: Player, packet: PacketContainer) {
        manager.sendServerPacket(player, packet)
    }

    fun addListener(
        plugin: JavaPlugin,
        vararg types: PacketType,
        onReceive: ((PacketEvent) -> Unit)? = null,
        onSend: ((PacketEvent) -> Unit)? = null
    ) {
        manager.addPacketListener(object : PacketAdapter(plugin, *types) {
            override fun onPacketReceiving(event: PacketEvent) {
                onReceive?.invoke(event)
            }
            override fun onPacketSending(event: PacketEvent) {
                onSend?.invoke(event)
            }
        })
    }

    fun removeAllListeners(plugin: JavaPlugin) {
        manager.removePacketListeners(plugin)
    }
}
