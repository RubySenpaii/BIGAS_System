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
public class DeployedProgram {
    public static final String TABLE_NAME = "DeployedProgram";
    public static final String COLUMN_DEPLOYEDID = "DeployedID";
    public static final String COLUMN_PROGRAMPLANID = "ProgramPlanID";
    public static final String COLUMN_ASSIGNEDMUNICIPALITY = "AssignedMunicipality";
    public static final String COLUMN_VARIETYPROVIDED = "VarietyProvided";
    public static final String COLUMN_VARIETYAMOUNT = "VarietyAmount";
    public static final String COLUMN_FERTILIZERPROVIDED = "FertilizerProvided";
    public static final String COLUMN_FERTILIZERAMOUNT = "FertilizerAmount";
    public static final String COLUMN_DATESTARTED = "DateStarted";
    public static final String COLUMN_DATECOMPLETED = "DateCompleted";
    public static final String COLUMN_STATUS = "DeployedStatus";
    public static final String COLUMN_CONDITION = "DeployedCondition";
    
    private int deployedID;
    private int programPlanID;
    private int assignedMunicipality;
    private String varietyProvided;
    private double varietyAmount;
    private String fertilizerProvided;
    private double fertilizerAmount;
    private String dateStarted;
    private String dateCompleted;
    private String status;
    
    //additional attributes
    private String programName;
    private int damageIncidentID;
    private String programType;
    private String municipalName;
    private int farmCount;
    
    private int farmID;
    private double varietyReceived;
    private double fertilizerReceived;
    private String dateReceived;
    
    private String effectivityResult;
    
    public DeployedProgram() {
        
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
     * @return the assignedMunicipality
     */
    public int getAssignedMunicipality() {
        return assignedMunicipality;
    }

    /**
     * @param assignedMunicipality the assignedMunicipality to set
     */
    public void setAssignedMunicipality(int assignedMunicipality) {
        this.assignedMunicipality = assignedMunicipality;
    }

    /**
     * @return the varietyProvided
     */
    public String getVarietyProvided() {
        return varietyProvided;
    }

    /**
     * @param varietyProvided the varietyProvided to set
     */
    public void setVarietyProvided(String varietyProvided) {
        this.varietyProvided = varietyProvided;
    }

    /**
     * @return the varietyAmount
     */
    public double getVarietyAmount() {
        return varietyAmount;
    }

    /**
     * @param varietyAmount the varietyAmount to set
     */
    public void setVarietyAmount(double varietyAmount) {
        this.varietyAmount = varietyAmount;
    }

    /**
     * @return the fertilizerProvided
     */
    public String getFertilizerProvided() {
        return fertilizerProvided;
    }

    /**
     * @param fertilizerProvided the fertilizerProvided to set
     */
    public void setFertilizerProvided(String fertilizerProvided) {
        this.fertilizerProvided = fertilizerProvided;
    }

    /**
     * @return the fertilizerAmount
     */
    public double getFertilizerAmount() {
        return fertilizerAmount;
    }

    /**
     * @param fertilizerAmount the fertilizerAmount to set
     */
    public void setFertilizerAmount(double fertilizerAmount) {
        this.fertilizerAmount = fertilizerAmount;
    }

    /**
     * @return the dateStarted
     */
    public String getDateStarted() {
        return dateStarted;
    }

    /**
     * @param dateStarted the dateStarted to set
     */
    public void setDateStarted(String dateStarted) {
        this.dateStarted = dateStarted;
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
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
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
     * @return the damageIncidentID
     */
    public int getDamageIncidentID() {
        return damageIncidentID;
    }

    /**
     * @param damageIncidentID the damageIncidentID to set
     */
    public void setDamageIncidentID(int damageIncidentID) {
        this.damageIncidentID = damageIncidentID;
    }

    /**
     * @return the programType
     */
    public String getProgramType() {
        return programType;
    }

    /**
     * @param programType the programType to set
     */
    public void setProgramType(String programType) {
        this.programType = programType;
    }

    /**
     * @return the municipalName
     */
    public String getMunicipalName() {
        return municipalName;
    }

    /**
     * @param municipalName the municipalName to set
     */
    public void setMunicipalName(String municipalName) {
        this.municipalName = municipalName;
    }

    /**
     * @return the farmCount
     */
    public int getFarmCount() {
        return farmCount;
    }

    /**
     * @param farmCount the farmCount to set
     */
    public void setFarmCount(int farmCount) {
        this.farmCount = farmCount;
    }

    /**
     * @return the farmID
     */
    public int getFarmID() {
        return farmID;
    }

    /**
     * @param farmID the farmID to set
     */
    public void setFarmID(int farmID) {
        this.farmID = farmID;
    }

    /**
     * @return the varietyReceived
     */
    public double getVarietyReceived() {
        return varietyReceived;
    }

    /**
     * @param varietyReceived the varietyReceived to set
     */
    public void setVarietyReceived(double varietyReceived) {
        this.varietyReceived = varietyReceived;
    }

    /**
     * @return the fertilizerReceived
     */
    public double getFertilizerReceived() {
        return fertilizerReceived;
    }

    /**
     * @param fertilizerReceived the fertilizerReceived to set
     */
    public void setFertilizerReceived(double fertilizerReceived) {
        this.fertilizerReceived = fertilizerReceived;
    }

    /**
     * @return the dateReceived
     */
    public String getDateReceived() {
        return dateReceived;
    }

    /**
     * @param dateReceived the dateReceived to set
     */
    public void setDateReceived(String dateReceived) {
        this.dateReceived = dateReceived;
    }

    /**
     * @return the effectivityResult
     */
    public String getEffectivityResult() {
        return effectivityResult;
    }

    /**
     * @param effectivityResult the effectivityResult to set
     */
    public void setEffectivityResult(String effectivityResult) {
        this.effectivityResult = effectivityResult;
    }
}
