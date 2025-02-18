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
import models.UserAccount;
 
public class LoginPanel extends JPanel {
    public LoginPanel(JFrame mainFrame, List<UserAccount> userAccounts, java.util.function.Consumer<UserAccount> onLoginSuccess) {
        setLayout(new GridLayout(4, 2, 10, 10));
 
        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField();
 
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();
 
        JButton loginButton = new JButton("Login");
        JLabel errorLabel = new JLabel("", SwingConstants.CENTER);
        errorLabel.setForeground(Color.RED);
 
        loginButton.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();
 
            // Validate user credentials
            UserAccount loggedInUser = validateUser(username, password, userAccounts);
            if (loggedInUser != null) {
                // Redirect to the role-specific dashboard
                onLoginSuccess.accept(loggedInUser);
            } else {
                // Show error message for invalid credentials
                errorLabel.setText("Invalid username or password!");
            }
        });
 
        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(loginButton);
        add(errorLabel);
    }
 
    private UserAccount validateUser(String username, String password, List<UserAccount> userAccounts) {
        for (UserAccount user : userAccounts) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user; // Return the matching user account
            }
        }
        return null; // No match found
    }
}