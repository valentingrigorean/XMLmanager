package gui.right_side;

import gui.FlowLabel;
import gui.MainWindow;
import gui.Menu;
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
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public abstract class AbstractView extends JPanel {

    protected JComponent view;
    protected JComponent scrollPane;
    protected JButton btnExit;
    protected FlowLabel label;   
    protected ArrayList<Observer> observers;
    protected MainWindow mw;

    public MainWindow getMw() {
        return mw;
    }

    public AbstractView() {       
        init();
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
            obs.update(null, this);
        }
    }

    public void setMainWindow(MainWindow mw) {
        this.mw = mw;
    }

    public void addPopMenu(Menu menu) {
        this.setComponentPopupMenu(menu);
        view.setComponentPopupMenu(menu);
    }    
   

    public abstract void parse();

    private void init() {
        this.setPreferredSize(new Dimension(300, 300));
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));

        this.setLayout(new BorderLayout());

        btnExit = new JButton("x");
        btnExit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    btnExitPressed();
                } catch (IOException ex) {
                    Logger.getLogger(AbstractView.class.getName()).log(Level.SEVERE, null, ex);
                }
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

    abstract public void btnExitPressed() throws IOException;    
}
