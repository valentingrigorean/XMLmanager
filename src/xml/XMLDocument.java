package xml;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class XMLDocument {

    DocumentBuilderFactory dbf;
    DocumentBuilder db;
    Document doc;
    TransformerFactory tf;
    Transformer transformer;
    
    public XMLDocument() {
        try {
            init();
        } catch (ParserConfigurationException | TransformerConfigurationException ex) {
            Logger.getLogger(XMLDocument.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean parse(String path){
        try {
            doc = db.parse(new File(path));
        } catch (SAXException | IOException ex) {            
            Logger.getLogger(XMLDocument.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }  
    
    

    private void init() throws ParserConfigurationException, TransformerConfigurationException {
        dbf = DocumentBuilderFactory.newInstance();
        db = dbf.newDocumentBuilder();
        db.setErrorHandler(new XmlErrorHandler());
        tf = TransformerFactory.newInstance();
        transformer = tf.newTransformer();
    }
}
