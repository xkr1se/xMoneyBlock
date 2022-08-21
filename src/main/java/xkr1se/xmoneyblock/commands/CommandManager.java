package xkr1se.xmoneyblock.commands;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xkr1se
 */
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public abstract class CommandManager implements CommandExecutor {

    List<SubCommand> subCommands = new ArrayList<>();


    @Override
    abstract public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args);

    /**
     * Метод возвращает класс команды
     * @param command - Команда
     * @return возвращает реализацию интерфейса {@link SubCommand}
     */
    public @Nullable SubCommand getSubCommandWithString(final String command) {
        return subCommands.stream()
                .filter(x -> x.command().equalsIgnoreCase(command)
                        || x.aliases() != null
                        && x.aliases().stream().anyMatch(command::equalsIgnoreCase))
                .findFirst()
                .orElse(null);
    }

    /**
     * Метод регистрируещий подкоманду
     * @param subCommand - Реализация интерфейса {@link SubCommand}
     */
    public void registerSub(@NotNull SubCommand subCommand) {
        subCommands.add(subCommand);
    }

    /**
     * Метод регистрируещий подкоманду
     * @param subCommand - Реализация интерфейса {@link SubCommand}
     */
    public void unregisterSub(@NotNull SubCommand subCommand) {
        subCommands.remove(subCommand);
    }

}
