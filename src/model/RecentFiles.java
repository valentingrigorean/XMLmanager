package model;

import java.io.File;
import java.util.ArrayList;

public class RecentFiles {

    private volatile ArrayList<String> paths;
    private static final String WINDOWS_PATH = "\\AppData\\XMLmanager";
    
    public RecentFiles() {
        this.paths = new ArrayList<>();
    }

    public void addFile(String path) {
        if (!paths.contains(path)) {
            paths.add(path);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    validAllPaths();
                }
            });
        }
    }

    public void removeFile(String path) {
        paths.remove(path);
    }

    public ArrayList<String> getPaths() {
        return paths;
    }

    private synchronized void validAllPaths() {
        for (String path : paths) {
            File file = new File(path);
            if (!file.exists()) {
                paths.remove(path);
            }
        }
    }
    
    private void loadFile(){
        String os = System.getProperty("os.name");
        if(os.startsWith("Windows")){            
            String path = System.getProperty("user.home");
            if(!new File(path+WINDOWS_PATH).exists()){
                new File(path+WINDOWS_PATH).mkdir();
            }
            File file = new File(path+WINDOWS_PATH+"\\recent.conf");
        }
    }

}
