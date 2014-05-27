package threads;

import ui.MainWindow;

public abstract class AbstractThread implements Runnable {

    protected boolean isRunning;
    protected Thread thread;
    protected MainWindow mw;

    public AbstractThread(MainWindow mw) {
        this.mw = mw;
    }

    public void start() {
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    public void stop() {
        isRunning = false;
    }

    public boolean isRunning() {
        return this.isRunning;
    }

    @Override
    public abstract void run();
}
