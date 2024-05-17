package com.example.rest_vision.model.Gestion_Personnel;

public class EmployesRoles {
    private int employeID;
    private int roleID;

    // Constructeur
    public EmployesRoles(int employeID, int roleID) {
        this.employeID = employeID;
        this.roleID = roleID;
    }

    // Getters et setters
    public int getEmployeID() {
        return employeID;
    }

    public void setEmployeID(int employeID) {
        this.employeID = employeID;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }
}

