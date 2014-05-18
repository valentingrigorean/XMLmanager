package ui.right_side;

import ui.utils.FlowLabel;
import ui.MainWindow;
import ui.utils.Menu;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public abstract class AbstractView extends JPanel {

    protected JComponent view;
    protected JComponent scrollPane;
    protected JButton btnExit;
    protected FlowLabel label;
    protected ArrayList<Observer> observers;
    protected MainWindow mw;
    protected Update update;
    protected boolean docListener;

    public MainWindow getMw() {
        return mw;
    }

    public AbstractView() {
        this.docListener = false;
        init();
    }

    public void setDocumentListener(boolean b) {
        docListener = b;
    }

    public JComponent getView() {
        return view;
    }

    public void addObserver(Observer obs) {
        if (!observers.contains(obs)) {
            observers.add(obs);
        }
    }

    public void removeObserver(Observer obs) {
        observers.remove(obs);
    }

    public void notifyObservers() {
        for (Observer obs : observers) {
            obs.update(null, update);
        }
    }

    public void setMainWindow(MainWindow mw) {
        this.mw = mw;
    }

    public void addPopMenu(Menu menu) {
        this.setComponentPopupMenu(menu);
        view.setComponentPopupMenu(menu);
    }

    private void init() {
        update = new Update(this, -1);

        this.setPreferredSize(new Dimension(300, 300));
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
        this.setLayout(new BorderLayout());

        btnExit = new JButton("x");
        btnExit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                update.setType(Update.VIEW_CHANGE);
                notifyObservers();
            }
        });

        label = new FlowLabel();

        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());
        p.add(label, BorderLayout.CENTER);
        p.add(btnExit, BorderLayout.EAST);

        this.add(p, BorderLayout.NORTH);
        this.observers = new ArrayList<>();
    }
}
