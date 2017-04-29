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
public class MaterialProvided {
    public static final String TABLE_NAME = "MaterialProvided";
    public static final String COLUMN_DEPLOYEDID = "DeployedID";
    public static final String COLUMN_MATERIAL = "Material";
    public static final String COLUMN_RECEIVEDBY = "ReceivedBy";
    public static final String COLUMN_DATERECEIVED = "DateReceived";
    public static final String COLUMN_RECIPIENT = "Recipient";
    public static final String COLUMN_DATEDISTRIBUTED = "DateDistributed";
    
    private int deployedID;
    private String material;
    private int receivedBy;
    private String dateReceived;
    private String recipient;
    private String dateDistributed;
    
    public MaterialProvided() {
        
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

    /**
     * @return the material
     */
    public String getMaterial() {
        return material;
    }

    /**
     * @param material the material to set
     */
    public void setMaterial(String material) {
        this.material = material;
    }

    /**
     * @return the receivedBy
     */
    public int getReceivedBy() {
        return receivedBy;
    }

    /**
     * @param receivedBy the receivedBy to set
     */
    public void setReceivedBy(int receivedBy) {
        this.receivedBy = receivedBy;
    }

    /**
     * @return the dateReceived
     */
    public String getDateReceived() {
        return dateReceived;
    }

    /**
     * @param dateReceived the dateReceived to set
     */
    public void setDateReceived(String dateReceived) {
        this.dateReceived = dateReceived;
    }

    /**
     * @return the recipient
     */
    public String getRecipient() {
        return recipient;
    }

    /**
     * @param recipient the recipient to set
     */
    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    /**
     * @return the dateDistributed
     */
    public String getDateDistributed() {
        return dateDistributed;
    }

    /**
     * @param dateDistributed the dateDistributed to set
     */
    public void setDateDistributed(String dateDistributed) {
        this.dateDistributed = dateDistributed;
    }
}
