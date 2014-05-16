package model;


import java.util.logging.Level;
import java.util.logging.Logger;

public class AutoUpdate implements Runnable {

    private Thread thread;
    private String path;
    private boolean isRunning;

    public final static int SLEEP_TIME = 600000;

    public AutoUpdate(String path) {
        this.path = path;
    }
    
    public void setFilePath(String path){
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
                Logger.getLogger(AutoUpdate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void saveFile() {
        System.out.println("SAVING " + path + "~");
    }

}
