package io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class DocumentReader {

    private BufferedReader buffer;

    public DocumentReader(String path) {
        readFile(path);
    }

    public DocumentReader() {

    }

    public void readFile(String path) {
        try {
            buffer = new BufferedReader(new FileReader(path));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DocumentReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String readLine() {
        if (buffer != null) {
            try {
                return buffer.readLine()+"\n";
            } catch (IOException ex) {
                Logger.getLogger(DocumentReader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public String readAllLines() {
        if (buffer != null) {
            StringBuilder sb = new StringBuilder();
            String s;
            try {
                while ((s = buffer.readLine()) != null) {
                    sb.append(s).append("\n");
                }
            } catch (IOException ex) {
                Logger.getLogger(DocumentReader.class.getName()).log(Level.SEVERE, null, ex);
            }
            return sb.toString();
        }
        return null;
    }

    public void close() {
        if (buffer != null) {
            try {
                buffer.close();
            } catch (IOException ex) {
                Logger.getLogger(DocumentReader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
