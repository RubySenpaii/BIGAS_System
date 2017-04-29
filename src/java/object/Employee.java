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
public class Employee {
    public static final String TABLE_NAME = "Employee";
    public static final String COLUMN_EMPLOYEEID = "EmployeeID";
    public static final String COLUMN_MUNICIPALITYID = "MunicipalityID";
    public static final String COLUMN_LASTNAME = "LastName";
    public static final String COLUMN_FIRSTNAME = "FirstName";
    public static final String COLUMN_MIDDLENAME = "MiddleName";
    public static final String COLUMN_ADDRESS = "Address";
    public static final String COLUMN_BIRTHDAY = "Birthday";
    public static final String COLUMN_OFFICLEVEL = "OfficeLevel";
    public static final String COLUMN_AUTHORITY = "Authority";
    public static final String COLUMN_GENDER = "Gender";
    public static final String COLUMN_CONTACTNO = "ContactNo";
    public static final String COLUMN_USERNAME = "Username";
    public static final String COLUMN_PASSWORD = "Password";
    public static final String COLUMN_FLAG = "Flag";
    
    private int employeeID;
    private int municipalityID;
    private String lastName;
    private String firstName;
    private String middleName;
    private String address;
    private String birthday;
    private String officeLevel;
    private String authority;
    private String gender;
    private String contactNo;
    private String username;
    private String password;
    private int flag;
    
    public Employee() {
        
    }

    /**
     * @return the employeeID
     */
    public int getEmployeeID() {
        return employeeID;
    }

    /**
     * @param employeeID the employeeID to set
     */
    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
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
     * @return the officeLevel
     */
    public String getOfficeLevel() {
        return officeLevel;
    }

    /**
     * @param officeLevel the officeLevel to set
     */
    public void setOfficeLevel(String officeLevel) {
        this.officeLevel = officeLevel;
    }

    /**
     * @return the authority
     */
    public String getAuthority() {
        return authority;
    }

    /**
     * @param authority the authority to set
     */
    public void setAuthority(String authority) {
        this.authority = authority;
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
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
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
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
