/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui.dialogs;

import java.io.*;

import javax.swing.event.*;
import javax.swing.*;

import java.net.*;
import java.awt.event.*;
import java.awt.*;

/**
 *
 * @author Alexandru Rapanu
 */

public class Help extends JFrame implements ActionListener{
    
    private JEditorPane editorPanel;
    private URL adresaFisier;

    public Help(){
        //constructor simplu, neparametrizat
    }
    
    public Help(String titluFereastra, URL adresaFisierIN) {

        super(titluFereastra);

        adresaFisier = adresaFisierIN; 
        
        editorPanel = new JEditorPane();
        editorPanel.setEditable(false);

        try {
            editorPanel.setPage(adresaFisier);
        } catch (IOException ex) {
            
            ex.printStackTrace();
        }
        
        //adaug html listener pe editorPanel pentru a afisa fisierele html automat
        editorPanel.addHyperlinkListener(new HyperlinkListener() {
            @Override
            public void hyperlinkUpdate(HyperlinkEvent ev) {
                
                try {
                    
                    if (ev.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                        
                        editorPanel.setPage(ev.getURL());
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        // --- Help GUI -----------------------------------------
            getContentPane().add(new JScrollPane(editorPanel));
            adaugaButoaneInPanel();
            //setDefaultCloseOperation(EXIT_ON_CLOSE);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE); //inchid doar fereastra help
      
            ImageIcon img = new ImageIcon("./xml_editor_logo_icon_v2.png");
            setIconImage(img.getImage());
            
            setSize(new Dimension(600, 650));
            setLocationRelativeTo(null); //afisare in centrul ecranului
            setVisible(true);
        // ------------------------------------------------------
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String actionButtonName = e.getActionCommand(); //stochez numele butonului
        
        // in caz ca am mai multe butoane, folosesc un singur action listener
        if ("Close".equals(actionButtonName)) {
            processWindowEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING)); //inchid doar fereastra help (WINDOW_CLOSING)
        }
    }

    private void adaugaButoaneInPanel() {

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(this);
        
        // creez un panou de tip JPanel pentru butoanele din partea de jos a ferestrei
        JPanel panouButoane = new JPanel();
        panouButoane.add(closeButton);
        getContentPane().add(panouButoane, BorderLayout.SOUTH);
    }
}