/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object;

/**
 *
 * @author RubySenpaii
 */
public class ProgramTrigger {
    public static final String TABLE_NAME = "ProgramTrigger";
    public static final String COLUMN_PROGRAMID = "ProgramID";
    public static final String COLUMN_TRIGGERNAME = "TriggerName";
    public static final String COLUMN_TRIGGERVALUE = "TriggerValue";
    
    private int programID;
    private String triggerName;
    private double triggerValue;
    
    public ProgramTrigger() {
        
    }

    /**
     * @return the programID
     */
    public int getProgramID() {
        return programID;
    }

    /**
     * @param programID the programID to set
     */
    public void setProgramID(int programID) {
        this.programID = programID;
    }

    /**
     * @return the triggerName
     */
    public String getTriggerName() {
        return triggerName;
    }

    /**
     * @param triggerName the triggerName to set
     */
    public void setTriggerName(String triggerName) {
        this.triggerName = triggerName;
    }

    /**
     * @return the triggerValue
     */
    public double getTriggerValue() {
        return triggerValue;
    }

    /**
     * @param triggerValue the triggerValue to set
     */
    public void setTriggerValue(double triggerValue) {
        this.triggerValue = triggerValue;
    }
}
