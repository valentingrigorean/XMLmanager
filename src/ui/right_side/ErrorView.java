package ui.right_side;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class ErrorView extends JPanel {

    private javax.swing.JTable table;
    private ArrayList<ErrorList> container;

    private static String[] HEADERS = new String[]{"Description", "Line", "Column", "Type"};

    private class ErrorList {

        private String type;
        private String msg;
        private String line;
        private String column;

        public ErrorList(String type, String msg, String line, String column) {
            this.type = type;
            this.msg = msg;
            this.line = line;
            this.column = column;
        }

        public String getMsg() {
            return msg;
        }

        public String getLine() {
            return line;
        }

        public String getColumn() {
            return column;
        }

        public String getType() {
            return type;
        }
    }

    private class TableMod extends DefaultTableModel {

        public TableMod() {
            super();
        }

        public TableMod(String[][] items, String[] tableNames) {
            super(items, tableNames);
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    }

    public ErrorView() {
        this.container = new ArrayList<>();
        init();
    }

    public void clearAll() {
        container.clear();
        setTableMod();
    }

    public void error(String type, String msg, int line, int column) {
        container.add(new ErrorList(type, msg, Integer.toString(line),
                Integer.toString(column)));
        setTableMod();
    }

    private void setTableMod() {
        String[][] values = new String[container.size()][4];
        for (int i = 0; i < container.size(); i++) {
            values[i][0] = container.get(i).getMsg();
            values[i][1] = container.get(i).getLine();
            values[i][2] = container.get(i).getColumn();
            values[i][3] = container.get(i).getType();
        }
        table.setModel(new TableMod(values, HEADERS));
    }

    private void init() {

        this.setDoubleBuffered(true);
        table = new JTable();

        setPreferredSize(new Dimension(250, 100));
        setLayout(new BorderLayout());

        table.setModel(new TableMod(null, HEADERS));
        table.setShowGrid(false);
        table.setIntercellSpacing(new Dimension(0, 0));

        add(new JScrollPane(table), java.awt.BorderLayout.CENTER);
    }
}
