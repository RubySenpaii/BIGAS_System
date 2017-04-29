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
public class FarmAssignment {
    
    public static final String TABLE_NAME = "FarmAssignment";
    public static final String COLUMN_TECHNICIAN = "Technician";
    public static final String COLUMN_ASSIGNEDFARM = "AssignedFarm";
    public static final String COLUMN_FLAG = "Flag";
    
    private int technician;
    private int assignedFarm;
    private int flag;
    
    public FarmAssignment() {
        
    }

    /**
     * @return the technician
     */
    public int getTechnician() {
        return technician;
    }

    /**
     * @param technician the technician to set
     */
    public void setTechnician(int technician) {
        this.technician = technician;
    }

    /**
     * @return the assignedFarm
     */
    public int getAssignedFarm() {
        return assignedFarm;
    }

    /**
     * @param assignedFarm the assignedFarm to set
     */
    public void setAssignedFarm(int assignedFarm) {
        this.assignedFarm = assignedFarm;
    }

    /**
     * @return the flag
     */
    public int getFlag() {
        return flag;
    }

    /**
     * @param flag the flag to set
     */
    public void setFlag(int flag) {
        this.flag = flag;
    }
}
