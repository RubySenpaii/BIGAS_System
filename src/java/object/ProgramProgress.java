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
public class ProgramProgress {
    public static final String TABLE_NAME = "ProgramProgress";
    public static final String COLUMN_DEPLOYEDID = "DeployedID";
    public static final String COLUMN_UPDATEDBY = "UpdatedBy";
    public static final String COLUMN_PROCEDURENUMBER = "ProcedureNumber";
    public static final String COLUMN_DATECOMPLETED = "DateCompleted";
    public static final String COLUMN_IMAGE = "Image";
    public static final String COLUMN_REMARKS = "Remarks";
    
    private int deployedID;
    private int updatedBy;
    private int procedureNumber;
    private String dateCompleted;
    private String image;
    private String remarks;
    
    //additional attributes
    private String phase;
    private String activity;
    private String updatedByName;
    
    public ProgramProgress() {
        
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
     * @return the updatedBy
     */
    public int getUpdatedBy() {
        return updatedBy;
    }

    /**
     * @param updatedBy the updatedBy to set
     */
    public void setUpdatedBy(int updatedBy) {
        this.updatedBy = updatedBy;
    }

    /**
     * @return the procedureNumber
     */
    public int getProcedureNumber() {
        return procedureNumber;
    }

    /**
     * @param procedureNumber the procedureNumber to set
     */
    public void setProcedureNumber(int procedureNumber) {
        this.procedureNumber = procedureNumber;
    }

    /**
     * @return the dateCompleted
     */
    public String getDateCompleted() {
        return dateCompleted;
    }

    /**
     * @param dateCompleted the dateCompleted to set
     */
    public void setDateCompleted(String dateCompleted) {
        this.dateCompleted = dateCompleted;
    }

    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
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

    /**
     * @return the updatedByName
     */
    public String getUpdatedByName() {
        return updatedByName;
    }

    /**
     * @param updatedByName the updatedByName to set
     */
    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    /**
     * @return the remarks
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * @param remarks the remarks to set
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
