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
public class FertilizerRecommendation {
    public static final String TABLE_NAME = "FertilizerRecommendation";
    public static final String COLUMN_SOILANALYSISNUMBER = "SoilAnalysisNumber";
    public static final String COLUMN_APPLICATIONMETHOD = "ApplicationMethod";
    public static final String COLUMN_SEASON = "Season";
    public static final String COLUMN_FERTILIZERID = "FertilizerName";
    
    private int soilAnalysisNumber;
    private String applicationMethod;
    private String season;
    private String fertilizerName;
    
    public FertilizerRecommendation() {
        
    }

    /**
     * @return the soilAnalysisNumber
     */
    public int getSoilAnalysisNumber() {
        return soilAnalysisNumber;
    }

    /**
     * @param soilAnalysisNumber the soilAnalysisNumber to set
     */
    public void setSoilAnalysisNumber(int soilAnalysisNumber) {
        this.soilAnalysisNumber = soilAnalysisNumber;
    }

    /**
     * @return the applicationMethod
     */
    public String getApplicationMethod() {
        return applicationMethod;
    }

    /**
     * @param applicationMethod the applicationMethod to set
     */
    public void setApplicationMethod(String applicationMethod) {
        this.applicationMethod = applicationMethod;
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
     * @return the fertilizerName
     */
    public String getFertilizerName() {
        return fertilizerName;
    }

    /**
     * @param fertilizerName the fertilizerName to set
     */
    public void setFertilizerName(String fertilizerName) {
        this.fertilizerName = fertilizerName;
    }
}
