package xkr1se.xmoneyblock.utils;

import lombok.experimental.UtilityClass;
import org.bukkit.ChatColor;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xkr1se
 */
@UtilityClass
public class StringUtils {


    /**
     * Метод очень нужный и важный
     * @param str - Строка
     * @return возвращает ту же строку с изменёнными параграфами на амперсанды
     */
    public static String s(final String str) {
        return ChatColor.translateAlternateColorCodes('&', str);
    }

    /**
     * Метод который изменяет слово на плейсхолдер
     * @param lines - Стринги в коллекции
     */
    public static @NotNull List<String> replaceToPlaceholder(@NotNull Collection<String> lines, final String oldChar, final String newChar) {
        return lines.stream()
                .map(x -> x.replace(oldChar, newChar)).collect(Collectors.toList());
    }
}
