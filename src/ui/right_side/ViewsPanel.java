package ui.right_side;

import java.awt.GridLayout;
import java.io.PrintStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.*;
import javax.xml.transform.stream.StreamResult;
import org.fife.rsta.ac.xml.tree.XmlOutlineTree;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import ui.MainWindow;
import ui.utils.CPopupMenu;
import ui.utils.ManageMenu;

public class ViewsPanel extends JPanel implements Observer {

    private TextView textView;
    private TreeView treeView;
    private XmlView xmlView;
    private int currItems = 0x7;
    private MainWindow mw;
    private JSplitPane splitPane1;
    private ManageMenu manageMenu;
    private String text;
    
    public final static int TEXT_VIEW = 2;
    public final static int XML_VIEW = 1;
    public final static int TREE_VIEW = 0;

    public ViewsPanel() {
        init();
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Update) {
            switch (((Update) arg).getType()) {
                case Update.CHANGE_UPDATE:
                case Update.INSERT_UPDATE:
                case Update.REMOVE_UPDATE:
                    notifyViews((Update) arg);
                    return;
                case Update.VIEW_CHANGE:
                    hidePanels((Update) arg);
            }
       }
    }
    
    public String infoNode(Node n)
    {
        String s = "\n";
        if(n.getNodeValue() != null)
        {
            s = n.getNodeValue(); 
            System.out.println("S este "+ s);
        } 
        else
        {
            NodeList p = (NodeList)n.getChildNodes();
            for(int i=0;i < p.getLength(); i++)
                infoNode(p.item(i));
        }
        text +=s;
        return s;
    }
    public void textFromXMLView(String s) throws Exception
    {
        text = new String();
       DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        
        InputSource is = new InputSource();
        is.setCharacterStream(new StringReader(s));
       org.w3c.dom.Document doc = db.parse(is);
       //String nodesNames = doc.getNodeName();
       Node n = doc.getFirstChild();
       String nodesInfo = doc.getNodeValue();
       //if(n.hasChildNodes())
           String q = new String();
               NodeList fi =(NodeList) n.getChildNodes();
              System.out.println(fi.getLength());
               for(int i = 0 ; i<fi.getLength(); i++)
               {
                   System.out.println(fi.item(i).getNodeName());
                   q += infoNode(fi.item(i));
                }
               System.out.println(q);
//System.out.println(s);
                   
       
           
       /*
       while(doc.hasChildNodes())
       {
           nodesInfo = doc.getNodeValue();
           System.out.println(nodesInfo);
       }
       */
       //List<Elements> elements = new ArrayList<Elements>();
    /*    
        String[] newXml = s.split("\\<\\?");
    ArrayList<String> xmlList = new ArrayList<>(Arrays.asList(newXml));
    for(int i = 0; i<xmlList.size();i++){
        if(!xmlList.get(i).contains("xml version=\"1.0\" encoding=\"UTF-8\"")){
            xmlList.remove(i);
        }

    }
    for(int j = 0;j<xmlList.size();j++){
        xmlList.set(j, "<?"+xmlList.get(j));
        xmlList.set(j,xmlList.get(j).split("\\#")[0]);
    }

        for (String xmlList1 : xmlList) {
            System.out.println(xmlList1);
        }
        */
        /*
        is.setCharacterStream(new StringReader(s));
        DOMSource domSource = new DOMSource(db.parse(is));
        StringWriter writer = new StringWriter();
        //StreamResult = new StreamResult(writer);
        StreamResult result = new StreamResult(writer);
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer =tf.newTransformer();
        transformer.transform(domSource, result);
        System.out.println("XML IN String format is: \n" + writer.toString());
        //DOMParser parser = new DOMParser();
        //Document doc = parser.getDocument();
         */
    }

    public void setContent(String str) {
        xmlView.setDocumentListener(false);
        ((JTextArea) xmlView.getView()).setText(str);
        xmlView.setDocumentListener(true);
        System.out.println(str);
        try {
            textFromXMLView(str);
        } catch (Exception ex) {
            Logger.getLogger(ViewsPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        textView.setDocumentListener(false);
        ((JTextArea) textView.getView()).setText(text);
        textView.setDocumentListener(true);
        
        //System.out.println("%s", textFromXMLView(str));
        reinvalidateViews();
    }

    public String getContent() {
        return ((JTextArea) xmlView.getView()).getText();
    }

    public void reinvalidateViews() {
        treeView.revalidate();
    }

    public XmlView getXmlView() {
        return xmlView;
    }

    public TextView getXmlText() {
        return textView;
    }

    public TreeView getXmlTree() {
        return treeView;
    }

    public void setMainWindow(MainWindow mw) {
        this.mw = mw;
        textView.setMainWindow(mw);
        xmlView.setMainWindow(mw);
        treeView.setMainWindow(mw);
    }

    public void showPanel(int n) {
        currItems ^= 1 << n;
        if (currItems > 0) {
            rearrange();
            manageMenu.setSelected(n);
            this.revalidate();
        } else {
            currItems ^= 1 << n;
            manageMenu.setSelected(n, true);
        }
    }

    private void rearrange() {
        if (this.getComponent(0) != splitPane1) {
            this.removeAll();
            this.add(splitPane1);
        }
        switch (currItems) {
            case 7:
                splitPane1.setLeftComponent(xmlView);
                splitPane1.setRightComponent(new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                        textView, treeView));
                return;
            case 6:
                splitPane1.setLeftComponent(xmlView);
                splitPane1.setRightComponent(textView);
                return;
            case 5:
                splitPane1.setLeftComponent(textView);
                splitPane1.setRightComponent(treeView);
                return;
            case 4:
                this.removeAll();
                this.add(textView);
                return;
            case 3:
                splitPane1.setLeftComponent(xmlView);
                splitPane1.setRightComponent(treeView);
                return;
            case 2:
                this.removeAll();
                this.add(xmlView);
                return;
            case 1:
                this.removeAll();
                this.add(treeView);
        }
    }

    private void init() {
        this.manageMenu = new ManageMenu();

        textView = new TextView();
        treeView = new TreeView();
        xmlView = new XmlView();

        splitPane1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, xmlView,
                new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                        textView, treeView));

        this.setLayout(new GridLayout(1, 0));

        menuForViews();

        xmlView.addObserver(this);
        textView.addObserver(this);

        treeView.addObserver(this);

        ((XmlOutlineTree) treeView.getView()).listenTo((RSyntaxTextArea) xmlView.getView());
        treeView.revalidate();

        this.add(splitPane1); 
        
    }

    private void hidePanels(Update upd) {
        if (upd.getView() instanceof TextView) {
            showPanel(TEXT_VIEW);
        } else if (upd.getView() instanceof TreeView) {
            showPanel(TREE_VIEW);
        } else if (upd.getView() instanceof XmlView) {
            showPanel(XML_VIEW);
        }
    }

    private void notifyViews(Update upd) {
        mw.setFileStatus(true);
    }

    private void menuForViews() {
        CPopupMenu m1 = new CPopupMenu(this);
        CPopupMenu m2 = new CPopupMenu(this);
        JPopupMenu m3 = xmlView.getPopupMenu();
        m3.addSeparator();
        m3.add(m1.getViews());

        this.setComponentPopupMenu(m2);
        xmlView.addPopMenu(m3);
        textView.addPopMenu(m2);
        treeView.addPopMenu(m2);

        manageMenu.setView((JMenu) m3.getComponent(12));
        manageMenu.setMenu(m2);
    }
}
