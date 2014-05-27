package threads;

import java.util.logging.Level;
import java.util.logging.Logger;
import ui.MainWindow;
import xml.XmlValidator;

public class ValidatorThread extends AbstractThread {

    private final XmlValidator validator;

    public final static int SLEEP_TIME = 1000;

    public ValidatorThread(MainWindow mw) {
        super(mw);        
        validator = new XmlValidator(mw);
    }

    @Override
    @SuppressWarnings("SleepWhileInLoop")
    public void run() {
        while (isRunning) {
            try {
                Thread.sleep(SLEEP_TIME);
                if (mw.getGotInput()) {
                    mw.setGotInput(false);
                    mw.clearAllErrors();                    
                    validator.validate(mw.getContent());                   
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(ValidatorThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
