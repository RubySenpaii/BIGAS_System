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
public class SeedVariety {
    public static final String TABLE_NAME = "SeedVariety";
    public static final String COLUMN_VARIETYNAME = "VarietyName";
    public static final String COLUMN_AVERAGEYIELD = "AverageYield";
    public static final String COLUMN_MAXIMUMYIELD = "MaximumYield";
    public static final String COLUMN_MATURITY = "Maturity";
    public static final String COLUMN_HEIGHT = "Height";
    public static final String COLUMN_GRAINSIZE = "GrainSize";
    
    private String varietyName;
    private double averageYield;
    private double maximumYield;
    private int maturity;
    private double height;
    private String grainSize;
    
    public SeedVariety() {
        
    }

    /**
     * @return the varietyName
     */
    public String getVarietyName() {
        return varietyName;
    }

    /**
     * @param varietyName the varietyName to set
     */
    public void setVarietyName(String varietyName) {
        this.varietyName = varietyName;
    }

    /**
     * @return the averageYield
     */
    public double getAverageYield() {
        return averageYield;
    }

    /**
     * @param averageYield the averageYield to set
     */
    public void setAverageYield(double averageYield) {
        this.averageYield = averageYield;
    }

    /**
     * @return the maximumYield
     */
    public double getMaximumYield() {
        return maximumYield;
    }

    /**
     * @param maximumYield the maximumYield to set
     */
    public void setMaximumYield(double maximumYield) {
        this.maximumYield = maximumYield;
    }

    /**
     * @return the maturity
     */
    public int getMaturity() {
        return maturity;
    }

    /**
     * @param maturity the maturity to set
     */
    public void setMaturity(int maturity) {
        this.maturity = maturity;
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
     * @return the grainSize
     */
    public String getGrainSize() {
        return grainSize;
    }

    /**
     * @param grainSize the grainSize to set
     */
    public void setGrainSize(String grainSize) {
        this.grainSize = grainSize;
    }
}
