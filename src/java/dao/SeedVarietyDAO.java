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
import object.SeedVariety;

/**
 *
 * @author RubySenpaii
 */
public class SeedVarietyDAO {
    
    public boolean addSeedVariety(SeedVariety seedVariety) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("INSERT INTO " + SeedVariety.TABLE_NAME + " "
                    + "(" + SeedVariety.COLUMN_AVERAGEYIELD + ", " + SeedVariety.COLUMN_GRAINSIZE + ", " + SeedVariety.COLUMN_HEIGHT + ", " 
                    + SeedVariety.COLUMN_MATURITY + ", " + SeedVariety.COLUMN_MAXIMUMYIELD + ", " + SeedVariety.COLUMN_VARIETYNAME + ") "
                    + "VALUES(?, ?, ?, ?, ?, ?)");
            ps.setDouble(1, seedVariety.getAverageYield());
            ps.setString(2, seedVariety.getGrainSize());
            ps.setDouble(3, seedVariety.getHeight());
            ps.setInt(4, seedVariety.getMaturity());
            ps.setDouble(5, seedVariety.getMaximumYield());
            ps.setString(6, seedVariety.getVarietyName());

            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(SeedVarietyDAO.class.getName()).log(Level.SEVERE, null, x);
            return false;
        }
        return true;
    }

    public ArrayList<SeedVariety> getListOfSeedVarieties() {
        ArrayList<SeedVariety> seedVarieties = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + SeedVariety.TABLE_NAME);

            ResultSet rs = ps.executeQuery();
            seedVarieties = getDataFromResultSet(rs);

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(SeedVarietyDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return seedVarieties;
    }

    private ArrayList<SeedVariety> getDataFromResultSet(ResultSet rs) throws SQLException {
        ArrayList<SeedVariety> seedVarieties = new ArrayList<>();
        while (rs.next()) {
            SeedVariety seedVariety = new SeedVariety();
            seedVariety.setAverageYield(rs.getDouble(SeedVariety.COLUMN_AVERAGEYIELD));
            seedVariety.setGrainSize(rs.getString(SeedVariety.COLUMN_GRAINSIZE));
            seedVariety.setHeight(rs.getDouble(SeedVariety.COLUMN_HEIGHT));
            seedVariety.setMaturity(rs.getInt(SeedVariety.COLUMN_MATURITY));
            seedVariety.setMaximumYield(rs.getDouble(SeedVariety.COLUMN_MAXIMUMYIELD));
            seedVariety.setVarietyName(rs.getString(SeedVariety.COLUMN_VARIETYNAME));
            seedVarieties.add(seedVariety);
        }
        return seedVarieties;
    }
}
