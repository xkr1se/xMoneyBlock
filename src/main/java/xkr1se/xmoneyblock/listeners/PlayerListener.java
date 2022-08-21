package xkr1se.xmoneyblock.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import xkr1se.xmoneyblock.Main;
import xkr1se.xmoneyblock.config.ConfigData;
import xkr1se.xmoneyblock.utils.MessageSender;
import xkr1se.xmoneyblock.utils.StringUtils;

/**
 * @author xkr1se
 */
public class PlayerListener implements Listener {

    ConfigData configData = Main.getInstance().getConfigData();

    /**
     * Обработчик ломания блока игроком
     * @param e - Ивент
     */
    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        if (e.getBlock().hasMetadata("moneyblock")) {
            MessageSender.sendMessageAll(StringUtils.replaceToPlaceholder(configData.getBlockDestroyMessage(), "%player%", e.getPlayer().getName()));

            Main.getInstance().getMoneyBlockManager().getMoneyBlock().destroy();

            if (configData.isSoundEnabled()) {
                try {
                    e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.valueOf(configData.getSoundType()), 70, 0);
                } catch (IllegalArgumentException exception) {
                    Main.getInstance().getLog().severe(String.format("Звук %s не найден.", configData.getSoundType()));
                }
            }

            if (configData.isEffectEnabled()) {
                try {
                    e.getPlayer().getWorld().playEffect(e.getPlayer().getLocation(), Effect.valueOf(configData.getEffectType()), configData.getEffectData());
                } catch (IllegalArgumentException exception) {
                    Main.getInstance().getLog().severe(String.format("Эффект %s не найден.", configData.getEffectType()));
                }
            }

            StringUtils.replaceToPlaceholder(configData.getCommandsCompleted(), "%player%", e.getPlayer().getName())
                .forEach(x -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), x));
        }
    }
}
