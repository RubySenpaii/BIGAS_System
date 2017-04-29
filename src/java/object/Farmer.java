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
public class Farmer {
    public static final String TABLE_NAME = "Farmer";
    public static final String COLUMN_FARMERID = "FarmerID";
    public static final String COLUMN_FARMID = "FarmID";
    public static final String COLUMN_LASTNAME = "LastName";
    public static final String COLUMN_FIRSTNAME = "FirstName";
    public static final String COLUMN_MIDDLENAME = "MiddleName";
    public static final String COLUMN_ADDRESS = "Address";
    public static final String COLUMN_BIRTHDAY = "Birthday";
    public static final String COLUMN_CONTACTNO = "ContactNo";
    public static final String COLUMN_FLAG = "Flag";
    
    private int farmerID;
    private int farmID;
    private String lastName;
    private String firstName;
    private String middleName;
    private String address;
    private String birthday;
    private String contactNo;
    private int flag;
    
    public Farmer() {
        
    }

    /**
     * @return the farmerID
     */
    public int getFarmerID() {
        return farmerID;
    }

    /**
     * @param farmerID the farmerID to set
     */
    public void setFarmerID(int farmerID) {
        this.farmerID = farmerID;
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
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the middleName
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * @param middleName the middleName to set
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the birthday
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * @param birthday the birthday to set
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    /**
     * @return the contactNo
     */
    public String getContactNo() {
        return contactNo;
    }

    /**
     * @param contactNo the contactNo to set
     */
    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    /**
     * @return the flag
     */
    public int getFlag() {
        return flag;
    }

    /**
     * @param flag the flag to set
     */
    public void setFlag(int flag) {
        this.flag = flag;
    }
}
