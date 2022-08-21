package xkr1se.xmoneyblock.commands.impl;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import xkr1se.xmoneyblock.Main;
import xkr1se.xmoneyblock.commands.CommandManager;
import xkr1se.xmoneyblock.config.ConfigData;

import java.util.Arrays;

/**
 * @author xkr1se
 */
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommandManagerImpl extends CommandManager {

    ConfigData configData = Main.getInstance().getConfigData();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(args.length == 0) {
            sender.sendMessage(configData.getNoAllowed());
            return true;
        }

        val subCommand = getSubCommandWithString(args[0]);

        if(subCommand != null) {
            if(subCommand.onlyPlayer() && !(sender instanceof Player)) {
                sender.sendMessage(configData.getOnlyPlayer());
                return true;
            }

            val permission = subCommand.permission();
            if((sender instanceof Player)
                    && permission != null
                    && !sender.hasPermission(permission)
                    && sender.hasPermission("xmoneyblock.use")) {
                sender.sendMessage(configData.getNoPermissions());
                return true;
            }

            val subCommandArgs = Arrays.copyOfRange(args, 1, args.length);

            if(subCommandArgs.length > subCommand.argsMax()) {
                sender.sendMessage(subCommand.description());
                return true;
            }

            return subCommand.onCommand(sender, subCommandArgs);
        } else {
            sender.sendMessage(configData.getNotFoundCommand());
            return true;
        }
    }

}
