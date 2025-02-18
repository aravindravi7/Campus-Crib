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
import java.awt.*;
import java.util.List;
import models.AmenityBookingRequest;
import models.ApartmentBookingRequest;
import models.MaintenanceRequest;
import models.UserAccount;
import models.WorkRequest;
 
public class WorkRequestPanel extends JPanel {
    public WorkRequestPanel(UserAccount user, List<WorkRequest> requests, Runnable onBackToDashboard) {
        setLayout(new GridLayout(4, 2));
 
        JLabel typeLabel = new JLabel("Request Type:");
        JComboBox<String> typeDropdown = new JComboBox<>(new String[]{
                "Amenity Booking", "Apartment Booking", "Plumbing", "Electrical", "Carpenter", "Pest Control"
        });
 
        JLabel descLabel = new JLabel("Description:");
        JTextField descField = new JTextField();
 
        JButton submitButton = new JButton("Submit");
        JLabel statusLabel = new JLabel("", SwingConstants.CENTER);
        statusLabel.setForeground(Color.RED);
 
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> onBackToDashboard.run());
 
        submitButton.addActionListener(e -> {
            String type = (String) typeDropdown.getSelectedItem();
            String description = descField.getText();
 
            if (type == null || type.isEmpty()) {
                statusLabel.setText("Please select a request type.");
                return;
            }
 
            if (description == null || description.isEmpty()) {
                statusLabel.setText("Description cannot be empty.");
                return;
            }
 
            WorkRequest request;
            if (type.equalsIgnoreCase("Amenity Booking")) {
                request = new AmenityBookingRequest();
            } else if (type.equalsIgnoreCase("Apartment Booking")) {
                request = new ApartmentBookingRequest();
            } else if(List.of("Plumbing","Electrical","Carpenter","Pest Control").contains(type)){
                request = new MaintenanceRequest(type);
            } else{
                statusLabel.setText("Invalid request type selected. ");
                return;
            }
            request.setDescription(description);
            requests.add(request);
 
            statusLabel.setText("Request submitted successfully!");
            descField.setText(""); // Clear the description field
        });
 
        add(typeLabel);
        add(typeDropdown);
        add(descLabel);
        add(descField);
        add(submitButton);
        add(statusLabel);
        add(backButton);
    }
}
