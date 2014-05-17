/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package xml;

import gui.MainWindow;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 *
 * @author Valentin
 */
public class XMLDocument {
    DocumentBuilderFactory dbf;
    DocumentBuilder db;
    private MainWindow mw;
    
    public XMLDocument(){
        
    }
    
    public void setDefaultText()
    {
        if(mw.getLeftSidePanel().getButtonsPanel().getBtn1().isCursorSet())
            mw.getViewsPanel().getXmlView().setDefaultText();
    }
    
}