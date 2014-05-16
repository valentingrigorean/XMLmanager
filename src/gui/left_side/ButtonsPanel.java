package gui.left_side;

import gui.MainWindow;
import gui.dialogs.Open;
import gui.dialogs.Save;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonsPanel extends JPanel {

    private JButton btn1;
    private JButton btn2;
    private JButton btn3;
    private JButton btn4;
    private MainWindow mw;

    public ButtonsPanel() {
        super();        
        init();
    }
    
    public void setMainWindow(MainWindow mw){
        this.mw = mw;
    }

    private void init() {
        this.setPreferredSize(new Dimension(300, 300));
        this.setLayout(new GridLayout(4, 1));

        btn1 = new JButton("New");
        btn1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                newBtnPressed();
            }
        });

        btn2 = new JButton("Open");
        btn2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {                
                openBtnPressed();
            }
        });

        btn3 = new JButton("Save as");
        btn3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                saveBtnPressed();
            }
        });

        btn4 = new JButton("Exit");
        btn4.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }

        });

        this.add(btn1);
        this.add(btn2);
        this.add(btn3);
        this.add(btn4);
    }

    private void newBtnPressed() {
        
    }

    private void openBtnPressed() {
        Open open = new Open(this, ".xml", "Extensible Markup Language (.XML)");
        open.doModal();
        if(open.isSet()){
            mw.setCurrentFilePath(open.getPath());
        }
    }

    private void saveBtnPressed() {
        Save save = new Save(this, ".xml", "Extensible Markup Language (.XML)");
        save.doModal();
        if(save.isSet()){
            mw.setCurrentFilePath(save.getPath());
        }
    }
}
