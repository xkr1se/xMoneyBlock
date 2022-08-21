package xkr1se.xmoneyblock.utils;

import lombok.experimental.UtilityClass;

/**
 * @author xkr1se
 */
@UtilityClass
public class TimeUtils {

    /**
     * Метод для форматирования секунд в читабельный вид
     * @param seconds - Секунды
     * @return возвращает строку с временем
     */
    public static String timeFormat(int seconds) {
        return String.format("%02d дн. %02d ч. %02d мин. %02d сек.", seconds / 86400, seconds / 3600, seconds / 60 % 3600, seconds % 60);
    }

}
