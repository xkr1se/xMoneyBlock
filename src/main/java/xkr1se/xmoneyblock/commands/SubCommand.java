package xkr1se.xmoneyblock.commands;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @author xkr1se
 */
public interface SubCommand {

    /**
     * Метод который выполняет действия после ввода подкоманды
     * @param sender - Отправитель команды
     * @param args - Аргументы команды
     * @return возвращает статус выполнения
     */
    boolean onCommand(CommandSender sender, String[] args);

    /**
     * @return возвращает алиасы команды
     */
    @Nullable List<String> aliases();

    /**
     * @return возвращает право на использование подкоманды
     */
    @Nullable String permission();

    /**
     * @return возвращает название подкоманды
     */
    @NotNull String command();

    /**
     * @return возвращает правильное использование команды
     */
    @NotNull String description();

    /**
     *
     * @return возвращает использование команды от лица игрока или нет
     */
    boolean onlyPlayer();

    /**
     * @return возвращает максимальное количество аргументов после подкоманды
     */
    int argsMax();

}
