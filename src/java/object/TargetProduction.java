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
public class TargetProduction {
    public static final String TABLE_NAME = "TargetProduction";
    public static final String COLUMN_MUNICIPALITYID = "MunicipalityID";
    public static final String COLUMN_YEAR = "Year";
    public static final String COLUMN_TARGETVALUE = "TargetValue";
    
    private int municipalityID;
    private int year;
    private double targetValue;
    
    //additional info
    private String municipalityName;
    
    public TargetProduction() {
        
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
     * @return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * @return the targetValue
     */
    public double getTargetValue() {
        return targetValue;
    }

    /**
     * @param targetValue the targetValue to set
     */
    public void setTargetValue(double targetValue) {
        this.targetValue = targetValue;
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
}
