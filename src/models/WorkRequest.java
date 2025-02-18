/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import utilities.UniqueIdGenerator;

/**
 *
 * @author nidhinair
 */
public abstract class WorkRequest {
    private String id;
    private String type;
    private String description;
    private String status;
    private String responsibleRole; // Null if not role-specific
    private boolean isRoleRestricted; // True for maintenance requests
 
    public WorkRequest(String type, String responsibleRole, boolean isRoleRestricted) {
        this.id = UniqueIdGenerator.generateId();
        this.type = type;
        this.description = "";
        this.status = "Pending";
        this.responsibleRole = responsibleRole;
        this.isRoleRestricted = isRoleRestricted;
    }
 
    public String getId() {
        return id;
    }
 
    public String getType() {
        return type;
    }
 
    public String getDescription() {
        return description;
    }
 
    public void setDescription(String description) {
        this.description = description;
    }
 
    public String getStatus() {
        return status;
    }
 
    public void setStatus(String status) {
        this.status = status;
    }
 
    public String getResponsibleRole() {
        return responsibleRole;
    }
 
    public boolean isRoleRestricted() {
        return isRoleRestricted;
    }
}
