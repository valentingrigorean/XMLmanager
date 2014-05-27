package xml;

import java.io.IOException;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import ui.MainWindow;

public class XmlValidator {

    private final MainWindow mw;
    private XmlErrorHandler xmlErrorHandler;
    private DocumentBuilderFactory dbf;
    private DocumentBuilder db;
    private org.w3c.dom.Document doc;

    public XmlValidator(MainWindow mw) {
        this.mw = mw;
        init();
    }

    public void validate(String str) {
        if (!str.isEmpty()) {
            try {
                doc = db.parse(new InputSource(new StringReader(str)));
            } catch (SAXException | IOException ex) {
                Logger.getLogger(XmlValidator.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    private void init() {
        try {
            xmlErrorHandler = new XmlErrorHandler(mw);
            dbf = DocumentBuilderFactory.newInstance();
            db = dbf.newDocumentBuilder();
            db.setErrorHandler(xmlErrorHandler);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(XmlValidator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
