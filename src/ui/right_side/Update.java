package ui.right_side;

public class Update {

    private AbstractView view;
    private int type;

    public static final int INSERT_UPDATE = 0x0;
    public static final int REMOVE_UPDATE = 0x1;
    public static final int CHANGE_UPDATE = 0x2;
    public static final int VIEW_CHANGE = 0x3;

    public Update(AbstractView view, int type) {
        this.view = view;
        this.type = type;
    }
    
    public Update(){
        
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public AbstractView getView() {
        return view;
    }

    public void setView(AbstractView view) {
        this.view = view;
    }

}
