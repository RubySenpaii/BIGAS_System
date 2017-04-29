/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import db.DBConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import object.Employee;
import object.Plot;

/**
 *
 * @author RubySenpaii
 */
public class EmployeeDAO {

    public boolean registerEmployee(Employee employee) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("INSERT INTO " + Employee.TABLE_NAME + " "
                    + "(" + Employee.COLUMN_ADDRESS + ", " + Employee.COLUMN_AUTHORITY + ", " + Employee.COLUMN_BIRTHDAY + ", "
                    + Employee.COLUMN_CONTACTNO + ", " + Employee.COLUMN_EMPLOYEEID + ", " + Employee.COLUMN_FIRSTNAME + ", "
                    + Employee.COLUMN_FLAG + ", " + Employee.COLUMN_GENDER + ", " + Employee.COLUMN_LASTNAME + ", "
                    + Employee.COLUMN_MIDDLENAME + ", " + Employee.COLUMN_MUNICIPALITYID + ", " + Employee.COLUMN_OFFICLEVEL + ", "
                    + Employee.COLUMN_PASSWORD + ", " + Employee.COLUMN_USERNAME + ") "
                    + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, employee.getAddress());
            ps.setString(2, employee.getAuthority());
            ps.setString(3, employee.getBirthday());
            ps.setString(4, employee.getContactNo());
            ps.setInt(5, employee.getEmployeeID());
            ps.setString(6, employee.getFirstName());
            ps.setInt(7, employee.getFlag());
            ps.setString(8, employee.getGender());
            ps.setString(9, employee.getLastName());
            ps.setString(10, employee.getMiddleName());
            ps.setInt(11, employee.getMunicipalityID());
            ps.setString(12, employee.getOfficeLevel());
            ps.setString(13, employee.getPassword());
            ps.setString(14, employee.getUsername());

            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, x);
            return false;
        }
        return true;
    }
    
    public boolean updateEmployee(Employee employee) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            
            PreparedStatement ps = conn.prepareStatement("UPDATE " + Employee.TABLE_NAME + 
                    " SET " + Employee.COLUMN_ADDRESS + " = ?, " + Employee.COLUMN_AUTHORITY + " = ?, " + Employee.COLUMN_BIRTHDAY + " = ?, "
                    + Employee.COLUMN_CONTACTNO + " = ?, " + Employee.COLUMN_EMPLOYEEID + " = ?, " + Employee.COLUMN_FIRSTNAME + " = ?, "
                    + Employee.COLUMN_FLAG + " = ?, " + Employee.COLUMN_GENDER + " = ?, " + Employee.COLUMN_LASTNAME + " = ?, "
                    + Employee.COLUMN_MIDDLENAME + " = ?, " + Employee.COLUMN_MUNICIPALITYID + " = ?, " + Employee.COLUMN_OFFICLEVEL + " = ?, "
                    + Employee.COLUMN_PASSWORD + " = ?, " + Employee.COLUMN_USERNAME + " = ? "
                    + "WHERE " + Employee.COLUMN_EMPLOYEEID + " = ?");
            ps.setString(1, employee.getAddress());
            ps.setString(2, employee.getAuthority());
            ps.setString(3, employee.getBirthday());
            ps.setString(4, employee.getContactNo());
            ps.setInt(5, employee.getEmployeeID());
            ps.setString(6, employee.getFirstName());
            ps.setInt(7, employee.getFlag());
            ps.setString(8, employee.getGender());
            ps.setString(9, employee.getLastName());
            ps.setString(10, employee.getMiddleName());
            ps.setInt(11, employee.getMunicipalityID());
            ps.setString(12, employee.getOfficeLevel());
            ps.setString(13, employee.getPassword());
            ps.setString(14, employee.getUsername());
            ps.setInt(15, employee.getEmployeeID());
            
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } return true;
    }

    public boolean checkUsername(String username, String password) {
        ArrayList<Employee> employees = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + Employee.TABLE_NAME 
                    + " WHERE " + Employee.COLUMN_USERNAME + " = ? AND " + Employee.COLUMN_PASSWORD + " = ?");
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            employees = getDataFromResultSet(rs);

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, x);
            return false;
        }
        return !employees.isEmpty();
    }
    
    public Employee getEmployeeWithUsername(String username) {
        ArrayList<Employee> employees = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + Employee.TABLE_NAME 
                    + " WHERE " + Employee.COLUMN_USERNAME + " = ?");
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();
            employees = getDataFromResultSet(rs);

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return employees.get(0);
    }
    
    public Employee getEmployeeWithEmployeeID(int employeeID) {
        ArrayList<Employee> employees = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + Employee.TABLE_NAME 
                    + " WHERE " + Employee.COLUMN_EMPLOYEEID + " = ?");
            ps.setInt(1, employeeID);

            ResultSet rs = ps.executeQuery();
            employees = getDataFromResultSet(rs);

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return employees.get(0);
    }

    public ArrayList<Employee> getListOfEmployees() {
        ArrayList<Employee> employees = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + Employee.TABLE_NAME);

            ResultSet rs = ps.executeQuery();
            employees = getDataFromResultSet(rs);

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return employees;
    }
    
    public ArrayList<Employee> getListOfTechniciansInMunicipal(int municipalityID) {
        ArrayList<Employee> employees = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + Employee.TABLE_NAME + 
                    " WHERE " + Employee.COLUMN_OFFICLEVEL + " = ? AND " + Employee.COLUMN_AUTHORITY + " = ? AND " + Employee.COLUMN_MUNICIPALITYID + " = ?");
            ps.setString(1, "MAO");
            ps.setString(2, "Technician");
            ps.setInt(3, municipalityID);

            ResultSet rs = ps.executeQuery();
            employees = getDataFromResultSet(rs);

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return employees;
    }

    private ArrayList<Employee> getDataFromResultSet(ResultSet rs) throws SQLException {
        ArrayList<Employee> employees = new ArrayList<>();
        while (rs.next()) {
            Employee employee = new Employee();
            employee.setAddress(rs.getString(Employee.COLUMN_ADDRESS));
            employee.setAuthority(rs.getString(Employee.COLUMN_AUTHORITY));
            employee.setBirthday(rs.getString(Employee.COLUMN_BIRTHDAY));
            employee.setContactNo(rs.getString(Employee.COLUMN_CONTACTNO));
            employee.setEmployeeID(rs.getInt(Employee.COLUMN_EMPLOYEEID));
            employee.setFirstName(rs.getString(Employee.COLUMN_FIRSTNAME));
            employee.setFlag(rs.getInt(Employee.COLUMN_FLAG));
            employee.setGender(rs.getString(Employee.COLUMN_GENDER));
            employee.setLastName(rs.getString(Employee.COLUMN_LASTNAME));
            employee.setMiddleName(rs.getString(Employee.COLUMN_MIDDLENAME));
            employee.setMunicipalityID(rs.getInt(Employee.COLUMN_MUNICIPALITYID));
            employee.setOfficeLevel(rs.getString(Employee.COLUMN_OFFICLEVEL));
            employee.setPassword(rs.getString(Employee.COLUMN_PASSWORD));
            employee.setUsername(rs.getString(Employee.COLUMN_USERNAME));
            employees.add(employee);
        }
        return employees;
    }
}
