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
import object.Barangay;
import object.DamageIncident;
import object.Plot;

/**
 *
 * @author RubySenpaii
 */
public class BarangayDAO {

    public boolean addBarangay(Barangay barangay) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("INSERT INTO " + Barangay.TABLE_NAME + " "
                    + "(" + Barangay.COLUMN_BARANGAYID + ", " + Barangay.COLUMN_BARANGAYNAME + ", " + Barangay.COLUMN_MUNICIPALITYID + ", " + Barangay.COLUMN_AREA + ") "
                    + "VALUES(?, ?, ?, ?)");
            ps.setInt(1, barangay.getBarangayID());
            ps.setString(2, barangay.getBarangayName());
            ps.setInt(3, barangay.getMunicipalityID());
            ps.setDouble(4, barangay.getArea());

            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(BarangayDAO.class.getName()).log(Level.SEVERE, null, x);
            return false;
        }
        return true;
    }

    public boolean updateBarangays(Barangay barangay) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("UPDATE " + Barangay.TABLE_NAME
                    + " SET " + Barangay.COLUMN_AREA + " = ?, " + Barangay.COLUMN_BARANGAYID + " = ?, " + Barangay.COLUMN_BARANGAYNAME + " = ?, "
                    + Barangay.COLUMN_MUNICIPALITYID + " = ? "
                    + "WHERE " + Barangay.COLUMN_BARANGAYID + " = ?");
            ps.setDouble(1, barangay.getArea());
            ps.setInt(2, barangay.getBarangayID());
            ps.setString(3, barangay.getBarangayName());
            ps.setInt(4, barangay.getMunicipalityID());
            ps.setDouble(5, barangay.getBarangayID());

            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(BarangayDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public ArrayList<Barangay> getListOfBarangays() {
        ArrayList<Barangay> barangays = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + Barangay.TABLE_NAME);

            ResultSet rs = ps.executeQuery();
            barangays = getDataFromResultSet(rs);

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(BarangayDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return barangays;
    }

    public ArrayList<Barangay> getListOfBarangaysInMunicipality(int municipalityID) {
        ArrayList<Barangay> barangays = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + Barangay.TABLE_NAME + " WHERE " + Barangay.COLUMN_MUNICIPALITYID + " = ?");
            ps.setInt(1, municipalityID);

            ResultSet rs = ps.executeQuery();
            barangays = getDataFromResultSet(rs);

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(BarangayDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return barangays;
    }

    public ArrayList<Barangay> getListOfBarangayProductionInMunicipality(int municipalityID, int year) {
        ArrayList<Barangay> barangays = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            String dateStart = "03-16-" + year;
            String dateEnd = "03-15-" + (year + 1);
            PreparedStatement ps = conn.prepareStatement("SELECT B.*, SUM(PR.AmountHarvested) AS 'TotalHarvested' \n"
                    + "FROM Barangay B JOIN Farm F ON B.BarangayID = F.BarangayID\n"
                    + "                JOIN Plot P ON F.FarmID = P.FarmID\n"
                    + "                JOIN PlantingReport PR ON PR.PlotID = P.PlotID\n"
                    + "WHERE STR_TO_DATE(CONCAT(PR.HarvestMonth, '-', PR.HarvestDay, '-', PR.HarvestYear), '%m-%d-%Y') > STR_TO_DATE(?, '%m-%d-%Y')\n"
                    + "		AND STR_TO_DATE(CONCAT(PR.HarvestMonth, '-', PR.HarvestDay, '-', PR.HarvestYear), '%m-%d-%Y') < STR_TO_DATE(?, '%m-%d-%Y')\n"
                    + "        AND B.MunicipalityID = ?\n"
                    + "GROUP BY B.BarangayID");
            ps.setString(1, dateStart);
            ps.setString(2, dateEnd);
            ps.setInt(3, municipalityID);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Barangay barangay = new Barangay();
                barangay.setArea(rs.getDouble(Barangay.COLUMN_AREA));
                barangay.setBarangayID(rs.getInt(Barangay.COLUMN_BARANGAYID));
                barangay.setBarangayName(rs.getString(Barangay.COLUMN_BARANGAYNAME));
                barangay.setMunicipalityID(rs.getInt(Barangay.COLUMN_MUNICIPALITYID));
                barangay.setHarvest(rs.getDouble("TotalHarvested"));
                barangays.add(barangay);
            }

            rs.close();
            ps.close();
            conn.close();

            if (barangays.isEmpty()) {
                ArrayList<Barangay> barangayList = getListOfBarangaysInMunicipality(municipalityID);
                barangays.addAll(barangayList);
            }
        } catch (SQLException x) {
            Logger.getLogger(BarangayDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return barangays;
    }

    public ArrayList<Barangay> getBarangayCropGrowthMonitoring(String municipalityName, String date) {
        ArrayList<Barangay> barangays = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT T1.BarangayName, T1.MunicipalityName, T2.CropStage, SUM(T1.PlotSize) AS 'PlantedArea' "
                    + "FROM (SELECT PR.*, B.BarangayName, M.MunicipalityName, P.PlotSize, MAX(STR_TO_DATE(CONCAT(PR.HarvestMonth, '-', PR.HarvestDay, '-', PR.HarvestYear), '%m-%d-%Y')) "
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
                    + "GROUP BY T1.BarangayName, T1.MunicipalityName, T2.CropStage "
                    + "HAVING T1.MunicipalityName = ?");
            ps.setString(1, date);
            ps.setString(2, date);
            ps.setString(3, date);
            ps.setString(4, municipalityName);

            ResultSet rs = ps.executeQuery();
            System.out.println(rs.getFetchSize());
            while (rs.next()) {
                Barangay barangay = new Barangay();
                barangay.setBarangayName(rs.getString(Barangay.COLUMN_BARANGAYNAME));
                barangay.setMunicipalityName(rs.getString("MunicipalityName"));
                barangay.setCropStage(rs.getString("CropStage"));
                barangay.setArea(rs.getDouble("PlantedArea"));
                barangays.add(barangay);
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(BarangayDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return barangays;
    }

    public ArrayList<Barangay> getBarangayAreaMonitoring(int municipalityID, String date) {
        ArrayList<Barangay> barangays = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT T1.MunicipalityName, T1.BarangayName, T1.TotalArea, T2.PlantedArea, T2.MinorDamaged, T2.MajorDamaged\n"
                    + "FROM (SELECT M.MunicipalityName, B.BarangayName, SUM(P.PlotSize) AS 'TotalArea'\n"
                    + "	     FROM Municipality M JOIN Barangay B ON M.MunicipalityID = B.MunicipalityID\n"
                    + "		      	         JOIN Farm F ON F.BarangayID = B.BarangayID\n"
                    + "                          JOIN Plot P ON P.FarmID = F.FarmID\n"
                    + "      WHERE M.MunicipalityID = ?\n"
                    + "      GROUP BY M.MunicipalityName, B.BarangayName) T1\n"
                    + "LEFT JOIN (SELECT IT1.MunicipalityName, IT1.BarangayName, SUM(IT1.PlotSize) AS 'PlantedArea', IT1.MinorDamaged, IT1.MajorDamaged\n"
                    + "		  FROM (SELECT PR.*, B.BarangayName, M.MunicipalityName, P.PlotSize, MAX(STR_TO_DATE(CONCAT(PR.HarvestMonth, '-', PR.HarvestDay, '-', PR.HarvestYear), '%m-%d-%Y')), SUM(DR.AreaAffected) AS 'MinorDamaged', SUM(DR.AreaDamaged) AS 'MajorDamaged'\n"
                    + "			FROM PlantingReport PR JOIN Plot P ON PR.PlotID = P.PlotID\n"
                    + "                                        JOIN Farm F ON P.FarmID = F.FarmID\n"
                    + "                                        JOIN Barangay B ON F.BarangayID = B.BarangayID\n"
                    + "                                        JOIN Municipality M ON B.MunicipalityID = M.MunicipalityID\n"
                    + "                                        LEFT JOIN DamageIncident DI ON PR.PlantingReportID = DI.PlantingReportID\n"
                    + "                                        JOIN DamageReport DR ON DI.DamageIncidentID = DR.DamageIncidentID\n"
                    + "                                        JOIN (SELECT DR1.DamageIncidentID, MAX(STR_TO_DATE(DR1.DateReported, '%m-%d-%Y')) AS 'RecentDate'\n"
                    + "                                              FROM DamageReport DR1\n"
                    + "                                              GROUP BY DR1.DamageIncidentID) IDR ON IDR.DamageIncidentID = DR.DamageIncidentID AND IDR.RecentDate = STR_TO_DATE(DR.DateReported,'%m-%d-%Y')\n"
                    + "                 WHERE STR_TO_DATE(CONCAT(PR.HarvestMonth, '-', PR.HarvestDay, '-', PR.HarvestYear), '%m-%d-%Y') > STR_TO_DATE(?, '%m-%d-%Y')\n"
                    + "				AND STR_TO_DATE(CONCAT(PR.PlantedMonth, '-', PR.PlantedDay, '-', PR.PlantedYear), '%m-%d-%Y') <= STR_TO_DATE(?, '%m-%d-%Y')\n"
                    + "			GROUP BY PR.PlotID) IT1\n"
                    + "           GROUP BY IT1.MunicipalityName, IT1.BarangayName) T2 ON T1.BarangayName = T2.BarangayName AND T1.MunicipalityName = T2.MunicipalityName\n"
                    + "GROUP BY T1.BarangayName, T1.MunicipalityName;");
            ps.setInt(1, municipalityID);
            ps.setString(2, date);
            ps.setString(3, date);

            ResultSet rs = ps.executeQuery();
            System.out.println(rs.getFetchSize());
            while (rs.next()) {
                Barangay barangay = new Barangay();
                barangay.setBarangayName(rs.getString(Barangay.COLUMN_BARANGAYNAME));
                barangay.setMunicipalityName(rs.getString("MunicipalityName"));
                barangay.setArea(rs.getDouble("TotalArea"));
                barangay.setPlantedArea(rs.getDouble("PlantedArea"));
                barangay.setMinorDamagedArea(rs.getDouble("MinorDamaged"));
                barangay.setMajorDamagedArea(rs.getDouble("MajorDamaged"));
                barangay.setUnplantedArea(rs.getDouble("TotalArea") - rs.getDouble("PlantedArea"));
                barangays.add(barangay);
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(BarangayDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return barangays;
    }

    public ArrayList<Barangay> getMunicipalNotification(int municipalityID) {
        ArrayList<Barangay> barangays = new ArrayList<>();
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
                    + "      WHERE DI.IncidentStatus = 'Approved' AND M.MunicipalityID = ?\n"
                    + "      GROUP BY DI.ProblemReported, PR.PlantingReportID, P.PlotID, F.FarmID, B.BarangayID, M.MunicipalityID) T1\n"
                    + "JOIN (SELECT M.MunicipalityName, B.*, SUM(P.PlotSize) AS 'TotalArea', COUNT(F.FarmName) AS 'TotalFarmCount'\n"
                    + "      FROM Municipality M JOIN Barangay B ON M.MunicipalityID = B.MunicipalityID\n"
                    + "				 JOIN Farm F ON F.BarangayID = B.BarangayID\n"
                    + "                          JOIN Plot P ON P.FarmID = F.FarmID\n"
                    + "      GROUP BY M.MunicipalityName, B.BarangayName) T2 ON T1.BarangayName = T2.BarangayName AND T1.MunicipalityName = T2.MunicipalityName\n"
                    + "GROUP BY T1.BarangayName, T1.MunicipalityName, T1.ProblemName");
            ps.setInt(1, municipalityID);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Barangay barangay = new Barangay();
                barangay.setBarangayName(rs.getString(Barangay.COLUMN_BARANGAYNAME));
                barangay.setMunicipalityName(rs.getString("MunicipalityName"));
                barangay.setProblemName(rs.getString("ProblemName"));
                barangay.setFarmCount(rs.getInt("TotalFarmCount"));
                barangay.setFarmAffected(rs.getInt("FarmsAffected"));
                barangay.setArea(rs.getDouble("TotalArea"));
                barangay.setMinorDamagedArea(rs.getDouble("AreaAffected"));
                barangay.setMajorDamagedArea(rs.getDouble("AreaDamaged"));
                barangays.add(barangay);
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(BarangayDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return barangays;
    }

    public Barangay getBarangayInfoWithBrgyID(int barangayID) {
        ArrayList<Barangay> barangays = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + Barangay.TABLE_NAME + " WHERE " + Barangay.COLUMN_BARANGAYID + " = ?");
            ps.setInt(1, barangayID);

            ResultSet rs = ps.executeQuery();
            barangays = getDataFromResultSet(rs);

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(BarangayDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return barangays.get(0);
    }

    public Barangay getBarangayInfoWithBrgyName(String barangayName) {
        ArrayList<Barangay> barangays = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + Barangay.TABLE_NAME + " WHERE " + Barangay.COLUMN_BARANGAYNAME + " = ?");
            ps.setString(1, barangayName);

            ResultSet rs = ps.executeQuery();
            barangays = getDataFromResultSet(rs);

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(BarangayDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return barangays.get(0);
    }

    public ArrayList<Barangay> getBarangayListWithApprovedDamagesAndInfoFromMunicipality(int municipalityID) {
        ArrayList<Barangay> barangays = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT B.*, DI.EscalationLevel, PM.ProblemName, SUM(DR2.AreaAffected) AS 'AreaAffected', SUM(DR2.AreaDamaged) AS 'AreaDamaged', COUNT(F.FarmID) AS 'FarmsAffected'\n"
                    + "FROM DamageReport DR2 JOIN DamageIncident DI ON DI.DamageIncidentID = DR2.DamageIncidentID\n"
                    + "                      JOIN PlantingReport PR ON PR.PlantingReportID = DI.PlantingReportID\n"
                    + "                      JOIN Plot P ON PR.PlotID = P.PlotID\n"
                    + "                      JOIN Farm F ON F.FarmID = P.FarmID\n"
                    + "                      JOIN Barangay B ON B.BarangayID = F.BarangayID\n"
                    + "                      JOIN Municipality M ON M.MunicipalityID = B.MunicipalityID\n"
                    + "                      JOIN Problem PM ON PM.ProblemID = DI.ProblemReported\n"
                    + "			     JOIN (SELECT DR1.DamageIncidentID, MAX(DR1.DateReported) AS 'RecentDate'\n"
                    + "				   FROM DamageReport DR1\n"
                    + "		   		   GROUP BY DR1.DamageIncidentID) T1 ON DR2.DamageIncidentID = T1.DamageIncidentID\n"
                    + "WHERE DR2.DateReported = T1.RecentDate AND DI.Status = 'Approved' AND M.MunicipalityID = ?\n"
                    + "GROUP BY B.BarangayID, DI.ProblemReported");
            ps.setInt(1, municipalityID);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Barangay barangay = new Barangay();
                barangay.setArea(rs.getDouble(Barangay.COLUMN_AREA));
                barangay.setBarangayID(rs.getInt(Barangay.COLUMN_BARANGAYID));
                barangay.setBarangayName(rs.getString(Barangay.COLUMN_BARANGAYNAME));
                barangay.setMunicipalityID(rs.getInt(Barangay.COLUMN_MUNICIPALITYID));
                barangay.setProblemName(rs.getString("ProblemName"));
                barangay.setAreaAffected(rs.getDouble("AreaAffected"));
                barangay.setAreaDamaged(rs.getDouble("AreaDamaged"));
                barangay.setFarmCount(rs.getInt("FarmsAffected"));
                barangay.setEscalationLevel(rs.getString("EscalationLevel"));
                barangays.add(barangay);
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(BarangayDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return barangays;
    }

    private ArrayList<Barangay> getDataFromResultSet(ResultSet rs) throws SQLException {
        ArrayList<Barangay> barangays = new ArrayList<>();
        while (rs.next()) {
            Barangay barangay = new Barangay();
            barangay.setArea(rs.getDouble(Barangay.COLUMN_AREA));
            barangay.setBarangayID(rs.getInt(Barangay.COLUMN_BARANGAYID));
            barangay.setBarangayName(rs.getString(Barangay.COLUMN_BARANGAYNAME));
            barangay.setMunicipalityID(rs.getInt(Barangay.COLUMN_MUNICIPALITYID));
            barangays.add(barangay);
        }
        return barangays;
    }
}
