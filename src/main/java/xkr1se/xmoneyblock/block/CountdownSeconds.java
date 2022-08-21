package xkr1se.xmoneyblock.block;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

/**
 * @author xkr1se
 */
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CountdownSeconds {
    int countdownTime;
    long beginTime = System.currentTimeMillis();;

    /**
     * Отсчитывает каждую секунду 1 единицу от числа
     * @return возвращает число да хуй знает что тут написать
     */
    public int getTimeLeft() {
        return (int) (((beginTime / 1000) + countdownTime) - (System.currentTimeMillis() / 1000));
    }
}
