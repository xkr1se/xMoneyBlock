package xkr1se.xmoneyblock.block;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import xkr1se.xmoneyblock.Main;
import xkr1se.xmoneyblock.block.impl.MoneyBlockImpl;
import xkr1se.xmoneyblock.config.ConfigData;
import xkr1se.xmoneyblock.hologram.HologramOnBlock;
import xkr1se.xmoneyblock.utils.RandomLocation;
import xkr1se.xmoneyblock.utils.TimeUtils;

/**
 * @author xkr1se
 */
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MoneyBlockManager {

    JavaPlugin plugin = Main.getInstance();
    ConfigData configData = Main.getInstance().getConfigData();

    @Getter
    @NonFinal
    MoneyBlock moneyBlock;

    @Getter
    @NonFinal
    HologramOnBlock hologramOnBlock;

    @Getter
    @NonFinal
    CountdownSeconds countdown;

    @Getter
    @NonFinal
    BukkitTask blockSpawner;

    /**
     * Метод который начинает цикл спавна блока
     */
    public void startLoop() {
        if(configData.getTimeRespawn() < configData.getLifetimeBlock()){
            Main.getInstance().getLog().severe("Время жизни блока не может быть больше чем время респавна блока...");
            return;
        }

        blockSpawner = Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            World worldSpawn = Bukkit.getWorld(configData.getWorldSpawn());
            if(worldSpawn == null) {
                Main.getInstance().getLog().severe(String.format("Мир %s не найден.", configData.getWorldSpawn()));
                return;
            }

            Location safeLocation = RandomLocation.findSafeLocation(worldSpawn, configData.getMaxX(), configData.getMaxZ());
            hologramOnBlock = new HologramOnBlock(
                    plugin,
                    new Location(
                            safeLocation.getWorld(),
                            safeLocation.getX(),
                            safeLocation.getY() + 2f,
                            safeLocation.getZ()),
                    configData.getHologramText());

            int lifetime = configData.getLifetimeBlock();
            countdown = new CountdownSeconds(lifetime);

            hologramOnBlock.registerPlaceholder("%time%", 0.5D,
                    () -> TimeUtils.timeFormat(countdown.getTimeLeft()));

            (moneyBlock = new MoneyBlockImpl(plugin, hologramOnBlock, safeLocation, lifetime)).spawn();
        }, configData.getTimeRespawn() * 20L, configData.getTimeRespawn() * 20L);
    }

    /**
     * Метод который заканчивает цикл спавна блока
     */
    public void stopLoop() {
        if(blockSpawner != null) {
            blockSpawner.cancel();
        }
    }
}
