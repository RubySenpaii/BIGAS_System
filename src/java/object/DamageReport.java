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
public class DamageReport {
    public static final String TABLE_NAME = "DamageReport";
    public static final String COLUMN_DAMAGEINCIDENTID = "DamageIncidentID";
    public static final String COLUMN_DATEREPORTED = "DateReported";
    public static final String COLUMN_AREAAFFECTED = "AreaAffected";
    public static final String COLUMN_AREADAMAGED = "AreaDamaged";
    public static final String COLUMN_IMAGE = "Image";
    
    private int damageIncidentID;
    private String dateReported;
    private double areaAffected;
    private double areaDamaged;
    private String image;
    
    public DamageReport() {
        
    }

    /**
     * @return the damageIncidentID
     */
    public int getDamageIncidentID() {
        return damageIncidentID;
    }

    /**
     * @param damageIncidentID the damageIncidentID to set
     */
    public void setDamageIncidentID(int damageIncidentID) {
        this.damageIncidentID = damageIncidentID;
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
     * @return the areaAffected
     */
    public double getAreaAffected() {
        return areaAffected;
    }

    /**
     * @param areaAffected the areaAffected to set
     */
    public void setAreaAffected(double areaAffected) {
        this.areaAffected = areaAffected;
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
}
