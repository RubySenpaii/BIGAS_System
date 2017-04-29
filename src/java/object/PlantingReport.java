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
public class PlantingReport {
    public static final String TABLE_NAME = "PlantingReport";
    public static final String COLUMN_PLANTINGREPORTID = "PlantingReportID";
    public static final String COLUMN_PLOTID = "PlotID";
    public static final String COLUMN_SEASON =  "Season";
    public static final String COLUMN_PLANTEDYEAR = "PlantedYear";
    public static final String COLUMN_PLANTEDMONTH = "PlantedMonth";
    public static final String COLUMN_PLANTEDDAY = "PlantedDay";
    public static final String COLUMN_PLANTINGMETHOD = "PlantingMethod";
    public static final String COLUMN_SEEDVARIETY = "SeedVariety";
    public static final String COLUMN_SEEDTYPE = "SeedType";
    public static final String COLUMN_AMOUNTPLANTED = "AmountPlanted";
    public static final String COLUMN_SEEDACQUISITION = "SeedAcquisition";
    public static final String COLUMN_AMOUNTHARVESTED = "AmountHarvested";
    public static final String COLUMN_HARVESTYEAR = "HarvestYear";
    public static final String COLUMN_HARVESTMONTH = "HarvestMonth";
    public static final String COLUMN_HARVESTDAY = "HarvestDay";
    public static final String COLUMN_REPORTEDBY = "ReportedBy";
    
    private int plantingReportID;
    private int plotID;
    private String season;
    private int plantedYear;
    private int plantedMonth;
    private int plantedDay;
    private String plantingMethod;
    private String seedVariety;
    private String seedType;
    private double amountPlanted;
    private String seedAcquisition;
    private double amountHarvested;
    private int harvestYear;
    private int harvestMonth;
    private int harvestDay;
    private int reportedBy;
    
    //additional attributes
    private String stage;
    private double areaDamaged;
    
    private String farmName;
    private String barangayName;
    private String municipalityName;
    private double plantedArea;
    private String irrigationMethod;
    private String landElevation;
    
    public PlantingReport() {
        
    }

    /**
     * @return the plantingReportID
     */
    public int getPlantingReportID() {
        return plantingReportID;
    }

    /**
     * @param plantingReportID the plantingReportID to set
     */
    public void setPlantingReportID(int plantingReportID) {
        this.plantingReportID = plantingReportID;
    }

    /**
     * @return the plotID
     */
    public int getPlotID() {
        return plotID;
    }

    /**
     * @param plotID the plotID to set
     */
    public void setPlotID(int plotID) {
        this.plotID = plotID;
    }

    /**
     * @return the season
     */
    public String getSeason() {
        return season;
    }

    /**
     * @param season the season to set
     */
    public void setSeason(String season) {
        this.season = season;
    }

    /**
     * @return the plantedYear
     */
    public int getPlantedYear() {
        return plantedYear;
    }

    /**
     * @param plantedYear the plantedYear to set
     */
    public void setPlantedYear(int plantedYear) {
        this.plantedYear = plantedYear;
    }

    /**
     * @return the plantedMonth
     */
    public int getPlantedMonth() {
        return plantedMonth;
    }

    /**
     * @param plantedMonth the plantedMonth to set
     */
    public void setPlantedMonth(int plantedMonth) {
        this.plantedMonth = plantedMonth;
    }

    /**
     * @return the plantedDay
     */
    public int getPlantedDay() {
        return plantedDay;
    }

    /**
     * @param plantedDay the plantedDay to set
     */
    public void setPlantedDay(int plantedDay) {
        this.plantedDay = plantedDay;
    }

    /**
     * @return the plantingMethod
     */
    public String getPlantingMethod() {
        return plantingMethod;
    }

    /**
     * @param plantingMethod the plantingMethod to set
     */
    public void setPlantingMethod(String plantingMethod) {
        this.plantingMethod = plantingMethod;
    }

    /**
     * @return the seedVariety
     */
    public String getSeedVariety() {
        return seedVariety;
    }

    /**
     * @param seedVariety the seedVariety to set
     */
    public void setSeedVariety(String seedVariety) {
        this.seedVariety = seedVariety;
    }

    /**
     * @return the seedType
     */
    public String getSeedType() {
        return seedType;
    }

    /**
     * @param seedType the seedType to set
     */
    public void setSeedType(String seedType) {
        this.seedType = seedType;
    }

    /**
     * @return the amountPlanted
     */
    public double getAmountPlanted() {
        return amountPlanted;
    }

    /**
     * @param amountPlanted the amountPlanted to set
     */
    public void setAmountPlanted(double amountPlanted) {
        this.amountPlanted = amountPlanted;
    }

    /**
     * @return the seedAcquisition
     */
    public String getSeedAcquisition() {
        return seedAcquisition;
    }

    /**
     * @param seedAcquisition the seedAcquisition to set
     */
    public void setSeedAcquisition(String seedAcquisition) {
        this.seedAcquisition = seedAcquisition;
    }

    /**
     * @return the amountHarvested
     */
    public double getAmountHarvested() {
        return amountHarvested;
    }

    /**
     * @param amountHarvested the amountHarvested to set
     */
    public void setAmountHarvested(double amountHarvested) {
        this.amountHarvested = amountHarvested;
    }

    /**
     * @return the harvestYear
     */
    public int getHarvestYear() {
        return harvestYear;
    }

    /**
     * @param harvestYear the harvestYear to set
     */
    public void setHarvestYear(int harvestYear) {
        this.harvestYear = harvestYear;
    }

    /**
     * @return the harvestMonth
     */
    public int getHarvestMonth() {
        return harvestMonth;
    }

    /**
     * @param harvestMonth the harvestMonth to set
     */
    public void setHarvestMonth(int harvestMonth) {
        this.harvestMonth = harvestMonth;
    }

    /**
     * @return the harvestDay
     */
    public int getHarvestDay() {
        return harvestDay;
    }

    /**
     * @param harvestDay the harvestDay to set
     */
    public void setHarvestDay(int harvestDay) {
        this.harvestDay = harvestDay;
    }

    /**
     * @return the reportedBy
     */
    public int getReportedBy() {
        return reportedBy;
    }

    /**
     * @param reportedBy the reportedBy to set
     */
    public void setReportedBy(int reportedBy) {
        this.reportedBy = reportedBy;
    }

    /**
     * @return the stage
     */
    public String getStage() {
        return stage;
    }

    /**
     * @param stage the stage to set
     */
    public void setStage(String stage) {
        this.stage = stage;
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
}
