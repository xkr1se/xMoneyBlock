package xkr1se.xmoneyblock.config;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static xkr1se.xmoneyblock.utils.StringUtils.s;

/**
 * @author xkr1se
 */
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ConfigData {
    FileConfiguration configuration;

    List<String> hologramText;
    List<String> blockSpawnMessage;
    List<String> blockDespawnMessage;
    List<String> blockDestroyMessage;
    List<String> helpSuccess;
    List<String> commandsCompleted;

    int lifetimeBlock;
    int timeRespawn;
    int maxX;
    int maxZ;
    int effectData;

    boolean hologramEnabled;
    boolean soundEnabled;
    boolean effectEnabled;

    String worldSpawn;
    String effectType;
    String soundType;
    String noPermissions;
    String noAllowed;
    String notFoundCommand;
    String onlyPlayer;
    String helpDesc;
    String reloadDesc;
    String reloadSuccess;
    String respawnDesc;
    String respawnSuccess;


    /**
     * Конструктор - инициализия всех переменных из конфига
     * @param configuration - Конфиг
     */
    public ConfigData(@NotNull FileConfiguration configuration) {
        this.configuration = configuration;

        this.hologramText = configuration.getStringList("hologram.text");
        this.blockSpawnMessage = configuration.getStringList("messages.block-spawn");
        this.blockDespawnMessage = configuration.getStringList("messages.block-despawn");
        this.blockDestroyMessage = configuration.getStringList("messages.block-destroy");
        this.commandsCompleted = configuration.getStringList("commands-completed");

        this.lifetimeBlock = configuration.getInt("settings.lifetime-block");
        this.timeRespawn = configuration.getInt("settings.time-respawn");
        this.maxX = configuration.getInt("settings.x");
        this.maxZ = configuration.getInt("settings.z");
        this.effectData = Integer.parseInt(s(configuration.getString("settings.effect-type")).split(":")[1]);

        this.hologramEnabled = configuration.getBoolean("hologram.enable");
        this.soundEnabled = configuration.getBoolean("settings.sound");
        this.effectEnabled = configuration.getBoolean("settings.effect");

        this.worldSpawn = s(configuration.getString("settings.world-spawn"));
        this.effectType = s(configuration.getString("settings.effect-type")).split(":")[0];
        this.soundType = s(configuration.getString("settings.sound-type"));
        this.noPermissions = s(configuration.getString("messages.no-perm"));
        this.noAllowed = s(configuration.getString("messages.no-allowed"));
        this.notFoundCommand = s(configuration.getString("messages.not-found-command"));
        this.onlyPlayer = s(configuration.getString("messages.only-player"));
        this.helpDesc = s(configuration.getString("commands.help.description"));
        this.reloadDesc = s(configuration.getString("commands.reload.description"));
        this.reloadSuccess = s(configuration.getString("commands.reload.success"));
        this.respawnDesc = s(configuration.getString("commands.respawn.description"));
        this.respawnSuccess = s(configuration.getString("commands.respawn.success"));

        this.helpSuccess = Collections.unmodifiableList(
                new ArrayList<String>() {
                    {
                        add(getReloadDesc());
                        add(getRespawnDesc());
                    }
                }
        );
    }
}
