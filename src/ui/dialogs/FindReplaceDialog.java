package ui.dialogs;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rtextarea.RTextArea;
import org.fife.ui.rtextarea.SearchContext;
import org.fife.ui.rtextarea.SearchEngine;
import org.fife.ui.rtextarea.SearchResult;
import ui.MainWindow;
import ui.utils.LabelledItemPanel;



public class FindReplaceDialog extends JPanel {

    private JTextField searchField;
    private JTextField replaceField;
    private final JCheckBox regexCB = new JCheckBox("Regular expressions");
    private final JCheckBox matchCaseCB = new JCheckBox("Match Case");
    private final JCheckBox wholeWordCB = new JCheckBox("Whole word");
    private final JCheckBox wrapSearchCB = new JCheckBox("Wrap Search");
    private final JRadioButton backwardSearch = new JRadioButton("Backward");
    private final JRadioButton forwardSearch = new JRadioButton("Forward");
    private RSyntaxTextArea textArea;

    public FindReplaceDialog(final MainWindow frame) {
        setLayout(new GridLayout(4, 1));
        textArea = null;

        LabelledItemPanel findReplacePanel = new LabelledItemPanel();

        JPanel optionsPanel = new JPanel(new GridLayout(2, 2));
        Border paddingBorderOptions = new EmptyBorder(10, 10, 10, 10);
        Border titleBorderOptions = BorderFactory.createTitledBorder("Options");
        optionsPanel.setBorder(new CompoundBorder(paddingBorderOptions,
                titleBorderOptions));
        optionsPanel.add(regexCB);
        optionsPanel.add(matchCaseCB);
        optionsPanel.add(wholeWordCB);
        optionsPanel.add(wrapSearchCB);
        wrapSearchCB.setSelected(true);

        JPanel directionPanel = new JPanel(new GridLayout(1, 2));
        Border paddingBorderDirection = new EmptyBorder(10, 10, 10, 10);
        Border titleBorderDirection = BorderFactory
                .createTitledBorder("Direction");
        directionPanel.setBorder(new CompoundBorder(paddingBorderDirection,
                titleBorderDirection));
        ButtonGroup group = new ButtonGroup();
        group.add(forwardSearch);
        group.add(backwardSearch);
        directionPanel.add(forwardSearch);
        directionPanel.add(backwardSearch);
        forwardSearch.setSelected(true);
   
        JPanel buttonPanel = new JPanel(new GridLayout(3, 3));

        add(findReplacePanel);
        add(directionPanel);
        add(optionsPanel);
        add(buttonPanel);
        this.setBorder(new EmptyBorder(10, 10, 15, 15)); // Padding around panel

        searchField = new JTextField(20);
        searchField.setText(frame.getLastSearchString());
        replaceField = new JTextField(20);
        replaceField.setText(frame.getLastReplaceString());
        findReplacePanel.addItem("Find", searchField);
        findReplacePanel.addItem("Replace", replaceField);

        final JButton nextButton = new JButton("Find");
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setLastSearchString(searchField.getText());
                SearchContext context = setUpContext();
                find(context);                
            }
        });
        buttonPanel.add(nextButton);
        frame.getRootPane().setDefaultButton(nextButton);

        final JButton replaceFindButton = new JButton("Replace / Find");
        replaceFindButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setLastSearchString(searchField.getText());
                frame.setLastReplaceString(replaceField.getText());
                SearchContext context = setUpContext();
                replace(context);
                find(context);
            }
        });
        buttonPanel.add(replaceFindButton);

        JButton replaceButton = new JButton("Replace");
        replaceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setLastReplaceString(replaceField.getText());
                SearchContext context = setUpContext();
                replace(context);
            }
        });
        buttonPanel.add(replaceButton);

        final JButton replaceAllButton = new JButton("Replace All");
        replaceAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setLastReplaceString(replaceField.getText());
                SearchContext context = setUpContext();
                if (context != null) {
                    SearchResult replacements = SearchEngine.replaceAll(textArea,
                            context);                    
                }
            }
        });
        buttonPanel.add(replaceAllButton);
       
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        buttonPanel.add(closeButton);
    }
    
    private SearchContext setUpContext() {
        SearchContext context = new SearchContext();
        String text = searchField.getText();
        if (text.length() == 0) {
            return null;
        }
        context.setSearchFor(text);
        context.setReplaceWith(replaceField.getText());
        context.setSearchForward(forwardSearch.isSelected());
        context.setMatchCase(matchCaseCB.isSelected());
        context.setRegularExpression(regexCB.isSelected());
        context.setWholeWord(wholeWordCB.isSelected());

        return context;
    }
   
    private void find(SearchContext context) {
        if (context != null) {
            SearchResult found = SearchEngine.find(textArea, context);
            if (!found.wasFound()) {
                if (wrapSearchCB.isSelected()) {
                    if (forwardSearch.isSelected()) {
                        textArea.setCaretPosition(0);
                    } else {
                        textArea.setCaretPosition(textArea.getText().length());
                    }
                }
            }
        }
    }
    
    private void replace(SearchContext context) {
        if (context != null) {
            SearchEngine.replace((RTextArea) textArea, context);
        }

    }
}
