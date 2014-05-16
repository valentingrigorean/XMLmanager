package model;

/**
 *
 * @author Valentin
 */
public class XML {
    Node root;
    
    public XML(){
        
    }
    
    public void addElement(Node node){
        root.addNode(node);
    }
    
    public void removeElement(Node node){
        root.removeNode(node);
    }
    
    
}
