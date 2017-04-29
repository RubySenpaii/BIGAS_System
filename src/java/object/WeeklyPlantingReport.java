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
public class WeeklyPlantingReport {
    public static final String TABLE_NAME = "WeeklyPlantingReport";
    public static final String COLUMN_PLANTINGREPORTID = "PlantingReportID";
    public static final String COLUMN_CROPSTAGE = "CropStage";
    public static final String COLUMN_DATEREPORTED = "DateReported";
    public static final String COLUMN_IMAGE = "Image";
    public static final String COLUMN_WATERLEVEL = "WaterLevel";
    public static final String COLUMN_HEIGHT = "Height";
    public static final String COLUMN_AREAPLANTED = "AreaPlanted";
    
    private int plantingReportID;
    private String cropStage;
    private String dateReported;
    private String image;
    private double waterLevel;
    private double height;
    private double areaPlanted;
    
    public WeeklyPlantingReport() {
        
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
     * @return the dateReported
     */
    public String getDateReported() {
        return dateReported;
    }

    /**
     * @param dateReported the dateReported to set
     */
    public void setDateReported(String dateReported) {
        this.dateReported = dateReported;
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
     * @return the waterLevel
     */
    public double getWaterLevel() {
        return waterLevel;
    }

    /**
     * @param waterLevel the waterLevel to set
     */
    public void setWaterLevel(double waterLevel) {
        this.waterLevel = waterLevel;
    }

    /**
     * @return the height
     */
    public double getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * @return the areaPlanted
     */
    public double getAreaPlanted() {
        return areaPlanted;
    }

    /**
     * @param areaPlanted the areaPlanted to set
     */
    public void setAreaPlanted(double areaPlanted) {
        this.areaPlanted = areaPlanted;
    }
}
