package org.nano.utilsLib.color

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.format.TextDecoration

data class StringColor(val content: String, val color: String, val decorations: List<TextDecoration>) {
    fun build(): Component {
        var comp = Component.text(content)
        comp = comp.color(TextColor.fromHexString(color))
        decorations.forEach { comp = comp.decorate(it) }
        return comp;
    }
}
