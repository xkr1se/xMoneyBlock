package xkr1se.xmoneyblock.hologram.exceptions;

/**
 * @author xkr1se
 */
public class NullHologramException extends Exception {

    /**
     * Ошибка если голограмы не существует
     * @param message - Сообщение ошибки
     */
   public NullHologramException(final String message) {
       super(message);
   }

}
