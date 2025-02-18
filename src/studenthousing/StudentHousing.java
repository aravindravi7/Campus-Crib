/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package studenthousing;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import models.AgentRole;
import models.AmenityBookingRequest;
import models.ApartmentBookingRequest;
import models.BuildingManagerRole;
import models.CarpenterRole;
import models.ElectricianRole;
import models.MaintenanceRequest;
import models.PestControlRole;
import models.PlumberRole;
import models.ResidentRole;
 
import ui.WorkRequestPanel;

import models.UserAccount;
import models.WorkRequest;
import ui.AgentDashboardPanel;
import ui.BuildingManagerDashboardPanel;
import ui.LoginPanel;
import ui.ResidentDashboardPanel;
import ui.RoleDashboardPanel;

public class StudentHousing {
    private static JFrame frame;
    private static List<WorkRequest> requests = new ArrayList<>();
    private static List<UserAccount> userAccounts = new ArrayList<>();
    private static ResidentDashboardPanel residentDashboard;
 
    public static void main(String[] args) {
        // Initialize JFrame
        frame = new JFrame("Housing Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);
 
        // Prepopulate system data
        initializeData();
 
        // Start the application with the login screen
        showLoginPanel();
        // make frame visible
        frame.setVisible(true);
        
    }
 
    private static void initializeData() {
        // Create users
        userAccounts.add(new UserAccount("resident1", "password", new ResidentRole()));
        userAccounts.add(new UserAccount("agent1", "password", new AgentRole()));
        userAccounts.add(new UserAccount("manager1", "password", new BuildingManagerRole()));
        userAccounts.add(new UserAccount("plumber1", "password", new PlumberRole()));
        userAccounts.add(new UserAccount("electrician1", "password", new ElectricianRole()));
        userAccounts.add(new UserAccount("pestcontrol1", "password", new PestControlRole()));
        userAccounts.add(new UserAccount("carpenter1", "password", new CarpenterRole()));
 
        // Prepopulate work requests for demo
        WorkRequest req1 = new AmenityBookingRequest();
        req1.setDescription("Book the gym for 2 hours.");
        requests.add(req1);
 
        WorkRequest req2 = new ApartmentBookingRequest();
        req2.setDescription("Book an apartment with 2 bedrooms.");
        requests.add(req2);
 
        WorkRequest req3 = new MaintenanceRequest("Plumbing");
        req3.setDescription("Fix the leaking kitchen faucet.");
        requests.add(req3);
    }
 
    private static void showLoginPanel() {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(new LoginPanel(frame, userAccounts, StudentHousing::handleLoginSuccess));
        frame.revalidate();
        frame.repaint();
    }
 
    private static void handleLoginSuccess(UserAccount user) {
        if (user.getRole() instanceof ResidentRole) {
            showResidentDashboard(user);
        } else if (user.getRole() instanceof BuildingManagerRole) {
            showBuildingManagerDashboard(user);
        } else if (user.getRole() instanceof AgentRole) {
            showAgentDashboard(user);
        } else if (user.getRole() instanceof PlumberRole){
            showRoleDashboard(user);
        } else if (user.getRole() instanceof CarpenterRole){
            showRoleDashboard(user);
        } else if (user.getRole() instanceof PestControlRole){
            showRoleDashboard(user);
        } else if (user.getRole() instanceof ElectricianRole){
            showRoleDashboard(user);
        }
    }
 
    private static void showResidentDashboard(UserAccount user) {
        if(residentDashboard == null){
            residentDashboard = new ResidentDashboardPanel(user,requests, ()-> showWorkRequestPanel(user),StudentHousing::showLoginPanel);
        }
        else{
            residentDashboard.refreshTable(user,requests);//refresh the table data
        }
        frame.getContentPane().removeAll();
        frame.getContentPane().add(new ResidentDashboardPanel(user, requests, 
            () -> showWorkRequestPanel(user), 
            StudentHousing::showLoginPanel));
        frame.revalidate();
        frame.repaint();
    }
 
    private static void showBuildingManagerDashboard(UserAccount user) {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(new BuildingManagerDashboardPanel(user, requests, StudentHousing::showLoginPanel));
        frame.revalidate();
        frame.repaint();
    }
 
    private static void showAgentDashboard(UserAccount user) {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(new AgentDashboardPanel(user, requests, StudentHousing::showLoginPanel));
        frame.revalidate();
        frame.repaint();
    }
 
    private static void showWorkRequestPanel(UserAccount user) {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(new WorkRequestPanel(user, requests, () -> showResidentDashboard(user)));
        frame.revalidate();
        frame.repaint();
    }
    private static void showRoleDashboard(UserAccount user) {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(new RoleDashboardPanel(user, requests, StudentHousing::showLoginPanel));
        frame.revalidate();
        frame.repaint();
    }
}
