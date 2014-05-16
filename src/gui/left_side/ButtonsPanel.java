package gui.left_side;

import gui.dialogs.New;
import gui.MainWindow;
import gui.dialogs.Open;
import gui.dialogs.Save;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import static javax.swing.JFileChooser.SAVE_DIALOG;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ButtonsPanel extends JPanel {

    private JButton btn1;
    private JButton btn2;
    private JButton btn3;
    private JButton btn4;
    private JButton btn5;
    private final MainWindow mw;
    private Component frame;

    public ButtonsPanel(MainWindow mw) {
        super();
        this.mw = mw;
        init();
    }

    private void init() {
        this.setPreferredSize(new Dimension(300, 300));
        this.setLayout(new GridLayout(5, 1));

        btn1 = new JButton("New");
        btn1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    newBtnPressed();
                    
                } catch (IOException ex) {
                    Logger.getLogger(ButtonsPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
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

        btn4 = new JButton("Validate");
        btn4.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
               // System.exit(0);
            }

        });
        btn5 = new JButton("Help");
        btn5.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
               // System.exit(0);
            }

        });

        this.add(btn1);
        this.add(btn2);
        this.add(btn3);
        this.add(btn4);
        this.add(btn5);
    }

    private void newBtnPressed() throws IOException {
          
        New newDialog = new New (this,".xml","Extensible Markup Language (.XML)");
        newDialog.doModal();
        
        //newDialog.setName("New");
      
        if(newDialog.isSet()){
           
           // System.out.println("--------");
          //  mw.setCurrentFilePath(newDialog.getPath());
            File fisierXML = new File(newDialog.getPath());
            if(newDialog.getPath().contains(".xml"))
            {
                if(!fisierXML.createNewFile())
               {
              
                int n = JOptionPane.showConfirmDialog(frame,
                "The file already exist. \nDo you want to overwrite? ",
                "Existing file",
                JOptionPane.YES_NO_CANCEL_OPTION
                );
                switch(n){
                           case JOptionPane.YES_OPTION:
                               fisierXML.delete();
                               fisierXML.createNewFile();
                               return;
                           case JOptionPane.NO_OPTION:
                               return;
                           case JOptionPane.CLOSED_OPTION:
                               return;
                           case JOptionPane.CANCEL_OPTION:   
                               return;
                       }
                }   
                else
                    fisierXML.createNewFile();
            }
            else
            {
               File fisierXML2 = new File(fisierXML.getPath()+".xml");
              //  System.out.println(fisierXML2.getName());
               if(!fisierXML2.createNewFile())
               {
                    
                   int n = JOptionPane.showConfirmDialog(frame,
                "The file already exist. \nDo you want to overwrite? ",
                "Existing file",
                JOptionPane.YES_NO_CANCEL_OPTION
                );
                switch(n){
                           case JOptionPane.YES_OPTION:
                               //File fisierExistent = new File(fisierXML.getPath()+".xml");
                               fisierXML.delete();
                               fisierXML2.delete();
                               fisierXML2.createNewFile();
                               return;
                           case JOptionPane.NO_OPTION:
                               return;
                           case JOptionPane.CLOSED_OPTION:
                               return;
                           case JOptionPane.CANCEL_OPTION:   
                               return;
                       }
                }
               else 
                   fisierXML2.createNewFile();
            }
          
    } 
     
// automatic go mouse to xml view

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
        //save.setDialogTitle("Create");
        if(save.isSet()){
            mw.setCurrentFilePath(save.getPath());
        }
    }
}
