package xkr1se.xmoneyblock.block;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;
import xkr1se.xmoneyblock.hologram.HologramOnBlock;

/**
 * @author xkr1se
 */
@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public abstract class MoneyBlock {

    JavaPlugin plugin;
    HologramOnBlock hologram;
    Location locationBlock;
    int lifeTime;

    @NonFinal
    boolean isAlive = true;

    /**
     * Метод который спавнит золотой блок
     */
    abstract public void spawn();

    /**
     * Метод который удаляет золотой блок
     */
    public void destroy() {
        if(isAlive) {
            locationBlock.getBlock().setType(Material.AIR);
            hologram.delete();
        }

        isAlive = false;
    }

}
