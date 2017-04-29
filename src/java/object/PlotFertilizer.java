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
public class PlotFertilizer {
    public static final String TABLE_NAME = "PlotFertilizer";
    public static final String COLUMN_FERTILIZERNAME = "FertilizerName";
    public static final String COLUMN_PLOTID = "PlotID";
    public static final String COLUMN_DATEAPPLIED = "DateApplied";
    
    private String fertilizerName;
    private int plotID;
    private String dateApplied;
    
    public PlotFertilizer(){
        
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
     * @return the dateApplied
     */
    public String getDateApplied() {
        return dateApplied;
    }

    /**
     * @param dateApplied the dateApplied to set
     */
    public void setDateApplied(String dateApplied) {
        this.dateApplied = dateApplied;
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
