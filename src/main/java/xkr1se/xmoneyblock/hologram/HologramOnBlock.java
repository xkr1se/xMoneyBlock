package xkr1se.xmoneyblock.hologram;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import com.gmail.filoghost.holographicdisplays.api.placeholder.PlaceholderReplacer;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import xkr1se.xmoneyblock.hologram.exceptions.AlreadyExistsHologramException;
import xkr1se.xmoneyblock.hologram.exceptions.NullHologramException;
import xkr1se.xmoneyblock.utils.StringUtils;

import java.util.Collection;

/**
 * @author xkr1se
 */
@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class HologramOnBlock {

    JavaPlugin plugin;
    Location hologramLocation;
    Collection<String> hologramLines;

    @NonFinal
    Hologram hologram;

    @NonFinal
    boolean isAlive = false;

    /**
     * Метод создания голограмы
     */
    public void create() throws AlreadyExistsHologramException {
        //проверка на сущесвование этой голограмы
        if(isAlive) {
            throw new AlreadyExistsHologramException("The hologram already exists!");
        }

        hologram = HologramsAPI.createHologram(plugin, hologramLocation);
        hologram.setAllowPlaceholders(true);

        //добавление линий в голограму
        hologramLines.stream()
                .map(StringUtils::s)
                .forEach(hologram::appendTextLine);

        isAlive = true;
    }

    /**
     * Метод удаления
     */
    public void delete() {
        hologram.delete();

        isAlive = false;
    }

    /**
     * Метод который регистрирует плейсхолдер в HolographicDisplays
     * @param textPlaceholder - Текст который будет изменятся на плейсхолдер
     * @param timeUpdate - Время обновления плейсхолдера
     * @param replacer - На что менять текст плейсхолдера
     */
    public void registerPlaceholder(final String textPlaceholder, double timeUpdate, @NotNull PlaceholderReplacer replacer) {
        HologramsAPI.registerPlaceholder(plugin, textPlaceholder, timeUpdate, replacer);
    }

    /**
     * Метод получения поля {@link HologramOnBlock#hologram}
     * @return - возвращает текущую голограму
     * @throws NullHologramException - На случай если голограма не создана
     */
    public @NotNull Hologram getHologram() throws NullHologramException {
        if(this.hologram == null) {
            throw new NullHologramException("");
        }

        return hologram;
    }
}
