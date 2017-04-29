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
import object.Farmer;
import object.Plot;

/**
 *
 * @author RubySenpaii
 */
public class FarmerDAO {
    
    public boolean registerFarmer(Farmer farmer){
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("INSERT INTO " + Farmer.TABLE_NAME + " "
                    + "(" + Farmer.COLUMN_ADDRESS + ", " + Farmer.COLUMN_BIRTHDAY + ", " + Farmer.COLUMN_CONTACTNO + ", " + Farmer.COLUMN_FARMERID + ", " 
                    + Farmer.COLUMN_FARMID + ", " + Farmer.COLUMN_FIRSTNAME + ", " + Farmer.COLUMN_FLAG + ", " + Farmer.COLUMN_LASTNAME + ", " 
                    + Farmer.COLUMN_MIDDLENAME + ") "
                    + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, farmer.getAddress());
            ps.setString(2, farmer.getBirthday());
            ps.setString(3, farmer.getContactNo());
            ps.setInt(4, farmer.getFarmerID());
            ps.setInt(5, farmer.getFarmID());
            ps.setString(6, farmer.getFirstName());
            ps.setInt(7, farmer.getFlag());
            ps.setString(8, farmer.getLastName());
            ps.setString(9, farmer.getMiddleName());

            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(FarmerDAO.class.getName()).log(Level.SEVERE, null, x);
            return false;
        }
        return true;
    }
    
    public boolean updateFarmer(Farmer farmer) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            
            PreparedStatement ps = conn.prepareStatement("UPDATE " + Farmer.TABLE_NAME + 
                    " SET " + Farmer.COLUMN_ADDRESS + " = ?, " + Farmer.COLUMN_BIRTHDAY + " = ?, " + Farmer.COLUMN_CONTACTNO + " = ?, "
                    + Farmer.COLUMN_FARMERID + " = ?, " + Farmer.COLUMN_FARMID + " = ?, " + Farmer.COLUMN_FIRSTNAME + " = ?, "
                    + Farmer.COLUMN_FLAG + " = ?, " + Farmer.COLUMN_LASTNAME + " = ?, " + Farmer.COLUMN_MIDDLENAME + " = ? "
                    + "WHERE " + Farmer.COLUMN_FARMERID + " = ?");
            ps.setString(1, farmer.getAddress());
            ps.setString(2, farmer.getBirthday());
            ps.setString(3, farmer.getContactNo());
            ps.setInt(4, farmer.getFarmID());
            ps.setInt(5, farmer.getFarmID());
            ps.setString(6, farmer.getFirstName());
            ps.setInt(7, farmer.getFlag());
            ps.setString(8, farmer.getLastName());
            ps.setString(9, farmer.getMiddleName());
            ps.setInt(10, farmer.getFarmerID());
            
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(FarmerDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } return true;
    }

    public ArrayList<Farmer> getListOfFarmers() {
        ArrayList<Farmer> farmers = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + Farmer.TABLE_NAME);

            ResultSet rs = ps.executeQuery();
            farmers = getDataFromResultSet(rs);

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(FarmerDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return farmers;
    }
    
    public Farmer getFarmerDetaillsOnFarm(int farmID) {
        ArrayList<Farmer> farmers = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT Fr.* "
                    + "FROM Farmer Fr JOIN Farm F ON Fr.FarmID = F.FarmID "
                    + "WHERE Fr.Flag = 1 AND F.FarmID = ?");
            ps.setInt(1, farmID);

            ResultSet rs = ps.executeQuery();
            farmers = getDataFromResultSet(rs);

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(FarmerDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return farmers.get(0);
    }

    private ArrayList<Farmer> getDataFromResultSet(ResultSet rs) throws SQLException {
        ArrayList<Farmer> farmers = new ArrayList<>();
        while (rs.next()) {
            Farmer farmer = new Farmer();
            farmer.setAddress(rs.getString(Farmer.COLUMN_ADDRESS));
            farmer.setBirthday(rs.getString(Farmer.COLUMN_BIRTHDAY));
            farmer.setContactNo(rs.getString(Farmer.COLUMN_CONTACTNO));
            farmer.setFarmID(rs.getInt(Farmer.COLUMN_FARMID));
            farmer.setFarmerID(rs.getInt(Farmer.COLUMN_FARMERID));
            farmer.setFirstName(rs.getString(Farmer.COLUMN_FIRSTNAME));
            farmer.setFlag(rs.getInt(Farmer.COLUMN_FLAG));
            farmer.setLastName(rs.getString(Farmer.COLUMN_LASTNAME));
            farmer.setMiddleName(rs.getString(Farmer.COLUMN_MIDDLENAME));
            farmers.add(farmer);
        }
        return farmers;
    }
}
