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
import object.WeeklyPlantingReport;

/**
 *
 * @author RubySenpaii
 */
public class WeeklyPlantingReportDAO {

    public boolean addWeeklyPlantingReport(WeeklyPlantingReport weeklyPlantingReport) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("INSERT INTO " + WeeklyPlantingReport.TABLE_NAME + " "
                    + "(" + WeeklyPlantingReport.COLUMN_CROPSTAGE + ", " + WeeklyPlantingReport.COLUMN_DATEREPORTED + ", " + WeeklyPlantingReport.COLUMN_HEIGHT + ", "
                    + WeeklyPlantingReport.COLUMN_IMAGE + ", " + WeeklyPlantingReport.COLUMN_PLANTINGREPORTID + ", " + WeeklyPlantingReport.COLUMN_AREAPLANTED + ", " 
                    + WeeklyPlantingReport.COLUMN_WATERLEVEL + ") "
                    + "VALUES(?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, weeklyPlantingReport.getCropStage());
            ps.setString(2, weeklyPlantingReport.getDateReported());
            ps.setDouble(3, weeklyPlantingReport.getHeight());
            ps.setString(4, weeklyPlantingReport.getImage());
            ps.setInt(5, weeklyPlantingReport.getPlantingReportID());
            ps.setDouble(6, weeklyPlantingReport.getAreaPlanted());
            ps.setDouble(7, weeklyPlantingReport.getWaterLevel());

            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(WeeklyPlantingReportDAO.class.getName()).log(Level.SEVERE, null, x);
            return false;
        }
        return true;
    }

    public ArrayList<WeeklyPlantingReport> getListOfWeeklyPlantingReports() {
        ArrayList<WeeklyPlantingReport> weeklyPlantingReports = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + WeeklyPlantingReport.TABLE_NAME);

            ResultSet rs = ps.executeQuery();
            weeklyPlantingReports = getDataFromResultSet(rs);

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(WeeklyPlantingReportDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return weeklyPlantingReports;
    }
    
    public ArrayList<WeeklyPlantingReport> getListOfWeeklyPlantingReportsFromMunicipality(int municipalityID) {
        ArrayList<WeeklyPlantingReport> weeklyPlantingReports = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT WPR.* "
                    + "FROM WeeklyPlantingReport WPR JOIN PlantingReport PR ON PR.PlantingReportID = WPR.PlantingReportID "
                    + "                             JOIN Plot P ON PR.PlotID = P.PlotID "
                    + "                             JOIN Farm F ON P.FarmID = F.FarmID "
                    + "                             JOIN Barangay B ON B.BarangayID = F.BarangayID "
                    + "                             JOIN Municipality M ON M.MunicipalityID = B.MunicipalityID "
                    + "WHERE M.MunicipalityID = ?");
            ps.setInt(1, municipalityID);

            ResultSet rs = ps.executeQuery();
            weeklyPlantingReports = getDataFromResultSet(rs);

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(WeeklyPlantingReportDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return weeklyPlantingReports;
    }
    
    public WeeklyPlantingReport getLatestWeeklyPlantingReportForPlantingReportID(int plantingReportID) {
        ArrayList<WeeklyPlantingReport> weeklyPlantingReports = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT WPR1.*\n"
                    + "FROM WeeklyPlantingReport WPR1 JOIN (SELECT MAX(STR_TO_DATE(WPR2.DateReported, '%m-%d-%Y')) AS 'LatestReport', WPR2.PlantingReportID\n"
                    + "                                     FROM WeeklyPlantingReport WPR2\n"
                    + "                                     WHERE WPR2.PlantingReportID = ?) T1 ON WPR1.PlantingReportID = T1.PlantingReportID\n"
                    + "WHERE STR_TO_DATE(WPR1.DateReported, '%m-%d-%Y') = T1.LatestReport");
            ps.setInt(1, plantingReportID);

            ResultSet rs = ps.executeQuery();
            weeklyPlantingReports = getDataFromResultSet(rs);

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(WeeklyPlantingReportDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return weeklyPlantingReports.get(0);
    }

    public WeeklyPlantingReport getLatestWeeklyPlantingReportForPlantingReportIDBefore(int plantingReportID, String date) {
        ArrayList<WeeklyPlantingReport> weeklyPlantingReports = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT WPR1.*\n"
                    + "FROM WeeklyPlantingReport WPR1 JOIN (SELECT MAX(STR_TO_DATE(WPR2.DateReported, '%m-%d-%Y')) AS 'LatestReport', WPR2.PlantingReportID\n"
                    + "                                     FROM WeeklyPlantingReport WPR2\n"
                    + "                                     WHERE STR_TO_DATE(WPR2.DateReported, '%m-%d-%Y') <= STR_TO_DATE(?, '%m-%d-%Y') AND WPR2.PlantingReportID = ?) T1 ON WPR1.PlantingReportID = T1.PlantingReportID\n"
                    + "WHERE STR_TO_DATE(WPR1.DateReported, '%m-%d-%Y') = T1.LatestReport");
            ps.setString(1, date);
            ps.setInt(2, plantingReportID);

            ResultSet rs = ps.executeQuery();
            weeklyPlantingReports = getDataFromResultSet(rs);

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(WeeklyPlantingReportDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return weeklyPlantingReports.get(0);
    }

    private ArrayList<WeeklyPlantingReport> getDataFromResultSet(ResultSet rs) throws SQLException {
        ArrayList<WeeklyPlantingReport> weeklyPlantingReports = new ArrayList<>();
        while (rs.next()) {
            WeeklyPlantingReport weeklyPlantingReport = new WeeklyPlantingReport();
            weeklyPlantingReport.setCropStage(rs.getString(WeeklyPlantingReport.COLUMN_CROPSTAGE));
            weeklyPlantingReport.setDateReported(rs.getString(WeeklyPlantingReport.COLUMN_DATEREPORTED));
            weeklyPlantingReport.setHeight(rs.getDouble(WeeklyPlantingReport.COLUMN_HEIGHT));
            weeklyPlantingReport.setImage(rs.getString(WeeklyPlantingReport.COLUMN_IMAGE));
            weeklyPlantingReport.setPlantingReportID(rs.getInt(WeeklyPlantingReport.COLUMN_PLANTINGREPORTID));
            weeklyPlantingReport.setWaterLevel(rs.getDouble(WeeklyPlantingReport.COLUMN_WATERLEVEL));
            weeklyPlantingReport.setAreaPlanted(rs.getDouble(WeeklyPlantingReport.COLUMN_AREAPLANTED));
            weeklyPlantingReports.add(weeklyPlantingReport);
        }
        return weeklyPlantingReports;
    }
}
