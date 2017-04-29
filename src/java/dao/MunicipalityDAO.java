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
import object.Plot;

/**
 *
 * @author RubySenpaii
 */
public class MunicipalityDAO {

    public boolean addMunicipality(Municipality fertilizerRecommendation) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("INSERT INTO " + Municipality.TABLE_NAME + " "
                    + "(" + Municipality.COLUMN_AREA + ", " + Municipality.COLUMN_DISTRICT + ", " + Municipality.COLUMN_LATITUDE + ", "
                    + Municipality.COLUMN_LONGITUDE + ", " + Municipality.COLUMN_MUNICIPALITYID + ", " + Municipality.COLUMN_MUNICIPALITYNAME + ") "
                    + "VALUES(?, ?, ?, ?, ?, ?)");
            ps.setDouble(1, fertilizerRecommendation.getArea());
            ps.setInt(2, fertilizerRecommendation.getDistrict());
            ps.setDouble(3, fertilizerRecommendation.getLatitude());
            ps.setDouble(4, fertilizerRecommendation.getLongitude());
            ps.setInt(5, fertilizerRecommendation.getMunicipalityID());
            ps.setString(6, fertilizerRecommendation.getMunicipalityName());

            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(MunicipalityDAO.class.getName()).log(Level.SEVERE, null, x);
            return false;
        }
        return true;
    }

    public boolean updateMunicipality(Municipality municipality) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("UPDATE " + Municipality.TABLE_NAME
                    + " SET " + Municipality.COLUMN_AREA + " = ?, " + Municipality.COLUMN_DISTRICT + " = ?, " + Municipality.COLUMN_LATITUDE + " = ?, "
                    + Municipality.COLUMN_LONGITUDE + " = ?, " + Municipality.COLUMN_MUNICIPALITYID + " = ?, " + Municipality.COLUMN_MUNICIPALITYNAME + " = ? "
                    + "WHERE " + Municipality.COLUMN_MUNICIPALITYID + " = ?");
            ps.setDouble(1, municipality.getArea());
            ps.setInt(2, municipality.getDistrict());
            ps.setDouble(3, municipality.getLatitude());
            ps.setDouble(4, municipality.getLongitude());
            ps.setInt(5, municipality.getMunicipalityID());
            ps.setString(6, municipality.getMunicipalityName());
            ps.setInt(7, municipality.getMunicipalityID());

            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(MunicipalityDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public Municipality getMunicipalOfBarangay(int barangayID) {
        ArrayList<Municipality> municipalities = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT M.* "
                    + "FROM Municipality M JOIN Barangay B ON M.MunicipalityID = B.MunicipalityID "
                    + "WHERE B.BarangayID = ?");
            ps.setInt(1, barangayID);

            ResultSet rs = ps.executeQuery();
            municipalities = getDataFromResultSet(rs);

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(MunicipalityDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return municipalities.get(0);
    }

    public Municipality getMunicipalDetail(int municipalityID) {
        ArrayList<Municipality> municipalities = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + Municipality.TABLE_NAME + " WHERE " + Municipality.COLUMN_MUNICIPALITYID + " = ?");
            ps.setInt(1, municipalityID);

            ResultSet rs = ps.executeQuery();
            municipalities = getDataFromResultSet(rs);

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(MunicipalityDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return municipalities.get(0);
    }

    public Municipality getMunicipalDetail(String municipalityName) {
        ArrayList<Municipality> municipalities = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + Municipality.TABLE_NAME + " WHERE " + Municipality.COLUMN_MUNICIPALITYNAME + " = ?");
            ps.setString(1, municipalityName);

            ResultSet rs = ps.executeQuery();
            municipalities = getDataFromResultSet(rs);

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(MunicipalityDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return municipalities.get(0);
    }

    public ArrayList<Municipality> getListOfMunicipalities() {
        ArrayList<Municipality> municipalities = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + Municipality.TABLE_NAME + " WHERE " + Municipality.COLUMN_MUNICIPALITYID + " != -1");

            ResultSet rs = ps.executeQuery();
            municipalities = getDataFromResultSet(rs);

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(MunicipalityDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return municipalities;
    }

    public Municipality getMunicipalityProductionForYear(int municipalityID, int year) {
        ArrayList<Municipality> municipalities = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            String dateStart = "03-16-" + year;
            String dateEnd = "03-15-" + (year + 1);
            PreparedStatement ps = conn.prepareStatement("SELECT M.*, SUM(PR.AmountHarvested) AS 'TotalHarvested'\n"
                    + "FROM PlantingReport PR JOIN Plot P ON PR.PlotID = P.PlotID\n"
                    + "                       JOIN Farm F ON P.FarmID = F.FarmID\n"
                    + "                       JOIN Barangay B ON F.BarangayID = B.BarangayID\n"
                    + "                       JOIN Municipality M ON B.MunicipalityID = M.MunicipalityID\n"
                    + "WHERE STR_TO_DATE(CONCAT(PR.HarvestMonth, '-', PR.HarvestDay, '-', PR.HarvestYear), '%m-%d-%Y') > STR_TO_DATE(?, '%m-%d-%Y') "
                    + "      AND STR_TO_DATE(CONCAT(PR.HarvestMonth, '-', PR.HarvestDay, '-', PR.HarvestYear), '%m-%d-%Y') < STR_TO_DATE(?, '%m-%d-%Y') "
                    + "      AND M.MunicipalityID = ?\n"
                    + "GROUP BY M.MunicipalityID");
            ps.setString(1, dateStart);
            ps.setString(2, dateEnd);
            ps.setInt(3, municipalityID);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Municipality municipality = new Municipality();
                municipality.setArea(rs.getDouble(Municipality.COLUMN_AREA));
                municipality.setDistrict(rs.getInt(Municipality.COLUMN_DISTRICT));
                municipality.setLatitude(rs.getDouble(Municipality.COLUMN_LATITUDE));
                municipality.setLongitude(rs.getDouble(Municipality.COLUMN_LONGITUDE));
                municipality.setMunicipalityID(rs.getInt(Municipality.COLUMN_MUNICIPALITYID));
                municipality.setMunicipalityName(rs.getString(Municipality.COLUMN_MUNICIPALITYNAME));
                municipality.setHarvestedTotal(rs.getDouble("TotalHarvested"));
                municipalities.add(municipality);
            }

            rs.close();
            ps.close();
            conn.close();
            return municipalities.get(0);
        } catch (SQLException x) {
            Logger.getLogger(MunicipalityDAO.class.getName()).log(Level.SEVERE, null, x);
        } catch (IndexOutOfBoundsException ex) {
            Municipality municipality = getMunicipalDetail(municipalityID);
            municipality.setHarvestedTotal(0);
            return municipality;
        }
        return municipalities.get(0);
    }

    private ArrayList<Municipality> getDataFromResultSet(ResultSet rs) throws SQLException {
        ArrayList<Municipality> municipalities = new ArrayList<>();
        while (rs.next()) {
            Municipality municipality = new Municipality();
            municipality.setArea(rs.getDouble(Municipality.COLUMN_AREA));
            municipality.setDistrict(rs.getInt(Municipality.COLUMN_DISTRICT));
            municipality.setLatitude(rs.getDouble(Municipality.COLUMN_LATITUDE));
            municipality.setLongitude(rs.getDouble(Municipality.COLUMN_LONGITUDE));
            municipality.setMunicipalityID(rs.getInt(Municipality.COLUMN_MUNICIPALITYID));
            municipality.setMunicipalityName(rs.getString(Municipality.COLUMN_MUNICIPALITYNAME));
            municipalities.add(municipality);
        }
        return municipalities;
    }
}
