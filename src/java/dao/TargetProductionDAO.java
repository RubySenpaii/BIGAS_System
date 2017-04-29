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
import object.Municipality;
import object.TargetProduction;

/**
 *
 * @author RubySenpaii
 */
public class TargetProductionDAO {

    public boolean addTargetProduction(TargetProduction targetProduction) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("INSERT INTO " + TargetProduction.TABLE_NAME + " "
                    + "(" + TargetProduction.COLUMN_MUNICIPALITYID + ", " + TargetProduction.COLUMN_TARGETVALUE + ", "
                    + TargetProduction.COLUMN_YEAR + ") "
                    + "VALUES(?, ?, ?)");
            ps.setInt(1, targetProduction.getMunicipalityID());
            ps.setDouble(2, targetProduction.getTargetValue());
            ps.setInt(3, targetProduction.getYear());

            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(TargetProductionDAO.class.getName()).log(Level.SEVERE, null, x);
            return false;
        }
        return true;
    }

    public ArrayList<TargetProduction> getListOfTargetProductions() {
        ArrayList<TargetProduction> targetProductions = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + TargetProduction.TABLE_NAME);

            ResultSet rs = ps.executeQuery();
            targetProductions = getDataFromResultSet(rs);

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(TargetProductionDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return targetProductions;
    }

    public ArrayList<TargetProduction> getListOfTargetProductionForEachMunicipalityOnYear(int year) {
        ArrayList<TargetProduction> targetProductions = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT TP.*, M.MunicipalityNamme "
                    + "FROM TargetProduction TP JOIN Municipality M ON TP.MunicipalityID = M.MunicipalityID "
                    + "WHERE TP.Year = ?");
            ps.setInt(1, year);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TargetProduction targetProduction = new TargetProduction();
                targetProduction.setMunicipalityID(rs.getInt(TargetProduction.COLUMN_MUNICIPALITYID));
                targetProduction.setTargetValue(rs.getDouble(TargetProduction.COLUMN_TARGETVALUE));
                targetProduction.setYear(rs.getInt(TargetProduction.COLUMN_YEAR));
                targetProduction.setMunicipalityName(rs.getString("MunicipalityName"));
                targetProductions.add(targetProduction);
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(TargetProductionDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return targetProductions;
    }

    public TargetProduction getTargetProductionForMunicipalOnYear(int municipalID, int year) {
        ArrayList<TargetProduction> targetProductions = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT TP.*, M.MunicipalityName "
                    + "FROM TargetProduction TP JOIN Municipality M ON TP.MunicipalityID = M.MunicipalityID "
                    + "WHERE TP.Year = ? AND TP.MunicipalityID = ?");
            ps.setInt(1, year);
            ps.setInt(2, municipalID);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TargetProduction targetProduction = new TargetProduction();
                targetProduction.setMunicipalityID(rs.getInt(TargetProduction.COLUMN_MUNICIPALITYID));
                targetProduction.setTargetValue(rs.getDouble(TargetProduction.COLUMN_TARGETVALUE));
                targetProduction.setYear(rs.getInt(TargetProduction.COLUMN_YEAR));
                targetProduction.setMunicipalityName(rs.getString("MunicipalityName"));
                targetProductions.add(targetProduction);
            }

            rs.close();
            ps.close();
            conn.close();

            if (targetProductions.isEmpty()) {
                Municipality municipal = new MunicipalityDAO().getMunicipalDetail(municipalID);

                TargetProduction targetProduction = new TargetProduction();
                targetProduction.setMunicipalityID(municipalID);
                targetProduction.setTargetValue(0);
                targetProduction.setYear(year);
                targetProduction.setMunicipalityName(municipal.getMunicipalityName());
                return targetProduction;
            }
        } catch (SQLException x) {
            Logger.getLogger(TargetProductionDAO.class.getName()).log(Level.SEVERE, null, x);
        } 
        return targetProductions.get(0);
    }

    public TargetProduction getTotalTargetProductionForYear(int year) {
        ArrayList<TargetProduction> targetProductions = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT TP.MunicipalityID, TP.Year, SUM(TP.TargetValue) AS 'TargetValue', M.MunicipalityName "
                    + "FROM TargetProduction TP JOIN Municipality M ON TP.MunicipalityID = M.MunicipalityID "
                    + "WHERE TP.Year = ?");
            ps.setInt(1, year);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TargetProduction targetProduction = new TargetProduction();
                targetProduction.setMunicipalityID(rs.getInt(TargetProduction.COLUMN_MUNICIPALITYID));
                targetProduction.setTargetValue(rs.getDouble(TargetProduction.COLUMN_TARGETVALUE));
                targetProduction.setYear(rs.getInt(TargetProduction.COLUMN_YEAR));
                targetProduction.setMunicipalityName(rs.getString("MunicipalityName"));
                targetProductions.add(targetProduction);
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(TargetProductionDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return targetProductions.get(0);
    }

    private ArrayList<TargetProduction> getDataFromResultSet(ResultSet rs) throws SQLException {
        ArrayList<TargetProduction> targetProductions = new ArrayList<>();
        while (rs.next()) {
            TargetProduction targetProduction = new TargetProduction();
            targetProduction.setMunicipalityID(rs.getInt(TargetProduction.COLUMN_MUNICIPALITYID));
            targetProduction.setTargetValue(rs.getDouble(TargetProduction.COLUMN_TARGETVALUE));
            targetProduction.setYear(rs.getInt(TargetProduction.COLUMN_YEAR));
            targetProductions.add(targetProduction);
        }
        return targetProductions;
    }
}
