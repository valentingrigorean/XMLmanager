package threads;

import java.util.logging.Level;
import java.util.logging.Logger;
import ui.MainWindow;

public class IdleThread extends AbstractThread {

    private volatile int currentTimer = 0;
    private final ValidatorThread validatorThread;

    public final static int STOP_TIMER = 15000;
    public final static int SLEEP_TIME = 100;

    public IdleThread(MainWindow mw) {
        super(mw);
        validatorThread = new ValidatorThread(mw);
    }

    public void resetTimer() {
        currentTimer = 0;
        if (!validatorThread.isRunning()) {
            validatorThread.start();
        }
    }

    @Override
    @SuppressWarnings("SleepWhileInLoop")
    public void run() {
        validatorThread.start();
        while (isRunning) {
            try {
                Thread.sleep(SLEEP_TIME);
                currentTimer += SLEEP_TIME;
                if (currentTimer == STOP_TIMER) {
                    validatorThread.stop();
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(IdleThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
