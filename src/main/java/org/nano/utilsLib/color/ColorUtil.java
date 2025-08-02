package org.nano.utilsLib.color;

import java.util.ArrayList;
import java.util.List;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;

public class ColorUtil {

	private static final String REGEX = "(?=(<hex:#.*?>|&[a-fA-F0-9]|§[a-fA-F0-9]|&[lnmok]|§[lnmok]|&r|§r))";

	public static List<Component> format(List<String> list) {
		return list.stream()
			.map(ColorUtil::format)
			.toList();
	}

	public static Component format(String str) {

		List<StringColor> list = new ArrayList<>();
		Component text = Component.text("").decoration(TextDecoration.ITALIC,false);
		if (str == null)
			return text;

		String detected = "<hex:";

		String[] sections = str.split(REGEX);
		String currentColor = "#FFFFFF"; // 기본 색상
		List<TextDecoration> decorations = new ArrayList<>();

		for (String section : sections) {
			if (section.isEmpty())
				continue;

			if (section.startsWith(detected)) {
				currentColor = section.substring(detected.length(), section.indexOf(">"));
				section = section.substring(section.indexOf(">") + 1);
			}

			while (section.startsWith("&") || section.startsWith("§")) {
				char code = section.charAt(1);
				switch (code) {
					case '0':
						currentColor = "#000000";
						break;
					case '1':
						currentColor = "#0000AA";
						break;
					case '2':
						currentColor = "#00AA00";
						break;
					case '3':
						currentColor = "#00AAAA";
						break;
					case '4':
						currentColor = "#AA0000";
						break;
					case '5':
						currentColor = "#AA00AA";
						break;
					case '6':
						currentColor = "#FFAA00";
						break;
					case '7':
						currentColor = "#AAAAAA";
						break;
					case '8':
						currentColor = "#555555";
						break;
					case '9':
						currentColor = "#5555FF";
						break;
					case 'a':
						currentColor = "#55FF55";
						break;
					case 'b':
						currentColor = "#55FFFF";
						break;
					case 'c':
						currentColor = "#FF5555";
						break;
					case 'd':
						currentColor = "#FF55FF";
						break;
					case 'e':
						currentColor = "#FFFF55";
						break;
					case 'f':
						currentColor = "#FFFFFF";
						break;
					case 'l':
						decorations.add(TextDecoration.BOLD);
						break;
					case 'o':
						decorations.add(TextDecoration.ITALIC);
						break;
					case 'n':
						decorations.add(TextDecoration.UNDERLINED);
						break;
					case 'm':
						decorations.add(TextDecoration.STRIKETHROUGH);
						break;
					case 'k':
						decorations.add(TextDecoration.OBFUSCATED);
						break;
					case 'r':
						currentColor = "#FFFFFF";
						decorations.clear();
						break;
					default:
						break;
				}
				section = section.substring(2);
			}
			if (!section.isEmpty()) {
				list.add(new StringColor(section, currentColor, new ArrayList<>(decorations)));
			}
		}
		for (StringColor add : list) {
			text = text.append(add.build());
		}
		return text;
	}
}
