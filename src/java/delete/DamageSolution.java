/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package delete;

/**
 *
 * @author RubySenpaii
 */
public class DamageSolution {
    public static final String TABLE_NAME = "DamageSolution";
    public static final String COLUMN_DAMAGEINCIDENTID = "DamageIncidentID";
    public static final String COLUMN_DEPLOYEDID = "DeployedID";
    
    private int damageIncidentID;
    private int deployedID;
    
    public DamageSolution() {
        
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
     * @return the deployedID
     */
    public int getDeployedID() {
        return deployedID;
    }

    /**
     * @param deployedID the deployedID to set
     */
    public void setDeployedID(int deployedID) {
        this.deployedID = deployedID;
    }
}
