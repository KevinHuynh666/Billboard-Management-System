package client.panels;

import client.components.SelectableTable;
import client.components.SelectableTableModel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

public class UserPanel extends JPanel {
    int selected;
    JButton addButton = new JButton("New User");
    Container pagination = new Container();
    JButton first = new JButton("<<");
    JButton prev = new JButton("<");
    JButton next = new JButton(">");
    JButton last = new JButton(">>");

    private String[] columnNames = {"Id", "Username", "Create Billboards", "Edit Billboards", "Schedule Billboards", "Edit Users", "View Billboards"};

    private Object[][] data = {
        {0, "User 1", false, false, false, false, false},
        {1, "User 2", true, true, true, true, true},
    };

    SelectableTableModel model = new SelectableTableModel(data, columnNames);
    SelectableTable table = new SelectableTable(model, new ListSelectionListener() {
        public void valueChanged(ListSelectionEvent e) {
            selected = (int) table.getValueAt(table.getSelectedRow(), 0);
            System.out.println(selected);
        }
    });

    JScrollPane scrollPane = new JScrollPane(table);


    public UserPanel() {
        setLayout(new GridBagLayout());
        pagination.setLayout(new GridLayout());
        addComponentsToContainer();
    }

    public void addComponentsToContainer() {
        GridBagConstraints gbc = new GridBagConstraints();
        Insets i = new Insets(5, 5, 5, 5);
        gbc.insets = i;
        gbc.fill = GridBagConstraints.CENTER;
        gbc.gridwidth = 1;

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(addButton, gbc);

        GridBagConstraints table_gbc = new GridBagConstraints();

        table_gbc.weightx = 1.0;
        table_gbc.weighty = 1.0;
        table_gbc.gridx = 0;
        table_gbc.gridy = 1;
        table_gbc.gridwidth = 6;
        table_gbc.fill = GridBagConstraints.BOTH;
        add(scrollPane, table_gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        pagination.add(first, gbc);

        gbc.gridx = 1;
        pagination.add(prev, gbc);

        gbc.gridx = 3;
        pagination.add(next, gbc);

        gbc.gridx = 4;
        pagination.add(last, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 4;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.CENTER;
        add(pagination, gbc);

    }
}