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
import models.UserAccount;
import models.WorkRequest;
 
public class AgentDashboardPanel extends JPanel {
    private DefaultTableModel tableModel;
 
    public AgentDashboardPanel(UserAccount user, List<WorkRequest> requests, Runnable logoutAction) {
        setLayout(new BorderLayout());
 
        JLabel welcomeLabel = new JLabel("Welcome, " + user.getUsername() + " (Agent)");
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
 
        String[] columns = {"Request ID", "Type", "Description", "Status"};
        tableModel = new DefaultTableModel(columns, 0);
        JTable requestTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(requestTable);
 
        populateTable(requests);
 
        JButton updateStatusButton = new JButton("Update Status");
        updateStatusButton.addActionListener(e -> {
            int selectedRow = requestTable.getSelectedRow();
            if (selectedRow >= 0) {
                String newStatus = JOptionPane.showInputDialog("Enter new status (e.g., In Progress, Completed):");
                if (newStatus != null && !newStatus.isEmpty()) {
                    String requestId = (String) tableModel.getValueAt(selectedRow, 0);
                    for (WorkRequest request : requests) {
                        if (request.getId().equals(requestId)) {
                            request.setStatus(newStatus);
                            break;
                        }
                    }
                    refreshTable(requests);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a request to update.");
            }
        });
 
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> logoutAction.run());
 
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(updateStatusButton);
        buttonPanel.add(logoutButton);
 
        add(welcomeLabel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
 
    private void populateTable(List<WorkRequest> requests) {
        tableModel.setRowCount(0);
        for (WorkRequest request : requests) {
            if (request.getType().equalsIgnoreCase("Apartment Booking")) {
                tableModel.addRow(new Object[]{request.getId(), request.getType(), request.getDescription(), request.getStatus()});
            }
        }
    }
 
    private void refreshTable(List<WorkRequest> requests) {
        populateTable(requests);
    }
}
