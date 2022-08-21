package xkr1se.xmoneyblock.commands.impl;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xkr1se.xmoneyblock.Main;
import xkr1se.xmoneyblock.commands.SubCommand;
import xkr1se.xmoneyblock.config.ConfigData;
import xkr1se.xmoneyblock.utils.MessageSender;

import java.util.List;

/**
 * @author xkr1se
 */
public class Help implements SubCommand {

    ConfigData configData = Main.getInstance().getConfigData();

    @Override
    public boolean onCommand(CommandSender sender, String[] args) {
        MessageSender.sendMessage(sender, configData.getHelpSuccess());
        return false;
    }

    @Override
    public @Nullable List<String> aliases() {
        return null;
    }

    @Override
    public @Nullable String permission() {
        return "xmoneyblock.help";
    }

    @Override
    public @NotNull String command() {
        return "help";
    }

    @Override
    public @NotNull String description() {
        return configData.getHelpDesc();
    }

    @Override
    public boolean onlyPlayer() {
        return false;
    }

    @Override
    public int argsMax() {
        return 0;
    }
}
