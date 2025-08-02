package org.nano.utilsLib.item.container

import org.bukkit.NamespacedKey
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.persistence.PersistentDataContainer
import org.bukkit.persistence.PersistentDataType
import org.bukkit.plugin.java.JavaPlugin
import org.nano.utilsLib.UtilsLib

@Suppress("UNCHECKED_CAST")
abstract class ItemContainer<T : ItemContainer<T>> {
    val plugin: JavaPlugin = UtilsLib.getPlugin()
    protected lateinit var meta: ItemMeta
    protected val persist: PersistentDataContainer
        get() = meta.persistentDataContainer

    fun setData(key: String, value: String): T {
        val keyRef = NamespacedKey(plugin, key)
        persist.set(keyRef, PersistentDataType.STRING, value)
        return this as T
    }

    fun removeData(key: String): T {
        val keyRef = NamespacedKey(plugin, key)
        persist.remove(keyRef)
        return this as T
    }

    fun initData(): T {
        persist.keys.forEach { key ->
            persist.remove(key)
        }
        return this as T
    }

    fun complete(): T {
        return this as T
    }


}