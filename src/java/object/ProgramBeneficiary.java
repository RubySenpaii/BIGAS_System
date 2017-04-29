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
public class ProgramBeneficiary {
    public static final String TABLE_NAME = "ProgramBeneficiary";
    public static final String COLUMN_DEPLOYEDID = "DeployedID";
    public static final String COLUMN_FARMID = "FarmID";
    public static final String COLUMN_VARIETYRECEIVED = "VarietyReceived";
    public static final String COLUMN_FERTILIZERRECEIVED = "FertilizerReceived";
    public static final String COLUMN_DATERECEIVED = "DateReceived";
    
    private int deployedID;
    private int farmID;
    private double varietyReceived;
    private double fertilizerReceived;
    private String dateReceived;
    
    //additional attribute
    private String farmName;
    private String barangay;
    private double areaAffected;
    private double areaDamaged;
    private int incidentID;
    
    public ProgramBeneficiary() {
        
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
     * @return the farmName
     */
    public String getFarmName() {
        return farmName;
    }

    /**
     * @param farmName the farmName to set
     */
    public void setFarmName(String farmName) {
        this.farmName = farmName;
    }

    /**
     * @return the barangay
     */
    public String getBarangay() {
        return barangay;
    }

    /**
     * @param barangay the barangay to set
     */
    public void setBarangay(String barangay) {
        this.barangay = barangay;
    }

    /**
     * @return the areaAffected
     */
    public double getAreaAffected() {
        return areaAffected;
    }

    /**
     * @param areaAffected the areaAffected to set
     */
    public void setAreaAffected(double areaAffected) {
        this.areaAffected = areaAffected;
    }

    /**
     * @return the areaDamaged
     */
    public double getAreaDamaged() {
        return areaDamaged;
    }

    /**
     * @param areaDamaged the areaDamaged to set
     */
    public void setAreaDamaged(double areaDamaged) {
        this.areaDamaged = areaDamaged;
    }

    /**
     * @return the incidentID
     */
    public int getIncidentID() {
        return incidentID;
    }

    /**
     * @param incidentID the incidentID to set
     */
    public void setIncidentID(int incidentID) {
        this.incidentID = incidentID;
    }
}
