package xkr1se.xmoneyblock;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;
import xkr1se.xmoneyblock.block.MoneyBlockManager;
import xkr1se.xmoneyblock.commands.CommandManager;
import xkr1se.xmoneyblock.commands.impl.CommandManagerImpl;
import xkr1se.xmoneyblock.commands.impl.Help;
import xkr1se.xmoneyblock.commands.impl.Reload;
import xkr1se.xmoneyblock.config.ConfigData;
import xkr1se.xmoneyblock.listeners.PlayerListener;

import java.util.logging.Logger;

/**
 * @author xkr1se
 */
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public final class Main extends JavaPlugin {

    @Getter
    private static Main instance;

    Logger log;
    ConfigData configData;
    CommandManager commandManager;
    MoneyBlockManager moneyBlockManager;

    @Override
    public void onEnable() {
        instance = this;

        log = getLogger();
        configData = new ConfigData(getConfig());
        commandManager = new CommandManagerImpl();
        moneyBlockManager = new MoneyBlockManager();

        registerCommands();
        registerListeners();

        log.info("| xMoneyBlock v1.0 developed by xkr1se");

        saveDefaultConfig();

        moneyBlockManager.startLoop();
    }

    @Override
    public void onDisable() {
        log.info("| xMoneyBlock v1.0 developed by xkr1se. Bye!");

        //убрать действующий блок
        if(moneyBlockManager.getMoneyBlock().isAlive()) {
            moneyBlockManager.getMoneyBlock().destroy();
        }
        HandlerList.unregisterAll(this); //Отмена регистрации всех обработчиков
    }

    /**
     * Метод регистрации всех слушателей
     */
    private void registerListeners() {
        val pm = Bukkit.getPluginManager();

        pm.registerEvents(new PlayerListener(), this);
    }

    /**
     * Метод регистрации всех команд
     */
    private void registerCommands() {
        val mainCommand = getCommand("xmoneyblock");

        if(mainCommand != null) {
            mainCommand.setExecutor(commandManager);
        }

        commandManager.registerSub(new Help());
        commandManager.registerSub(new Reload());
    }

    /**
     * Метод переинициализации переменных из конфига
     */
    public void reloadConfigData() {
        reloadConfig();
        this.configData = new ConfigData(getConfig());
    }
}
