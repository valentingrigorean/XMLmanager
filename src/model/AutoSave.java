package model;

import io.DocumentWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import ui.MainWindow;

public class AutoSave implements Runnable {

    private Thread thread;
    private String path;
    private boolean isRunning;
    private final MainWindow mw;
    public final static int SLEEP_TIME = 600000;

    public AutoSave(String path, MainWindow mw) {
        this.path = path;
        this.mw = mw;
    }

    public void setFilePath(String path) {
        this.path = path;
    }

    public void start() {
        thread = new Thread(this);
        isRunning = true;
        thread.start();
    }

    public void stop() {
        saveFile();
        isRunning = true;
    }

    @Override
    @SuppressWarnings("SleepWhileInLoop")
    public void run() {
        while (isRunning) {
            try {
                saveFile();
                Thread.sleep(SLEEP_TIME);
                saveFile();
            } catch (InterruptedException ex) {
                Logger.getLogger(AutoSave.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void saveFile() {
        new DocumentWriter(path, mw.getContent());
        mw.setFileStatus(false);
    }
}
