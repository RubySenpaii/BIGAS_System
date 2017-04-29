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
public class Farm {
    public static final String TABLE_NAME = "Farm";
    public static final String COLUMN_FARMID = "FarmID";
    public static final String COLUMN_BARANGAYID = "BarangayID";
    public static final String COLUMN_FARMNAME = "FarmName";
    public static final String COLUMN_ADDRESS = "Address";
    public static final String COLUMN_FARMABLEAREA = "FarmableArea";
    public static final String COLUMN_IRRIGATIONMETHOD = "IrrigationMethod";
    public static final String COLUMN_LANDELEVATION = "LandElevation";
    public static final String COLUMN_LONGITUDE = "Longitude";
    public static final String COLUMN_LATITUDE = "Latitude";
    public static final String COLUMN_DATEVISITED = "DateVisited";
    public static final String COLUMN_FLAG = "Flag";
    public static final String COLUMN_ASSIGNEDTECHNICIAN = "AssignedTechnician";
    
    private int farmID;
    private int barangayID;
    private String farmName;
    private String address;
    private double farmableArea;
    private String irrigationMethod;
    private String landElevation;
    private double longitude;
    private double latitude;
    private String dateVisited;
    private int flag;
    private int assignedTechnician;
    
    //additional attribute
    private String technicianName;
    private int damageIncidentID;
    private String problemName;
    private double areaAffected;
    private double areaDamaged;
    
    private double harvest;
    
    public Farm() {
        
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
     * @return the barangayID
     */
    public int getBarangayID() {
        return barangayID;
    }

    /**
     * @param barangayID the barangayID to set
     */
    public void setBarangayID(int barangayID) {
        this.barangayID = barangayID;
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
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the farmableArea
     */
    public double getFarmableArea() {
        return farmableArea;
    }

    /**
     * @param farmableArea the farmableArea to set
     */
    public void setFarmableArea(double farmableArea) {
        this.farmableArea = farmableArea;
    }

    /**
     * @return the irrigationMethod
     */
    public String getIrrigationMethod() {
        return irrigationMethod;
    }

    /**
     * @param irrigationMethod the irrigationMethod to set
     */
    public void setIrrigationMethod(String irrigationMethod) {
        this.irrigationMethod = irrigationMethod;
    }

    /**
     * @return the landElevation
     */
    public String getLandElevation() {
        return landElevation;
    }

    /**
     * @param landElevation the landElevation to set
     */
    public void setLandElevation(String landElevation) {
        this.landElevation = landElevation;
    }

    /**
     * @return the longitude
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * @param longitude the longitude to set
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * @return the latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * @return the dateVisited
     */
    public String getDateVisited() {
        return dateVisited;
    }

    /**
     * @param dateVisited the dateVisited to set
     */
    public void setDateVisited(String dateVisited) {
        this.dateVisited = dateVisited;
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
     * @return the assignedTechnician
     */
    public int getAssignedTechnician() {
        return assignedTechnician;
    }

    /**
     * @param assignedTechnician the assignedTechnician to set
     */
    public void setAssignedTechnician(int assignedTechnician) {
        this.assignedTechnician = assignedTechnician;
    }

    /**
     * @return the problemName
     */
    public String getProblemName() {
        return problemName;
    }

    /**
     * @param problemName the problemName to set
     */
    public void setProblemName(String problemName) {
        this.problemName = problemName;
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
     * @return the harvest
     */
    public double getHarvest() {
        return harvest;
    }

    /**
     * @param harvest the harvest to set
     */
    public void setHarvest(double harvest) {
        this.harvest = harvest;
    }

    /**
     * @return the technicianName
     */
    public String getTechnicianName() {
        return technicianName;
    }

    /**
     * @param technicianName the technicianName to set
     */
    public void setTechnicianName(String technicianName) {
        this.technicianName = technicianName;
    }
}
