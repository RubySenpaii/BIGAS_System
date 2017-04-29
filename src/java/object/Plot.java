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
public class Plot {
    public static final String TABLE_NAME = "Plot";
    public static final String COLUMN_PLOTID = "PlotID";
    public static final String COLUMN_FARMID = "FarmID";
    public static final String COLUMN_PLOTNUMBER = "PlotNumber";
    public static final String COLUMN_PLOTSIZE = "PlotSize";
    public static final String COLUMN_PLOTPLANTED = "PlotPlanted";
    
    private int plotID;
    private int farmID;
    private int plotNumber;
    private double plotSize;
    private int plotPlanted;
    
    public Plot() {
        
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
     * @return the plotNumber
     */
    public int getPlotNumber() {
        return plotNumber;
    }

    /**
     * @param plotNumber the plotNumber to set
     */
    public void setPlotNumber(int plotNumber) {
        this.plotNumber = plotNumber;
    }

    /**
     * @return the plotSize
     */
    public double getPlotSize() {
        return plotSize;
    }

    /**
     * @param plotSize the plotSize to set
     */
    public void setPlotSize(double plotSize) {
        this.plotSize = plotSize;
    }

    /**
     * @return the plotPlanted
     */
    public int getPlotPlanted() {
        return plotPlanted;
    }

    /**
     * @param plotPlanted the plotPlanted to set
     */
    public void setPlotPlanted(int plotPlanted) {
        this.plotPlanted = plotPlanted;
    }
}
