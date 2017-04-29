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
public class ProgramProcedure {
    public static final String TABLE_NAME = "ProgramProcedure";
    public static final String COLUMN_PROGRAMPLANID = "ProgramPlanID";
    public static final String COLUMN_PROCEDURENO = "ProcedureNo";
    public static final String COLUMN_PHASE = "Phase";
    public static final String COLUMN_ACTIVITY = "Activity";
    
    private int programPlanID;
    private int procedureNo;
    private String phase;
    private String activity;
    
    public ProgramProcedure() {
        
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
     * @return the procedureNo
     */
    public int getProcedureNo() {
        return procedureNo;
    }

    /**
     * @param procedureNo the procedureNo to set
     */
    public void setProcedureNo(int procedureNo) {
        this.procedureNo = procedureNo;
    }

    /**
     * @return the phase
     */
    public String getPhase() {
        return phase;
    }

    /**
     * @param phase the phase to set
     */
    public void setPhase(String phase) {
        this.phase = phase;
    }

    /**
     * @return the activity
     */
    public String getActivity() {
        return activity;
    }

    /**
     * @param activity the activity to set
     */
    public void setActivity(String activity) {
        this.activity = activity;
    }
}
