package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import ui.MainWindow;
import ui.right_side.ViewsPanel;

public class XMLValidatorDisplayError implements ErrorHandler {

    public String fileError;
    public File f;

    public XMLValidatorDisplayError(String arg) throws IOException {
        this.fileError = arg;
        f = new File(fileError);

    }

    @Override
    public void warning(SAXParseException e) {

     try {
           PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(f, true))); 
            out.println("Warning - " + e.getMessage() + " File= " + e.getSystemId() + " Line Number = " + e.getLineNumber() + " Column Number = " + e.getColumnNumber());
            
            System.out.println("Warning - " + e.getMessage());
            System.out.println("In File - " + e.getSystemId());
            System.out.println("Line: " + e.getLineNumber());
            System.out.println("Column: " + e.getColumnNumber());
            
            
            
            
            out.close();
        } catch (IOException ex) {
            // Logger.getLogger(SimpleErrorHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void error(SAXParseException e) {

       try {
           PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(f, true))); 
            out.println("Error - " + e.getMessage()+" File= "+e.getSystemId()+" Line Number = " + e.getLineNumber()+" Column Number = " + e.getColumnNumber());
           
            System.out.println("Error - " + e.getMessage());
            System.out.println("In File - " + e.getSystemId());
            System.out.println("Line: " + e.getLineNumber());
            System.out.println("Column: " + e.getColumnNumber());
            
            out.close();
        } catch (IOException ex) {
            // Logger.getLogger(SimpleErrorHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void fatalError(SAXParseException e) {

        try {
           PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(f, true))); 
            out.println("FatalErrorr - " + e.getMessage() + "\nFile: " + e.getSystemId() + "\nLine Number: " + e.getLineNumber() + "\nColumn Number: " + e.getColumnNumber());
            
            System.out.println("Fatal Error - " + e.getMessage());
            System.out.println("In File - " + e.getSystemId());
            System.out.println("Line: " + e.getLineNumber());
            System.out.println("Column: " + e.getColumnNumber());
            
            //mw.getViewsPanel().getErrorTextArea().setText("asdas" + e.getMessage());
            
            out.close();
        } catch (IOException ex) {
            // Logger.getLogger(SimpleErrorHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
  
    }
}