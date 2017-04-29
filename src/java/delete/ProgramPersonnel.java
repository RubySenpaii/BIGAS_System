/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package delete;

/**
 *
 * @author RubySenpaii
 */
public class ProgramPersonnel {
    public static final String TABLE_NAME = "ProgramPersonnel";
    public static final String COLUMN_DEPLOYEDID = "DeployedID";
    public static final String COLUMN_EMPLOYEEID = "EmployeeID";
    public static final String COLUMN_ROLE = "Role";
    
    private int deployedID;
    private int employeeID;
    private String role;
    
    public ProgramPersonnel() {
        
    }

    /**
     * @return the deployedID
     */
    public int getDeployedID() {
        return deployedID;
    }

    /**
     * @param deployedID the deployedID to set
     */
    public void setDeployedID(int deployedID) {
        this.deployedID = deployedID;
    }

    /**
     * @return the employeeID
     */
    public int getEmployeeID() {
        return employeeID;
    }

    /**
     * @param employeeID the employeeID to set
     */
    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    /**
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }
}
