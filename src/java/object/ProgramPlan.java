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
public class ProgramPlan {
    public static final String TABLE_NAME = "ProgramPlan";
    public static final String COLUMN_PROGRAMPLANID = "ProgramPlanID";
    public static final String COLUMN_PROGRAMNAME = "ProgramName";
    public static final String COLUMN_DESCRIPTON = "Description";
    public static final String COLUMN_PROBLEMID = "ProblemID";
    public static final String COLUMN_TYPE = "Type";
    public static final String COLUMN_FLAG = "Flag";
    
    public int programPlanID;
    public String programName;
    public String description;
    public int problemID;
    public String type;
    public int flag;
    public int surveyForm;
    private int DeployedID;
    
    //additional attribute
    public double programTriggerFarmArea;
    public double programTriggerFarmCount;
    public String effectivityStatus;
    public double effectivityRating;
    
    public String programTrigger;
    private int deployCount;
    
    public ProgramPlan() {
        
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
     * @return the programName
     */
    public String getProgramName() {
        return programName;
    }

    /**
     * @param programName the programName to set
     */
    public void setProgramName(String programName) {
        this.programName = programName;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the problemID
     */
    public int getProblemID() {
        return problemID;
    }

    /**
     * @param problemID the problemID to set
     */
    public void setProblemID(int problemID) {
        this.problemID = problemID;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
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

    /**
     * @return the surveyForm
     */
    public int getSurveyForm() {
        return surveyForm;
    }

    /**
     * @param surveyForm the surveyForm to set
     */
    public void setSurveyForm(int surveyForm) {
        this.surveyForm = surveyForm;
    }

    /**
     * @return the programTriggerFarmArea
     */
    public double getProgramTriggerFarmArea() {
        return programTriggerFarmArea;
    }

    /**
     * @param programTriggerFarmArea the programTriggerFarmArea to set
     */
    public void setProgramTriggerFarmArea(double programTriggerFarmArea) {
        this.programTriggerFarmArea = programTriggerFarmArea;
    }

    /**
     * @return the programTriggerFarmCount
     */
    public double getProgramTriggerFarmCount() {
        return programTriggerFarmCount;
    }

    /**
     * @param programTriggerFarmCount the programTriggerFarmCount to set
     */
    public void setProgramTriggerFarmCount(double programTriggerFarmCount) {
        this.programTriggerFarmCount = programTriggerFarmCount;
    }

    /**
     * @return the effectivityStatus
     */
    public String getEffectivityStatus() {
        return effectivityStatus;
    }

    /**
     * @param effectivityStatus the effectivityStatus to set
     */
    public void setEffectivityStatus(String effectivityStatus) {
        this.effectivityStatus = effectivityStatus;
    }

    /**
     * @return the effectivityRating
     */
    public double getEffectivityRating() {
        return effectivityRating;
    }

    /**
     * @param effectivityRating the effectivityRating to set
     */
    public void setEffectivityRating(double effectivityRating) {
        this.effectivityRating = effectivityRating;
    }

    /**
     * @return the programTrigger
     */
    public String getProgramTrigger() {
        return programTrigger;
    }

    /**
     * @param programTrigger the programTrigger to set
     */
    public void setProgramTrigger(String programTrigger) {
        this.programTrigger = programTrigger;
    }

    /**
     * @return the DeployedID
     */
    public int getDeployedID() {
        return DeployedID;
    }

    /**
     * @param DeployedID the DeployedID to set
     */
    public void setDeployedID(int DeployedID) {
        this.DeployedID = DeployedID;
    }

    /**
     * @return the deployCount
     */
    public int getDeployCount() {
        return deployCount;
    }

    /**
     * @param deployCount the deployCount to set
     */
    public void setDeployCount(int deployCount) {
        this.deployCount = deployCount;
    }

}
