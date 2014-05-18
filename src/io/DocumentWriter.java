package io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class DocumentWriter {
    
    private BufferedWriter buffer;
    private FileWriter file;
    
    public DocumentWriter(){
        
    }
    
    public DocumentWriter(String path,String str){
        writeFile(path,str);
    }
    
    public void writeFile(String path,String str){
        try {
            file = new FileWriter(path);
            buffer = new BufferedWriter(file);
            buffer.write(str);
            buffer.close();
            file.close();
        } catch (IOException ex) {
            Logger.getLogger(DocumentWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
}
