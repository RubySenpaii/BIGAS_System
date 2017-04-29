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
public class Municipality {
    public static final String TABLE_NAME = "Municipality";
    public static final String COLUMN_MUNICIPALITYID = "MunicipalityID";
    public static final String COLUMN_MUNICIPALITYNAME = "MunicipalityName";
    public static final String COLUMN_DISTRICT = "District";
    public static final String COLUMN_AREA = "Area";
    public static final String COLUMN_LATITUDE = "Latitude";
    public static final String COLUMN_LONGITUDE = "Longitude";
    
    private int municipalityID;
    private String municipalityName;
    private int district;
    private double area;
    private double latitude;
    private double longitude;
    
    //additional attributes
    private double harvestedTotal;
    
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
    
    private int farmCount;
    
    private DamageIncident damageIncident;
    private String problemName;
    private double areaAffected;
    private double areaDamaged;
    private String escalationLevel;
    
    public Municipality() {
        
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
     * @return the district
     */
    public int getDistrict() {
        return district;
    }

    /**
     * @param district the district to set
     */
    public void setDistrict(int district) {
        this.district = district;
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
     * @return the harvestedTotal
     */
    public double getHarvestedTotal() {
        return harvestedTotal;
    }

    /**
     * @param harvestedTotal the harvestedTotal to set
     */
    public void setHarvestedTotal(double harvestedTotal) {
        this.harvestedTotal = harvestedTotal;
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
}
