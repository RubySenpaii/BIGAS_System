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
public class Fertilizer {
    public static final String TABLE_NAME = "Fertilizer";
    public static final String COLUMN_FERTILZIERNAME = "FertilizerName";
    
    private String fertilizerName;
    
    public Fertilizer() {
        
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
