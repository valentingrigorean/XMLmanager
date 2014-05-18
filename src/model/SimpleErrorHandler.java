/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 *
 * @author Alex
 */
public class SimpleErrorHandler implements ErrorHandler {

    public String fileError;
    public File f;

    public SimpleErrorHandler(String arg) throws IOException {
        this.fileError = arg;
        f = new File(fileError);

    }

    @Override
    public void warning(SAXParseException e) throws SAXException {

        try {
            BufferedWriter output = new BufferedWriter(new FileWriter(f));
            output.write("Warning - " + e.getMessage());
            output.write("Line Number = " + e.getLineNumber());
            output.write("Column Number = " + e.getColumnNumber());
            
            output.close();
        } catch (IOException ex) {
           // Logger.getLogger(SimpleErrorHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void error(SAXParseException e) throws SAXException {

        try {
            BufferedWriter output = new BufferedWriter(new FileWriter(f));
            output.write("Error - " + e.getMessage());
            output.write("Line Number = " + e.getLineNumber());
            output.write("Column Number = " + e.getColumnNumber());
            output.close();
            
            System.out.println("Error - " + e.getMessage());
            System.out.println("Line: " + e.getLineNumber());
            System.out.println("Column: " + e.getColumnNumber());
            
        } catch (IOException ex) {
           // Logger.getLogger(SimpleErrorHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void fatalError(SAXParseException e) throws SAXException {

        try {
            BufferedWriter output = new BufferedWriter(new FileWriter(f));
            output.write("Fatal Error - " + e.getMessage());
            output.write("Line Number = " + e.getLineNumber());
            output.write("Column Number = " + e.getColumnNumber());
            output.close();
            
            System.out.println("Error - " + e.getMessage());
            System.out.println("Line: " + e.getLineNumber());
            System.out.println("Column: " + e.getColumnNumber());
            
        } catch (IOException ex) {
           // Logger.getLogger(SimpleErrorHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
