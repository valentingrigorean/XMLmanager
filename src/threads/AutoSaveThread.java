package threads;

import io.DocumentWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import ui.MainWindow;

public class AutoSaveThread extends AbstractThread {
   
    private String path;
    
    public final static int SLEEP_TIME = 600000;

    public AutoSaveThread(String path, MainWindow mw) {
        super(mw);
        this.path = path;        
    }

    public void setFilePath(String path) {
        this.path = path;
    }   

    @Override
    @SuppressWarnings("SleepWhileInLoop")
    public void run() {
        while (isRunning) {
            try {               
                Thread.sleep(SLEEP_TIME);
                saveFile();
            } catch (InterruptedException ex) {
                Logger.getLogger(AutoSaveThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void saveFile() {
        new DocumentWriter(path+"~", mw.getContent());
        mw.setFileStatus(false);
    }
}
