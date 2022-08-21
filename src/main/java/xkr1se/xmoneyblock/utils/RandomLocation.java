package xkr1se.xmoneyblock.utils;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.experimental.UtilityClass;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

/**
 * @author xkr1se
 */
@UtilityClass
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RandomLocation {

    Random random = new Random();

    /**
     * Метод который ищет безопасную локацию в мире
     * @param world - Мир к в котором искать
     * @param maxX - Максимальная координата X
     * @param maxZ - Максимальная координата Z
     * @return возвращает безопасную локацию
     */
    public @NotNull Location findSafeLocation(@NotNull World world, int maxX, int maxZ) {
        Location center = new Location(world, 0, 1, 0);
        Location loc = world.getHighestBlockAt(
                center.clone().add(
                        ((random.nextBoolean() ? 1 : -1) * random.nextInt(maxX)),
                        ((random.nextBoolean() ? 1 : -1) * random.nextInt(150)),
                        ((random.nextBoolean() ? 1 : -1) * random.nextInt(maxZ))))
                .getLocation();
        Location tpLocation = getLocationNormal(loc);
        return tpLocation != null ? tpLocation : findSafeLocation(world, maxX, maxZ);
    }

    private @Nullable Location getLocationNormal(@NotNull Location loc) {
        Block block = loc.getWorld().getHighestBlockAt(loc.getBlockX(), loc.getBlockZ());
        if (block.getType() == Material.AIR || !block.getType().isSolid()) {
            block = loc.getWorld().getBlockAt(loc.getBlockX(), block.getY() - 1, loc.getBlockZ());
        }

        return block.getY() > 0 ? new Location(loc.getWorld(), loc.getX() + 0.5D, (block.getY() + 1), loc.getZ() + 0.5D, loc.getYaw(), loc.getPitch()) : null;
    }
}
