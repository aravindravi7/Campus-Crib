/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

/**
 *
 * @author nidhinair
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import models.ResidentRole;
import models.UserAccount;
import models.WorkRequest;
 
public class ResidentDashboardPanel extends JPanel {
    private DefaultTableModel tableModel;
 
    public ResidentDashboardPanel(UserAccount user, List<WorkRequest> requests, Runnable createRequestAction, Runnable logoutAction) {
        setLayout(new BorderLayout());
 
        JLabel welcomeLabel = new JLabel("Welcome, " + user.getUsername());
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
 
        String[] columns = {"Request ID", "Type", "Description", "Status"};
        tableModel = new DefaultTableModel(columns, 0);
        JTable requestTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(requestTable);
 
        populateTable(user, requests);
 
        JButton createRequestButton = new JButton("Create Work Request");
        createRequestButton.addActionListener(e -> createRequestAction.run());
 
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> logoutAction.run());
 
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(createRequestButton);
        buttonPanel.add(logoutButton);
 
        add(welcomeLabel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
 
    private void populateTable(UserAccount user, List<WorkRequest> requests) {
        tableModel.setRowCount(0); // Clear table data
        for (WorkRequest request : requests) {
            if (user.getRole() instanceof ResidentRole) {
                tableModel.addRow(new Object[]{request.getId(), request.getType(), request.getDescription(), request.getStatus()});
            }
        }
    }
    public void refreshTable(UserAccount user, List<WorkRequest> requests){
        populateTable(user,requests);//refresh the table with updated data
    }
}

