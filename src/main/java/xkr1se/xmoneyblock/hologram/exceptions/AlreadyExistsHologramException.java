package xkr1se.xmoneyblock.hologram.exceptions;
/**
* @author xkr1se
*/
public class AlreadyExistsHologramException extends Exception {

    /**
     * Ошибка если голограма уже существует
     * @param message - Сообщение ошибки
     */
    public AlreadyExistsHologramException(final String message) {
        super(message);
    }

}
