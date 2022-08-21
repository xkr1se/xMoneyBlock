package xkr1se.xmoneyblock.commands.impl;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xkr1se.xmoneyblock.Main;
import xkr1se.xmoneyblock.commands.SubCommand;
import xkr1se.xmoneyblock.config.ConfigData;

import java.util.List;

/**
 * @author xkr1se
 */
public class Reload implements SubCommand {

    ConfigData configData = Main.getInstance().getConfigData();

    @Override
    public boolean onCommand(CommandSender sender, String[] args) {
        Main.getInstance().reloadConfigData();
        sender.sendMessage(configData.getReloadSuccess());
        return false;
    }

    @Override
    public @Nullable List<String> aliases() {
        return null;
    }

    @Override
    public @Nullable String permission() {
        return "xmoneyblock.reload";
    }

    @Override
    public @NotNull String command() {
        return "reload";
    }


    @Override
    public @NotNull String description() {
        return configData.getReloadDesc();
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
