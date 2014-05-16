package gui;

import gui.right_side.AbstractView;

public class Update {

    private AbstractView view;
    private Object update;

    public Update() {
        
    }

    public void setView(AbstractView view) {
        this.view = view;
    }

    public AbstractView getView() {
        return view;
    }

    public void setUpdate(Object upd) {
        update = upd;
    }

    public Object getUpdate() {
        return update;
    }
}
