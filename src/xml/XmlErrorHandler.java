package xml;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class XmlErrorHandler implements ErrorHandler {

    private int columnNumber;
    private int lineNumber;
    private int typeError;
    private String errorMessage;

    public static final int WARNING = 0;
    public static final int ERROR = 1;
    public static final int FATAL_ERROR = 2;

    @Override
    public void warning(SAXParseException saxpe) throws SAXException {
        typeError = WARNING;
        lineNumber = saxpe.getLineNumber();
        columnNumber = saxpe.getColumnNumber();
        errorMessage = saxpe.getMessage();
    }

    @Override
    public void error(SAXParseException saxpe) throws SAXException {
        typeError = ERROR;
        lineNumber = saxpe.getLineNumber();
        columnNumber = saxpe.getColumnNumber();
        errorMessage = saxpe.getMessage();
    }

    @Override
    public void fatalError(SAXParseException saxpe) throws SAXException {
        typeError = FATAL_ERROR;
        lineNumber = saxpe.getLineNumber();
        columnNumber = saxpe.getColumnNumber();
        errorMessage = saxpe.getMessage();
    }

    public int getLineNumber(){
        return lineNumber;
    }
    
    public int getColumnNumber(){
        return columnNumber;
    }
    
    public String getMessage(){
        return errorMessage;
    }
    
    public int getTypeError(){
        return typeError;
    }
}
