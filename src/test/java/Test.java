import xkr1se.xmoneyblock.block.CountdownSeconds;

/**
 * @author xkr1se
 */
public class Test {

    public static void main(String[] args) throws InterruptedException {
        CountdownSeconds countdownSeconds = new CountdownSeconds(10);
        System.out.println(countdownSeconds.getTimeLeft());
        Thread.sleep(2000);
        System.out.println(countdownSeconds.getTimeLeft());
        Thread.sleep(1000);
        System.out.println(countdownSeconds.getTimeLeft());
    }

}
