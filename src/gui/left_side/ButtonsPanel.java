package gui.left_side;

import gui.MainWindow;
import gui.dialogs.Help;
import gui.dialogs.New;
import gui.dialogs.Open;
import gui.dialogs.Save;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ButtonsPanel extends JPanel {

    private JButton btn1;
    private JButton btn2;
    private JButton btn3;
    private JButton btn4;
    private JButton btn5;
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
                
                try {
                    openBtnPressed();
                } catch (IOException ex) {
                    Logger.getLogger(ButtonsPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        btn3 = new JButton("Save as");
        btn3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    saveBtnPressed();
                } catch (IOException ex) {
                    Logger.getLogger(ButtonsPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
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
               helpButtonPressed();
            }

        });

        this.add(btn1);
        this.add(btn2);
        this.add(btn3);
        this.add(btn4);
        this.add(btn5);
    }
    
    private void linkRecentFilesPanel(New dialog)
    {
        dialog.getPath();
        //split the link and add to the files panel
    }
    
    private void newBtnPressed() throws IOException {
          
       New newDialog = new New (this,".xml","Extensible Markup Language (.XML)");
       File fisierXML1 = new File("");
       String fromXMLView = ((JTextArea)(mw.getViewsPanel().getXmlView().getView())).getText();
        if(((JTextArea)(mw.getViewsPanel().getXmlView().getView())).getText().isEmpty())
        {
             
            newDialog.doModal();
            if(newDialog.isSet()){
            //  mw.setCurrentFilePath(newDialog.getPath());
            File fisierXML = new File(newDialog.getPath());
            if(newDialog.getPath().contains(".xml"))
            { 
                fisierXML1 = fisierXML;
                if(!fisierXML.createNewFile())
               {
              
                int n = JOptionPane.showConfirmDialog(mw,
                "The file already exist. \nDo you want to overwrite? ",
                "Existing file",
                JOptionPane.YES_NO_CANCEL_OPTION
                );
                switch(n){
                           case JOptionPane.YES_OPTION:
                               fisierXML.delete();
                               fisierXML.createNewFile();
                               ((JTextArea)(mw.getViewsPanel().getXmlView().getView())).setText(null);
                                mw.getViewsPanel().getXmlView().setDefaultText();
                                mw.getViewsPanel().getXmlView().setName(fisierXML.getName());
                               
                               return;
                           case JOptionPane.NO_OPTION:
                               newBtnPressed();
                               return;
                           case JOptionPane.CLOSED_OPTION:
                               return;
                           case JOptionPane.CANCEL_OPTION:
                               newBtnPressed();
                               
                       }
                }   
                else{
                   fisierXML.createNewFile();
                    ((JTextArea)(mw.getViewsPanel().getXmlView().getView())).setText(null);
                    mw.getViewsPanel().getXmlView().setDefaultText();
                    mw.getViewsPanel().getXmlView().setName(fisierXML.getName());
               }
            }
            else
            {
               File fisierXML2 = new File(fisierXML.getPath()+".xml");
              //  System.out.println(fisierXML2.getName());
               if(!fisierXML2.createNewFile())
               {
                    
                   int n = JOptionPane.showConfirmDialog(mw,
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
                               ((JTextArea)(mw.getViewsPanel().getXmlView().getView())).setText(null);
                               mw.getViewsPanel().getXmlView().setDefaultText();
                               mw.getViewsPanel().getXmlView().setName(fisierXML2.getName());
                              
                               return;
                           case JOptionPane.NO_OPTION:
                               newBtnPressed();
                               return;
                           case JOptionPane.CLOSED_OPTION:
                               return;
                           case JOptionPane.CANCEL_OPTION: 
                               newBtnPressed();
                            
                       }
                }
               else 
               {
                   fisierXML2.createNewFile();
                     ((JTextArea)(mw.getViewsPanel().getXmlView().getView())).setText(null);
                     mw.getViewsPanel().getXmlView().setDefaultText();
                  
               }
            }
        }
        }
            else
            {
                
                int n = JOptionPane.showConfirmDialog(mw,
                "Do you want to save the existing data? ",
                "Save",
                JOptionPane.YES_NO_CANCEL_OPTION
                );
                switch(n){
                           case JOptionPane.YES_OPTION:
                               saveBtnPressed();
                               ((JTextArea)(mw.getViewsPanel().getXmlView().getView())).setText(null);
                               mw.getViewsPanel().getXmlView().setName(fisierXML1.getName());
                             
                               return;
                           case JOptionPane.NO_OPTION:
                               newDialog.doModal();
                              if(newDialog.getOption() != 1);
                              {
                               ((JTextArea)(mw.getViewsPanel().getXmlView().getView())).setText(null);
                                mw.getViewsPanel().getXmlView().setDefaultText();
                              }
                             //  System.out.println(newDialog.getActionListeners().length);
                               if(newDialog.isSet()){
         
                        File fisierXML = new File(newDialog.getPath());
                        if(newDialog.getPath().contains(".xml"))
                        { 
                            fisierXML1 = fisierXML;
                            if(!fisierXML.createNewFile())
                           {

                            int n1 = JOptionPane.showConfirmDialog(mw,
                            "The file already exist. \nDo you want to overwrite? ",
                            "Existing file",
                            JOptionPane.YES_NO_CANCEL_OPTION
                            );
                            switch(n1){
                                       case JOptionPane.YES_OPTION:
                                           fisierXML.delete();
                                           fisierXML.createNewFile();
                                           ((JTextArea)(mw.getViewsPanel().getXmlView().getView())).setText(null);
                                            mw.getViewsPanel().getXmlView().setDefaultText();
                                            mw.getViewsPanel().getXmlView().setName(fisierXML.getName());

                                           return;
                                       case JOptionPane.NO_OPTION:
                                           newBtnPressed();
                                           return;
                                       case JOptionPane.CLOSED_OPTION:
                                           return;
                                       case JOptionPane.CANCEL_OPTION:
                                           newBtnPressed();

                                   }
                            }   
                            else{
                               fisierXML.createNewFile();
                               if(newDialog.getOption()!=1)
                               {
                               ((JTextArea)(mw.getViewsPanel().getXmlView().getView())).setText(null);
                               mw.getViewsPanel().getXmlView().setDefaultText();
                               }
                           }
                        }
                        else
                        {
                           File fisierXML2 = new File(fisierXML.getPath()+".xml");
                          //  System.out.println(fisierXML2.getName());
                           if(!fisierXML2.createNewFile())
                           {

                            int n1 = JOptionPane.showConfirmDialog(mw,
                            "The file already exist. \nDo you want to overwrite? ",
                            "Existing file",
                            JOptionPane.YES_NO_CANCEL_OPTION
                            );
                            switch(n1){
                                       case JOptionPane.YES_OPTION:
                                           //File fisierExistent = new File(fisierXML.getPath()+".xml");
                                           fisierXML.delete();
                                           fisierXML2.delete();
                                           fisierXML2.createNewFile();
                                           ((JTextArea)(mw.getViewsPanel().getXmlView().getView())).setText(null);
                                           mw.getViewsPanel().getXmlView().setDefaultText();
                                           mw.getViewsPanel().getXmlView().setName(fisierXML2.getName());

                                           return;
                                       case JOptionPane.NO_OPTION:
                                           newBtnPressed();
                                           return;
                                       case JOptionPane.CLOSED_OPTION:
                                           return;
                                       case JOptionPane.CANCEL_OPTION: 
                                           newBtnPressed();

                                        }
                            }
                           else 
                           {
                               fisierXML2.createNewFile();
                               if(newDialog.getOption()==0)
                               {
                               ((JTextArea)(mw.getViewsPanel().getXmlView().getView())).setText(null);
                                mw.getViewsPanel().getXmlView().setDefaultText();
                               }
                           }
                        }
                    }       
                          
                           case JOptionPane.CLOSED_OPTION:
                           case JOptionPane.CANCEL_OPTION:
                               if(newDialog.getOption() == 1 )
                            ((JTextArea)(mw.getViewsPanel().getXmlView().getView())).setText(fromXMLView);
                       }
            }
                
    } 
      
 
    private void openBtnPressed() throws FileNotFoundException, IOException {
        Open open = new Open(this, ".xml", "Extensible Markup Language (.XML)");
        //open.doModal();
        
        if(((JTextArea)(mw.getViewsPanel().getXmlView().getView())).getText().isEmpty())
        {
             
            open.doModal();
            if(open.isSet()){
          
            // 
            //  mw.setCurrentFilePath(newDialog.getPath());
            File fisierXML = new File(open.getPath());
            Scanner input = new Scanner(fisierXML);
            //fisierXML1 = fisierXML;
            
           while(input.hasNext())
           {
               ((JTextArea)(mw.getViewsPanel().getXmlView().getView())).append(input.nextLine()+"\n");
               
           }
            
        }
        }
            else
            {
                int n = JOptionPane.showConfirmDialog(mw,
                "Do you want to save the existing data? ",
                "Save",
                JOptionPane.YES_NO_CANCEL_OPTION
                );
                switch(n){
                           case JOptionPane.YES_OPTION:
                               saveBtnPressed();
                               open.doModal();
                               if(open.getOption()!=1)
                               ((JTextArea)(mw.getViewsPanel().getXmlView().getView())).setText(null);
                               if(open.isSet()){

                                  
                                    //  mw.setCurrentFilePath(newDialog.getPath());
                                    File fisierXML = new File(open.getPath());
                                    Scanner input = new Scanner(fisierXML);
                                    //fisierXML1 = fisierXML;

                                   while(input.hasNext())
                                   {
                                       ((JTextArea)(mw.getViewsPanel().getXmlView().getView())).append(input.nextLine()+"\n");

                                   }

                                }
                             //  mw.getViewsPanel().getXmlView().setName(fisierXML1.getName());
                             
                               return;
                           case JOptionPane.NO_OPTION:
                               open.doModal();
                               if(open.getOption()!=1)
                               ((JTextArea)(mw.getViewsPanel().getXmlView().getView())).setText(null);
                              // System.out.println("---------");
                              // mw.getViewsPanel().getXmlView().setDefaultText();
                              // mw.getViewsPanel().getXmlView().setName(fisierXML1.getName());
                               File fisierXML1 = new File(open.getPath());
                               Scanner input = new Scanner(fisierXML1);
                               
                                 while(input.hasNext())
                                 {
                                    ((JTextArea)(mw.getViewsPanel().getXmlView().getView())).append(input.nextLine()+"\n");
               
                                   }
                              
                           case JOptionPane.CLOSED_OPTION:
                           case JOptionPane.CANCEL_OPTION:
                          
                       }
            }
        }


    private void saveBtnPressed() throws IOException {
        Save save = new Save(this, ".xml", "Extensible Markup Language (.XML)");
        //save.doModal();
        if(!((JTextArea)(mw.getViewsPanel().getXmlView().getView())).getText().isEmpty())
        {
             
            save.doModal();
            if(save.isSet()){
          
            // 
            //  mw.setCurrentFilePath(newDialog.getPath());
            File fisierXML = new File(save.getPath());
            if(save.getPath().contains(".xml"))
            { 
                //fisierXML1 = fisierXML;
                if(!fisierXML.createNewFile())
               {
              
                int n = JOptionPane.showConfirmDialog(mw,
                "The file already exist. \nDo you want to overwrite? ",
                "Existing file",
                JOptionPane.YES_NO_CANCEL_OPTION
                );
                switch(n){
                           case JOptionPane.YES_OPTION:
                               fisierXML.delete();
                               fisierXML.createNewFile();
                               PrintWriter pr = new PrintWriter(fisierXML);
                               pr.print(((JTextArea)(mw.getViewsPanel().getXmlView().getView())).getText());
                                //mw.getViewsPanel().getXmlView().setDefaultText();
                                //mw.getViewsPanel().getXmlView().setName(fisierXML.getName());
                               pr.close();
                              return;
                           case JOptionPane.NO_OPTION:
                               save.doModal();
                               fisierXML.delete();
                               fisierXML.createNewFile();
                               pr = new PrintWriter(fisierXML);
                               pr.print(((JTextArea)(mw.getViewsPanel().getXmlView().getView())).getText());
                               pr.close();
                           case JOptionPane.CLOSED_OPTION:
                              
                           case JOptionPane.CANCEL_OPTION:
                               
                               
                       }
                }   
                else{
                    fisierXML.createNewFile();
                                PrintWriter pr = new PrintWriter(fisierXML);
                               pr.print(((JTextArea)(mw.getViewsPanel().getXmlView().getView())).getText());
                               pr.close();
                }
           
                    
               }
            
            else
            {
               File fisierXML2 = new File(fisierXML.getPath()+".xml");
              //  System.out.println(fisierXML2.getName());
               if(!fisierXML2.createNewFile())
               {
                    
                   int n = JOptionPane.showConfirmDialog(mw,
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
                               PrintWriter pr = new PrintWriter(fisierXML2);
                               pr.print(((JTextArea)(mw.getViewsPanel().getXmlView().getView())).getText());
                               pr.close();
                              // mw.getViewsPanel().getXmlView().setDefaultText();
                              // mw.getViewsPanel().getXmlView().setName(fisierXML2.getName());
                              
                               return;
                           case JOptionPane.NO_OPTION:
                               newBtnPressed();
                               return;
                           case JOptionPane.CLOSED_OPTION:
                               return;
                           case JOptionPane.CANCEL_OPTION: 
                               newBtnPressed();
                            
                       }
                }
               else 
               {
                   fisierXML2.createNewFile();
                                PrintWriter pr = new PrintWriter(fisierXML2);
                               pr.print(((JTextArea)(mw.getViewsPanel().getXmlView().getView())).getText());
                               pr.close();
                  // mw.getViewsPanel().getXmlView().setDefaultText();
               }
            }
        }
        }
            else
            {
                int n = JOptionPane.showConfirmDialog(mw,
                "Your XMLView is empty.Do you want to save? ",
                "Save",
                JOptionPane.YES_NO_CANCEL_OPTION
                );
                switch(n){
                           case JOptionPane.YES_OPTION:
                               save.doModal();
                               if(save.getPath().contains(".xml"))
                               {
                                File fisierXML2 = new File(save.getPath());
                                fisierXML2.createNewFile();
                              // ((JTextArea)(mw.getViewsPanel().getXmlView().getView())).setText(null);
                               //mw.getViewsPanel().getXmlView().setName(fisierXML1.getName());
                               }
                               else
                               {
                                   File fisierXML2 = new File(save.getPath()+ ".xml");
                                   fisierXML2.createNewFile();
                               }
                             
                           case JOptionPane.NO_OPTION:   
                           case JOptionPane.CLOSED_OPTION:
                           case JOptionPane.CANCEL_OPTION:
                          
                       }
            }
                
        //save.setDialogTitle("Create");
       /* if(save.isSet()){
            mw.setCurrentFilePath(save.getPath());
        }
               */
    }

    public void helpButtonPressed(){
        
        URL index;
        Help fereastra = new Help();
        index = fereastra.getClass().getClassLoader().getResource("./gui/help_files/index.html");
        fereastra = new Help("Help", index);
    }
    
    public JButton getBtn1() {
        return btn1;
    }

    public void setBtn1(JButton btn1) {
        this.btn1 = btn1;
    }

    public JButton getBtn2() {
        return btn2;
    }

    public void setBtn2(JButton btn2) {
        this.btn2 = btn2;
    }

    public JButton getBtn3() {
        return btn3;
    }

    public void setBtn3(JButton btn3) {
        this.btn3 = btn3;
    }

    public JButton getBtn4() {
        return btn4;
    }

    public void setBtn4(JButton btn4) {
        this.btn4 = btn4;
    }

    public JButton getBtn5() {
        return btn5;
    }

    public void setBtn5(JButton btn5) {
        this.btn5 = btn5;
    }
}
