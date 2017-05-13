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
public class Barangay {
    public static final String TABLE_NAME = "Barangay";
    public static final String COLUMN_BARANGAYID = "BarangayID";
    public static final String COLUMN_MUNICIPALITYID = "MunicipalityID";
    public static final String COLUMN_BARANGAYNAME = "BarangayName";
    public static final String COLUMN_AREA = "Area";
    
    private int barangayID;
    private int municipalityID;
    private String barangayName;
    private double area;
    
    //additional attributes
    private double plantableArea;
    private double plantedArea;
    private double unplantedArea;
    private double minorDamagedArea;
    private double majorDamagedArea;
    private double growth;
    
    private double newlyPlantedArea;
    private double tilleringArea;
    private double reproductiveArea;
    private double harvestingArea;
    
    private double targetProduction;
    private double harvest;
    private int farmCount;
    private int farmAffected;
    
    private DamageIncident damageIncident;
    private String problemName;
    private double areaAffected;
    private double areaDamaged;
    private String escalationLevel;
    
    private String municipalityName;
    private String cropStage;
    
    public Barangay() {
        
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
     * @return the municipalityID
     */
    public int getMunicipalityID() {
        return municipalityID;
    }

    /**
     * @param municipalityID the municipalityID to set
     */
    public void setMunicipalityID(int municipalityID) {
        this.municipalityID = municipalityID;
    }

    /**
     * @return the barangayName
     */
    public String getBarangayName() {
        return barangayName;
    }

    /**
     * @param barangayName the barangayName to set
     */
    public void setBarangayName(String barangayName) {
        this.barangayName = barangayName;
    }

    /**
     * @return the area
     */
    public double getArea() {
        return area;
    }

    /**
     * @param area the area to set
     */
    public void setArea(double area) {
        this.area = area;
    }

    /**
     * @return the plantedArea
     */
    public double getPlantedArea() {
        return plantedArea;
    }

    /**
     * @param plantedArea the plantedArea to set
     */
    public void setPlantedArea(double plantedArea) {
        this.plantedArea = plantedArea;
    }

    /**
     * @return the unplantedArea
     */
    public double getUnplantedArea() {
        return unplantedArea;
    }

    /**
     * @param unplantedArea the unplantedArea to set
     */
    public void setUnplantedArea(double unplantedArea) {
        this.unplantedArea = unplantedArea;
    }

    /**
     * @return the growth
     */
    public double getGrowth() {
        return growth;
    }

    /**
     * @param growth the growth to set
     */
    public void setGrowth(double growth) {
        this.growth = growth;
    }

    /**
     * @return the newlyPlantedArea
     */
    public double getNewlyPlantedArea() {
        return newlyPlantedArea;
    }

    /**
     * @param newlyPlantedArea the newlyPlantedArea to set
     */
    public void setNewlyPlantedArea(double newlyPlantedArea) {
        this.newlyPlantedArea = newlyPlantedArea;
    }

    /**
     * @return the tilleringArea
     */
    public double getTilleringArea() {
        return tilleringArea;
    }

    /**
     * @param tilleringArea the tilleringArea to set
     */
    public void setTilleringArea(double tilleringArea) {
        this.tilleringArea = tilleringArea;
    }

    /**
     * @return the reproductiveArea
     */
    public double getReproductiveArea() {
        return reproductiveArea;
    }

    /**
     * @param reproductiveArea the reproductiveArea to set
     */
    public void setReproductiveArea(double reproductiveArea) {
        this.reproductiveArea = reproductiveArea;
    }

    /**
     * @return the harvestingArea
     */
    public double getHarvestingArea() {
        return harvestingArea;
    }

    /**
     * @param harvestingArea the harvestingArea to set
     */
    public void setHarvestingArea(double harvestingArea) {
        this.harvestingArea = harvestingArea;
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
     * @return the damageIncident
     */
    public DamageIncident getDamageIncident() {
        return damageIncident;
    }

    /**
     * @param damageIncident the damageIncident to set
     */
    public void setDamageIncident(DamageIncident damageIncident) {
        this.damageIncident = damageIncident;
    }

    /**
     * @return the escalationLevel
     */
    public String getEscalationLevel() {
        return escalationLevel;
    }

    /**
     * @param escalationLevel the escalationLevel to set
     */
    public void setEscalationLevel(String escalationLevel) {
        this.escalationLevel = escalationLevel;
    }

    /**
     * @return the plantableArea
     */
    public double getPlantableArea() {
        return plantableArea;
    }

    /**
     * @param plantableArea the plantableArea to set
     */
    public void setPlantableArea(double plantableArea) {
        this.plantableArea = plantableArea;
    }

    /**
     * @return the minorDamagedArea
     */
    public double getMinorDamagedArea() {
        return minorDamagedArea;
    }

    /**
     * @param minorDamagedArea the minorDamagedArea to set
     */
    public void setMinorDamagedArea(double minorDamagedArea) {
        this.minorDamagedArea = minorDamagedArea;
    }

    /**
     * @return the majorDamagedArea
     */
    public double getMajorDamagedArea() {
        return majorDamagedArea;
    }

    /**
     * @param majorDamagedArea the majorDamagedArea to set
     */
    public void setMajorDamagedArea(double majorDamagedArea) {
        this.majorDamagedArea = majorDamagedArea;
    }

    /**
     * @return the municipalityName
     */
    public String getMunicipalityName() {
        return municipalityName;
    }

    /**
     * @param municipalityName the municipalityName to set
     */
    public void setMunicipalityName(String municipalityName) {
        this.municipalityName = municipalityName;
    }

    /**
     * @return the cropStage
     */
    public String getCropStage() {
        return cropStage;
    }

    /**
     * @param cropStage the cropStage to set
     */
    public void setCropStage(String cropStage) {
        this.cropStage = cropStage;
    }

    /**
     * @return the targetProduction
     */
    public double getTargetProduction() {
        return targetProduction;
    }

    /**
     * @param targetProduction the targetProduction to set
     */
    public void setTargetProduction(double targetProduction) {
        this.targetProduction = targetProduction;
    }

    /**
     * @return the farmAffected
     */
    public int getFarmAffected() {
        return farmAffected;
    }

    /**
     * @param farmAffected the farmAffected to set
     */
    public void setFarmAffected(int farmAffected) {
        this.farmAffected = farmAffected;
    }
}
