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
    
    public ArrayList<Municipality> getMunicipalAreaMonitoring(String date) {
        ArrayList<Municipality> municipalities =  new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT T1.MunicipalityName, T1.TotalArea, T2.PlantedArea, T2.MinorDamaged, T2.MajorDamaged\n"
                    + "FROM (SELECT M.MunicipalityName, SUM(P.PlotSize) AS 'TotalArea'\n"
                    + "	     FROM Municipality M JOIN Barangay B ON M.MunicipalityID = B.MunicipalityID\n"
                    + "		      	         JOIN Farm F ON F.BarangayID = B.BarangayID\n"
                    + "                          JOIN Plot P ON P.FarmID = F.FarmID\n"
                    + "      GROUP BY M.MunicipalityName) T1\n"
                    + "LEFT JOIN (SELECT IT1.MunicipalityName, SUM(IT1.PlotSize) AS 'PlantedArea', SUM(IT1.MinorDamaged) AS 'MinorDamaged', SUM(IT1.MajorDamaged) AS 'MajorDamaged'\n"
                    + "		  FROM (SELECT PR.*, M.MunicipalityName, P.PlotSize, MAX(STR_TO_DATE(CONCAT(PR.HarvestMonth, '-', PR.HarvestDay, '-', PR.HarvestYear), '%m-%d-%Y')), SUM(DR.AreaAffected) AS 'MinorDamaged', SUM(DR.AreaDamaged) AS 'MajorDamaged'\n"
                    + "			FROM PlantingReport PR JOIN Plot P ON PR.PlotID = P.PlotID\n"
                    + "                                        JOIN Farm F ON P.FarmID = F.FarmID\n"
                    + "                                        JOIN Barangay B ON F.BarangayID = B.BarangayID\n"
                    + "                                        JOIN Municipality M ON B.MunicipalityID = M.MunicipalityID\n"
                    + "                                        LEFT JOIN DamageIncident DI ON PR.PlantingReportID = DI.PlantingReportID\n"
                    + "                                        LEFT JOIN DamageReport DR ON DI.DamageIncidentID = DR.DamageIncidentID\n"
                    + "                                        LEFT JOIN (SELECT DR1.DamageIncidentID, MAX(STR_TO_DATE(DR1.DateReported, '%m-%d-%Y')) AS 'RecentDate'\n"
                    + "                                              FROM DamageReport DR1\n"
                    + "                                              GROUP BY DR1.DamageIncidentID) IDR ON IDR.DamageIncidentID = DR.DamageIncidentID AND IDR.RecentDate = STR_TO_DATE(DR.DateReported,'%m-%d-%Y')\n"
                    + "                 WHERE STR_TO_DATE(CONCAT(PR.HarvestMonth, '-', PR.HarvestDay, '-', PR.HarvestYear), '%m-%d-%Y') > STR_TO_DATE(?, '%m-%d-%Y')\n"
                    + "				AND STR_TO_DATE(CONCAT(PR.PlantedMonth, '-', PR.PlantedDay, '-', PR.PlantedYear), '%m-%d-%Y') <= STR_TO_DATE(?, '%m-%d-%Y')\n"
                    + "			GROUP BY PR.PlotID) IT1\n"
                    + "           GROUP BY IT1.MunicipalityName) T2 ON T1.MunicipalityName = T2.MunicipalityName\n"
                    + "GROUP BY T1.MunicipalityName;");
            ps.setString(1, date);
            ps.setString(2, date);

            ResultSet rs = ps.executeQuery();
            System.out.println(rs.getFetchSize());
            while (rs.next()) {
                Municipality municipality = new Municipality();
                municipality.setMunicipalityName(rs.getString("MunicipalityName"));
                municipality.setArea(rs.getDouble("TotalArea"));
                municipality.setPlantedArea(rs.getDouble("PlantedArea"));
                municipality.setMinorDamagedArea(rs.getDouble("MinorDamaged"));
                municipality.setMajorDamagedArea(rs.getDouble("MajorDamaged"));
                municipality.setUnplantedArea(rs.getDouble("TotalArea") - rs.getDouble("PlantedArea"));
                municipalities.add(municipality);
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(MunicipalityDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return municipalities ;
    }
    
    public ArrayList<Municipality> getMunicipalCropGrowthMonitoring(String date) {
        ArrayList<Municipality> municipalities = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT T1.MunicipalityName, T2.CropStage, SUM(T1.PlotSize) AS 'PlantedArea' "
                    + "FROM (SELECT PR.*, M.MunicipalityName, P.PlotSize, MAX(STR_TO_DATE(CONCAT(PR.HarvestMonth, '-', PR.HarvestDay, '-', PR.HarvestYear), '%m-%d-%Y')) "
                    + "      FROM PlantingReport PR JOIN Plot P ON PR.PlotID = P.PlotID "
                    + "                             JOIN Farm F ON P.FarmID = F.FarmID "
                    + "                             JOIN Barangay B ON F.BarangayID = B.BarangayID "
                    + "                             JOIN Municipality M ON B.MunicipalityID = M.MunicipalityID "
                    + "      WHERE STR_TO_DATE(CONCAT(PR.HarvestMonth, '-', PR.HarvestDay, '-', PR.HarvestYear), '%m-%d-%Y') > STR_TO_DATE(?, '%m-%d-%Y')"
                    + "             AND STR_TO_DATE(CONCAT(PR.PlantedMonth, '-', PR.PlantedDay, '-', PR.PlantedYear), '%m-%d-%Y') <= STR_TO_DATE(?, '%m-%d-%Y') "
                    + "      GROUP BY PR.PlotID) T1 "
                    + "JOIN (SELECT WPR1.* "
                    + "      FROM WeeklyPlantingReport WPR1 JOIN (SELECT MAX(STR_TO_DATE(WPR2.DateReported, '%m-%d-%Y')) AS 'LatestReport', WPR2.PlantingReportID "
                    + "                                           FROM WeeklyPlantingReport WPR2 "
                    + "                                           WHERE STR_TO_DATE(WPR2.DateReported, '%m-%d-%Y') < STR_TO_DATE(?, '%m-%d-%Y')"
                    + "                                           GROUP BY WPR2.PlantingReportID) IT1 "
                    + "                                     ON WPR1.PlantingReportID = IT1.PlantingReportID AND STR_TO_DATE(WPR1.DateReported, '%m-%d-%Y') = IT1.LatestReport) T2 "
                    + "ON T1.PlantingReportID = T2.PlantingReportID "
                    + "GROUP BY T1.MunicipalityName, T2.CropStage");
            ps.setString(1, date);
            ps.setString(2, date);
            ps.setString(3, date);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Municipality municipal = new Municipality();
                municipal.setMunicipalityName(rs.getString("MunicipalityName"));
                municipal.setCropStage(rs.getString("CropStage"));
                municipal.setArea(rs.getDouble("PlantedArea"));
                municipalities.add(municipal);
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(MunicipalityDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return municipalities;
    }
    
    public ArrayList<Municipality> getMunicipalNotification() {
        ArrayList<Municipality> barangays = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT T1.MunicipalityName, T1.BarangayName, T2.BarangayID, T1.ProblemName, COUNT(T1.FarmName) AS 'FarmsAffected', T2.TotalFarmCount, T2.TotalArea, T1.AreaAffected, T1.AreaDamaged\n"
                    + "FROM (SELECT DI.*, PM.ProblemName, M.MunicipalityName, B.BarangayName, F.FarmName, DR2.AreaAffected, DR2.AreaDamaged\n"
                    + "      FROM DamageReport DR2 JOIN DamageIncident DI ON DI.DamageIncidentID = DR2.DamageIncidentID\n"
                    + "                            JOIN PlantingReport PR ON PR.PlantingReportID = DI.PlantingReportID\n"
                    + "                            JOIN Plot P ON PR.PlotID = P.PlotID\n"
                    + "                            JOIN Farm F ON F.FarmID = P.FarmID\n"
                    + "                            JOIN Barangay B ON B.BarangayID = F.BarangayID\n"
                    + "                            JOIN Municipality M ON M.MunicipalityID = B.MunicipalityID\n"
                    + "                            JOIN Problem PM ON PM.ProblemID = DI.ProblemReported\n"
                    + "                            JOIN (SELECT DR1.DamageIncidentID, MAX(STR_TO_DATE(DR1.DateReported, '%m-%d-%Y')) AS 'RecentDate'\n"
                    + "					 FROM DamageReport DR1\n"
                    + "                                  GROUP BY DR1.DamageIncidentID) IT1 ON DR2.DamageIncidentID = IT1.DamageIncidentID AND STR_TO_DATE(DR2.DateReported, '%m-%d-%Y') = IT1.RecentDate\n"
                    + "      WHERE DI.IncidentStatus = 'Approved'\n"
                    + "      GROUP BY DI.ProblemReported, PR.PlantingReportID, P.PlotID, F.FarmID, B.BarangayID, M.MunicipalityID) T1\n"
                    + "JOIN (SELECT M.MunicipalityName, B.*, SUM(P.PlotSize) AS 'TotalArea', COUNT(F.FarmName) AS 'TotalFarmCount'\n"
                    + "      FROM Municipality M JOIN Barangay B ON M.MunicipalityID = B.MunicipalityID\n"
                    + "				 JOIN Farm F ON F.BarangayID = B.BarangayID\n"
                    + "                          JOIN Plot P ON P.FarmID = F.FarmID\n"
                    + "      GROUP BY M.MunicipalityName, B.BarangayName) T2 ON T1.BarangayName = T2.BarangayName AND T1.MunicipalityName = T2.MunicipalityName\n"
                    + "GROUP BY T1.BarangayName, T1.MunicipalityName, T1.ProblemName");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Municipality municipality = new Municipality();
                municipality.setBarangayName(rs.getString("BarangayName"));
                municipality.setMunicipalityName(rs.getString("MunicipalityName"));
                municipality.setProblemName(rs.getString("ProblemName"));
                municipality.setFarmCount(rs.getInt("TotalFarmCount"));
                municipality.setFarmAffected(rs.getInt("FarmsAffected"));
                municipality.setArea(rs.getDouble("TotalArea"));
                municipality.setMinorDamagedArea(rs.getDouble("AreaAffected"));
                municipality.setMajorDamagedArea(rs.getDouble("AreaDamaged"));
                barangays.add(municipality);
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(BarangayDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return barangays;
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
