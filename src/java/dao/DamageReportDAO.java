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
import object.DamageReport;
import object.Plot;

/**
 *
 * @author RubySenpaii
 */
public class DamageReportDAO {

    public boolean reportDamageReport(DamageReport damageReport) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("INSERT INTO " + DamageReport.TABLE_NAME + " "
                    + "(" + DamageReport.COLUMN_AREAAFFECTED + ", " + DamageReport.COLUMN_AREADAMAGED + ", " + DamageReport.COLUMN_DAMAGEINCIDENTID + ", "
                    + DamageReport.COLUMN_DATEREPORTED + ", " + DamageReport.COLUMN_IMAGE + ") "
                    + "VALUES(?, ?, ?, ?, ?)");
            ps.setDouble(1, damageReport.getAreaAffected());
            ps.setDouble(2, damageReport.getAreaDamaged());
            ps.setInt(3, damageReport.getDamageIncidentID());
            ps.setString(4, damageReport.getDateReported());
            ps.setString(5, damageReport.getImage());

            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(DamageReportDAO.class.getName()).log(Level.SEVERE, null, x);
            return false;
        }
        return true;
    }
    
    public boolean updateDamageReport(DamageReport damageReport) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            
            PreparedStatement ps = conn.prepareStatement("UPDATE " + DamageReport.TABLE_NAME + 
                    " SET " + DamageReport.COLUMN_AREAAFFECTED + " = ?, " + DamageReport.COLUMN_AREADAMAGED + " = ?, " + DamageReport.COLUMN_DAMAGEINCIDENTID + " = ?, "
                    + DamageReport.COLUMN_DATEREPORTED + " = ?, " + DamageReport.COLUMN_IMAGE + " = ? "
                    + "WHERE " + DamageReport.COLUMN_DAMAGEINCIDENTID + " = ? AND " + DamageReport.COLUMN_DATEREPORTED + " = ?");
            ps.setDouble(1, damageReport.getAreaAffected());
            ps.setDouble(2, damageReport.getAreaDamaged());
            ps.setInt(3, damageReport.getDamageIncidentID());
            ps.setString(4, damageReport.getDateReported());
            ps.setString(5, damageReport.getImage());
            ps.setInt(6, damageReport.getDamageIncidentID());
            ps.setString(7, damageReport.getDateReported());
            
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DamageReportDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } return true;
    }

    public ArrayList<DamageReport> getListOfDamageReports() {
        ArrayList<DamageReport> damageReports = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + DamageReport.TABLE_NAME);

            ResultSet rs = ps.executeQuery();
            damageReports = getDataFromResultSet(rs);

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(DamageReportDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return damageReports;
    }

    public ArrayList<DamageReport> getListOfDamageReportWithIncidentID(int incidentID) {
        ArrayList<DamageReport> damageReports = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + DamageReport.TABLE_NAME + " WHERE " + DamageReport.COLUMN_DAMAGEINCIDENTID + " = ?");
            ps.setInt(1, incidentID);

            ResultSet rs = ps.executeQuery();
            damageReports = getDataFromResultSet(rs);

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(DamageReportDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return damageReports;
    }

    public DamageReport getLatestDamageReportWithIncidentIDBefore(int incidentID, String date) {
        ArrayList<DamageReport> damageReports = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT DR2.*\n"
                    + "FROM DamageReport DR2 JOIN (SELECT DR1.DamageIncidentID, MAX(STR_TO_DATE(DR1.DateReported, '%m-%d-%Y')) AS 'DateReported'\n"
                    + "                             FROM DamageReport DR1\n"
                    + "                             WHERE STR_TO_DATE(DR1.DateReported, '%m-%d-%Y') < STR_TO_DATE(?, '%m-%d-%Y') AND DR1.DamageIncidentID = ?\n"
                    + "                            GROUP BY DR1.DamageIncidentID) T1 ON STR_TO_DATE(DR2.DateReported, '%m-%d-%Y') = T1.Datereported AND DR2.DamageIncidentID = T1.DamageIncidentID");
            ps.setString(1, date);
            ps.setInt(2, incidentID);

            ResultSet rs = ps.executeQuery();
            damageReports = getDataFromResultSet(rs);

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(DamageReportDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return damageReports.get(0);
    }

    private ArrayList<DamageReport> getDataFromResultSet(ResultSet rs) throws SQLException {
        ArrayList<DamageReport> damageReports = new ArrayList<>();
        while (rs.next()) {
            DamageReport damageReport = new DamageReport();
            damageReport.setAreaAffected(rs.getDouble(DamageReport.COLUMN_AREAAFFECTED));
            damageReport.setAreaDamaged(rs.getDouble(DamageReport.COLUMN_AREADAMAGED));
            damageReport.setDamageIncidentID(rs.getInt(DamageReport.COLUMN_DAMAGEINCIDENTID));
            damageReport.setDateReported(rs.getString(DamageReport.COLUMN_DATEREPORTED));
            damageReport.setImage(rs.getString(DamageReport.COLUMN_IMAGE));
            damageReports.add(damageReport);
        }
        return damageReports;
    }
}
