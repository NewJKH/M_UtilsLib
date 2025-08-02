package org.nano.utilsLib.message

import net.kyori.adventure.text.Component
import org.nano.utilsLib.color.ColorUtil

class MessageBuilder {

    private var message: StringBuilder = StringBuilder()
    private var list = mutableListOf<String>()

    fun strOfPrefix(prefix: String): MessageBuilder {
        message.insert(0, prefix)
        return this
    }

    fun strOfAdd(text: String): MessageBuilder {
        message.append(text)
        return this
    }

    fun listOfAdd(line: Int, text: String ): MessageBuilder {
        list[line] = text
        return this
    }

    fun build(): Component {
        return ColorUtil.format(message.toString())
    }
    fun build2(): List<Component> {
        return ColorUtil.format(list)
    }
}