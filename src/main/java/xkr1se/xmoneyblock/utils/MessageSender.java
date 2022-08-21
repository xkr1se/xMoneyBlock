package xkr1se.xmoneyblock.utils;

import lombok.experimental.UtilityClass;
import lombok.val;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.stream.Collectors;

import static xkr1se.xmoneyblock.utils.StringUtils.s;

/**
 * @author xkr1se
 */
@UtilityClass
public class MessageSender {

    /**
     * Метод который отправляет сообщение всем игрокам на сервере
     * @param message - Сообщение
     */
    public static void sendMessageAll(final String message) {
        Bukkit.getOnlinePlayers().forEach(x -> x.sendMessage(s(message)));
    }

    /**
     * Метод который отправляет все сообщения из коллекции всем игрокам на сервере
     * @param messages - Сообщения в коллекции
     */
    public static void sendMessageAll(@NotNull Collection<String> messages) {
        val messagesReplaceCodes = messages.stream()
                .map(StringUtils::s).collect(Collectors.toList());

        Bukkit.getOnlinePlayers().forEach(player -> messagesReplaceCodes
                .forEach(line -> player.sendMessage(line))); // какого хуя player::sendMessage ошибку выдаёт...
    }

    /**
     * Метод который отправляет все сообщения из коллекции игроку
     * @param messages - Сообщения в коллекции
     */
    public static void sendMessage(@NotNull Player player, @NotNull Collection<String> messages) {
        messages.forEach(player::sendMessage);
    }

    /**
     * Метод который отправляет все сообщения из коллекции отправителю команды
     * @param messages - Сообщения в коллекции
     */
    public static void sendMessage(@NotNull CommandSender sender, @NotNull Collection<String> messages) {
        messages.forEach(sender::sendMessage);
    }

}
