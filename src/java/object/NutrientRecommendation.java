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
public class NutrientRecommendation {
    public static final String TABLE_NAME = "NutrientRecommendation";
    public static final String COLUMN_SOILANALYSISNUMBER = "SoilAnalysisNumber";
    public static final String COLUMN_SEASON = "Season";
    public static final String COLUMN_NITROGEN = "Nitrogen";
    public static final String COLUMN_PHOSPHOROUS = "Phosphorous";
    public static final String COLUMN_POTASSIUM = "Potassium";
    
    private int soilAnalysisNumber;
    private String season;
    private double nitrogen;
    private double phosphorous;
    private double potassium;
    
    public NutrientRecommendation() {
        
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
     * @return the nitrogen
     */
    public double getNitrogen() {
        return nitrogen;
    }

    /**
     * @param nitrogen the nitrogen to set
     */
    public void setNitrogen(double nitrogen) {
        this.nitrogen = nitrogen;
    }

    /**
     * @return the phosphorous
     */
    public double getPhosphorous() {
        return phosphorous;
    }

    /**
     * @param phosphorous the phosphorous to set
     */
    public void setPhosphorous(double phosphorous) {
        this.phosphorous = phosphorous;
    }

    /**
     * @return the potassium
     */
    public double getPotassium() {
        return potassium;
    }

    /**
     * @param potassium the potassium to set
     */
    public void setPotassium(double potassium) {
        this.potassium = potassium;
    }
}
