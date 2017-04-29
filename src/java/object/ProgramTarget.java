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
public class ProgramTarget {
    public static final String TABLE_NAME = "ProgramTarget";
    public static final String COLUMN_PROGRAMPLANID = "ProgramPlanID";
    public static final String COLUMN_TARGETNAME = "TargetName";
    public static final String COLUMN_TARGETVALUE = "TargetValue";
    public static final String COLUMN_ACTUALVALUE = "ActualValue";
    public static final String COLUMN_TARGETYEAR = "TargetYear";
    
    private int programPlanID;
    private String targetName;
    private double targetValue;
    private double actualValue;
    private int targetYear;
    
    public ProgramTarget() {
        
    }

    /**
     * @return the targetName
     */
    public String getTargetName() {
        return targetName;
    }

    /**
     * @param targetName the targetName to set
     */
    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    /**
     * @return the targetValue
     */
    public double getTargetValue() {
        return targetValue;
    }

    /**
     * @param targetValue the targetValue to set
     */
    public void setTargetValue(double targetValue) {
        this.targetValue = targetValue;
    }

    /**
     * @return the actualValue
     */
    public double getActualValue() {
        return actualValue;
    }

    /**
     * @param actualValue the actualValue to set
     */
    public void setActualValue(double actualValue) {
        this.actualValue = actualValue;
    }

    /**
     * @return the programPlanID
     */
    public int getProgramPlanID() {
        return programPlanID;
    }

    /**
     * @param programPlanID the programPlanID to set
     */
    public void setProgramPlanID(int programPlanID) {
        this.programPlanID = programPlanID;
    }

    /**
     * @return the targetYear
     */
    public int getTargetYear() {
        return targetYear;
    }

    /**
     * @param targetYear the targetYear to set
     */
    public void setTargetYear(int targetYear) {
        this.targetYear = targetYear;
    }
}
