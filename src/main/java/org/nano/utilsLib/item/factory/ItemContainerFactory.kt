package org.nano.utilsLib.item.factory

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import net.kyori.adventure.sound.Sound
import org.bukkit.NamespacedKey
import org.bukkit.Registry
import org.bukkit.SoundCategory
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.persistence.PersistentDataContainer
import org.bukkit.persistence.PersistentDataType
import org.bukkit.plugin.java.JavaPlugin
import org.nano.utilsLib.UtilsLib

object ItemContainerFactory {

    lateinit var itemStack: ItemStack
    lateinit var itemMeta: ItemMeta

    val plugin: JavaPlugin = UtilsLib.getPlugin()
    val persist: PersistentDataContainer
        get() = itemMeta.persistentDataContainer

    fun register(itemStack: ItemStack): ItemContainerFactory {
        this.itemStack = itemStack
        this.itemMeta = itemStack.itemMeta
        return this
    }

    fun getData(key: String): String {
        val keyRef = NamespacedKey(plugin, key)
        val value: String? = persist.get(keyRef, PersistentDataType.STRING)
        return value.toString()
    }

    fun getSound(): Sound? {
        val keyRef = NamespacedKey(plugin, "sound")
        val json = persist.get(keyRef, PersistentDataType.STRING) ?: return null

        val map = Gson().fromJson(json, object : TypeToken<Map<String, Any>>() {}.type) as Map<String, Any>

        val key = map["key"] as? String ?: error("")
        val category = SoundCategory.valueOf((map["category"] as String).uppercase())
        val volume = (map["volume"] as Number).toFloat()
        val pitch = (map["pitch"] as Number).toFloat()

        return Sound.sound(
            Registry.SOUNDS.getOrThrow(NamespacedKey.minecraft(key)),
            category,
            volume,
            pitch
        )
    }
}