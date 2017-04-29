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
public class SoilAnalysis {
    public static final String TABLE_NAME = "SoilAnalysis";
    public static final String COLUMN_SOILANALYSISNUMBER = "SoilAnalysisNumber";
    public static final String COLUMN_FARMID = "FarmID";
    public static final String COLUMN_DATESUBMITTED = "DateSubmitted";
    public static final String COLUMN_DATEFINISHED = "DateFinished";
    public static final String COLUMN_TEXTURE = "Texture";
    public static final String COLUMN_PHLEVEL = "pHLevel";
    public static final String COLUMN_NITROGEN = "Nitrogen";
    public static final String COLUMN_PHOSPHOROUS = "Phosphorous";
    public static final String COLUMN_POTASSIUM = "Potassium";
    public static final String COLUMN_ZINC = "Zinc";
    public static final String COLUMN_VALIDUNTIL = "ValidUntil";
    public static final String COLUMN_CERTIFIEDBY = "CertifiedBy";
    
    private int soilAnalysisNumber;
    private int farmID;
    private String dateSubmitted;
    private String dateFinished;
    private double texture;
    private double pHLevel;
    private double nitrogen;
    private double phosphorous;
    private double potassium;
    private double zinc;
    private String validUntil;
    private String certifiedBy;
    
    public SoilAnalysis() {
        
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
     * @return the dateSubmitted
     */
    public String getDateSubmitted() {
        return dateSubmitted;
    }

    /**
     * @param dateSubmitted the dateSubmitted to set
     */
    public void setDateSubmitted(String dateSubmitted) {
        this.dateSubmitted = dateSubmitted;
    }

    /**
     * @return the dateFinished
     */
    public String getDateFinished() {
        return dateFinished;
    }

    /**
     * @param dateFinished the dateFinished to set
     */
    public void setDateFinished(String dateFinished) {
        this.dateFinished = dateFinished;
    }

    /**
     * @return the texture
     */
    public double getTexture() {
        return texture;
    }

    /**
     * @param texture the texture to set
     */
    public void setTexture(double texture) {
        this.texture = texture;
    }

    /**
     * @return the pHLevel
     */
    public double getpHLevel() {
        return pHLevel;
    }

    /**
     * @param pHLevel the pHLevel to set
     */
    public void setpHLevel(double pHLevel) {
        this.pHLevel = pHLevel;
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

    /**
     * @return the zinc
     */
    public double getZinc() {
        return zinc;
    }

    /**
     * @param zinc the zinc to set
     */
    public void setZinc(double zinc) {
        this.zinc = zinc;
    }

    /**
     * @return the validUntil
     */
    public String getValidUntil() {
        return validUntil;
    }

    /**
     * @param validUntil the validUntil to set
     */
    public void setValidUntil(String validUntil) {
        this.validUntil = validUntil;
    }

    /**
     * @return the certifiedBy
     */
    public String getCertifiedBy() {
        return certifiedBy;
    }

    /**
     * @param certifiedBy the certifiedBy to set
     */
    public void setCertifiedBy(String certifiedBy) {
        this.certifiedBy = certifiedBy;
    }
}
