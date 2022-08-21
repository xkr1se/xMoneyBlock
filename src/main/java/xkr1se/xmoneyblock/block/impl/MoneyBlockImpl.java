package xkr1se.xmoneyblock.block.impl;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;
import xkr1se.xmoneyblock.Main;
import xkr1se.xmoneyblock.block.MoneyBlock;
import xkr1se.xmoneyblock.config.ConfigData;
import xkr1se.xmoneyblock.hologram.HologramOnBlock;
import xkr1se.xmoneyblock.hologram.exceptions.AlreadyExistsHologramException;
import xkr1se.xmoneyblock.utils.MessageSender;
import xkr1se.xmoneyblock.utils.StringUtils;

/**
 * @author xkr1se
 */
public class MoneyBlockImpl extends MoneyBlock {
    ConfigData configData = Main.getInstance().getConfigData();

    public MoneyBlockImpl(JavaPlugin plugin, HologramOnBlock hologram, Location locationBlock, int lifeTime) {
        super(plugin, hologram, locationBlock, lifeTime);
    }

    @Override
    public void spawn() {
        Block moneyBlock = getLocationBlock().getBlock();

        moneyBlock.setType(Material.GOLD_BLOCK);
        moneyBlock.setMetadata("moneyblock", new FixedMetadataValue(getPlugin(), "sex"));

        try {
            getHologram().create();
        } catch (AlreadyExistsHologramException e) {
            throw new RuntimeException(e);
        }
        MessageSender.sendMessageAll(StringUtils.replaceToPlaceholder(configData.getBlockSpawnMessage(), "%coordinates%",
                String.format("X: %.2f Y: %.2f Z: %.2f",
                        getLocationBlock().getX(),
                        getLocationBlock().getY(),
                        getLocationBlock().getZ())));

        //удаление блока если его время жизни истечёт
        Bukkit.getScheduler().runTaskLater(getPlugin(), () -> {
            if(isAlive()) {
                this.destroy();
                MessageSender.sendMessageAll(configData.getBlockDespawnMessage());
            }
        },  getLifeTime() * 20L);
    }
}
