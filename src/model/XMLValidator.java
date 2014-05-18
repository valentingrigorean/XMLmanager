/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.XMLReader;

/**
 *
 * @author Alex
 */
public class XMLValidator {

    /**
     * @param args the command line arguments
     * 
     */
    
    
    public static void Validate(String arg){
        
        try {
            File f = new File("XMLError.txt");
            f.delete();
            f.createNewFile();
            File f1 = new File("DtdError.txt");
            f1.delete();
            f1.createNewFile();
            File f2 = new File("XsdError.txt");
            f2.delete();
            f2.createNewFile();
            execute(arg);
        } catch (IOException ex) {
           // Logger.getLogger(XmlValidator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void execute(String arg) {
        
        try {
            WellWriten(arg);
            
            
            
            File f3 = new File(arg);
            if (find(f3, "<!DOCTYPE")) {
                
                DTDValidate(arg);
                
            } else {
            
                File f1 = new File("DtdError.txt");
                f1.delete();
                f1.createNewFile();
            
            }

            if (find(f3, "xsi:noNamespaceSchemaLocation")) {
                
                XSDValidate(arg);
                
            } else {
           
                File f2 = new File("XsdError.txt");
                f2.delete();
                f2.createNewFile();
            
            }
            
            
        } catch (ParserConfigurationException ex) {
           // Logger.getLogger(XmlValidator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
           // Logger.getLogger(XmlValidator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
           //Logger.getLogger(XmlValidator.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

    public static void WellWriten(String arg) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setValidating(false);
        factory.setNamespaceAware(true);

        SAXParser parser = factory.newSAXParser();

        XMLReader reader = parser.getXMLReader();
     
        reader.setErrorHandler(new SimpleErrorHandler("WellWritten.txt"));
        reader.parse(new InputSource(arg));
    }

    public static boolean DTDValidate(String arg) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setValidating(true);
        factory.setNamespaceAware(true);

        SAXParser parser = factory.newSAXParser();

        XMLReader reader = parser.getXMLReader();

     
        reader.setErrorHandler(new SimpleErrorHandler("DtdError.txt"));
        reader.parse(new InputSource(arg));

        return true;
    }

    public static boolean XSDValidate(String arg) throws ParserConfigurationException, SAXNotRecognizedException, SAXNotSupportedException, SAXException, IOException {
        
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setValidating(true);
        factory.setNamespaceAware(true);

        SAXParser parser = factory.newSAXParser();
        parser.setProperty("http://java.sun.com/xml/jaxp/properties/schemaLanguage", "http://www.w3.org/2001/XMLSchema");

        XMLReader reader = parser.getXMLReader();
        
        reader.setErrorHandler(new SimpleErrorHandler("XsdError.txt"));
        reader.parse(new InputSource(arg));

        return true;
    }

    public static boolean find(File f, String searchString) {
        
        boolean result = false;
        Scanner in = null;
        
        try {
            in = new Scanner(new FileReader(f));
            
            while (in.hasNextLine() && !result) {
                result = in.nextLine().indexOf(searchString) >= 0;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            
            try {
                in.close();
            } catch (Exception e) { /* ignore */ }
        }
        return result;
    }
}
