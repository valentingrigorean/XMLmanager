package ui.right_side;

import java.awt.BorderLayout;
import java.io.IOException;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ToolTipManager;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.fife.rsta.ac.LanguageSupportFactory;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rtextarea.RTextScrollPane;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class TextView extends AbstractView {

    private String textFor = new String();

    public TextView() {
        super();
        init();
    }

    public void textFromXMLView(String s) {
        textFor = "";
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(s));         
            org.w3c.dom.Document doc = db.parse(is);

            Node n = doc.getFirstChild();
           // String nodesInfo = doc.getNodeValue();
            //String q = new String();
            //  System.out.println("aci  " + n);
            if (n == doc.getLastChild()) {
                if (n.getNodeName().contains("#comment")) {

                } else if (n.hasChildNodes()) {
                    NodeList sons = n.getChildNodes();
                    // System.out.println("aci  " + n);
                    for (int i = 0; i < sons.getLength(); i++) {
                        //System.out.println(sons.item(i).getNodeName());
                        infoNode(sons.item(i));
                    }
                } else {
                    textFor += n.getNodeValue();
                }
            } else {
                while (n != doc.getLastChild()) {
                    if (n.getNodeName().contains("#comment")) {

                    } else if (n.hasChildNodes()) {
                        NodeList sons = n.getChildNodes();
                        //System.out.println("aci  " + n);
                        for (int i = 0; i < sons.getLength(); i++) {
                            //System.out.println(sons.item(i).getNodeName());
                            infoNode(sons.item(i));
                        }
                    } else {
                        textFor += n.getNodeValue();
                    }
                    //System.out.println("aci2  " + n);
                    n = n.getNextSibling();
            //NodeList fi = (NodeList) n.getChildNodes();
                    //System.out.println(fi.getLength());

                }
            }
            // System.out.println("aici ai textFor " + textFor);
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(TextView.class.getName()).log(Level.SEVERE, null, ex);
        }
        ((JTextArea)view).setText(textFor);
    }

    private JTextArea createJTextArea() {
        RSyntaxTextArea textArea = new RSyntaxTextArea(25, 80);
        LanguageSupportFactory.get().register(textArea);
        textArea.setCaretPosition(0);
        textArea.requestFocusInWindow();
        textArea.setMarkOccurrences(true);
        textArea.setCodeFoldingEnabled(true);
        textArea.setTabsEmulated(true);
        textArea.setTabSize(3);
        textArea.setSyntaxEditingStyle("text/plain");
        ToolTipManager.sharedInstance().registerComponent(textArea);
        return textArea;
    }

    private void infoNode(Node n) {

        if (n.getNodeValue() != null && !n.getNodeValue().matches("\n *")) {
            textFor += n.getNodeValue();
            //text +='\n';
            if (n.getNodeName().contains("#")) {
                textFor += ' ';
            }

        } else {
            NodeList p = (NodeList) n.getChildNodes();
            for (int i = 0; i < p.getLength(); i++) {
                infoNode(p.item(i));
            }
        }

    }

    private void init() {
        view = new JTextArea();
        label.setText("TextView");
        scrollPane = new JScrollPane(view);
        ((JTextArea) view).setEditable(false);
        // scrollPane = new JScrollPane(view);
        ((JScrollPane) scrollPane).setViewportView(view);
        super.add(scrollPane, BorderLayout.CENTER);

//((JTextArea) textView.getView()).setText("ala");
        // this.getView().set
    }
}
