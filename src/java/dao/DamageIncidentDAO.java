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
import object.DamageIncident;
import object.Plot;

/**
 *
 * @author RubySenpaii
 */
public class DamageIncidentDAO {

    public boolean reportDamageIncident(DamageIncident damageIncident) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("INSERT INTO " + DamageIncident.TABLE_NAME + " "
                    + "(" + DamageIncident.COLUMN_APPROVEDBY + ", " + DamageIncident.COLUMN_CROPSTAGE + ", " + DamageIncident.COLUMN_DAMAGEDDAY + ", "
                    + DamageIncident.COLUMN_DAMAGEDMONTH + ", " + DamageIncident.COLUMN_DAMAGEDYEAR + ", " + DamageIncident.COLUMN_DAMAGEINCIDENTID + ", "
                    + DamageIncident.COLUMN_ESCALATIONLEVEL + ", " + DamageIncident.COLUMN_PLANTINGREPORTID + ", " + DamageIncident.COLUMN_PROBLEMREPORTED + ", "
                    + DamageIncident.COLUMN_REMARKS + ", " + DamageIncident.COLUMN_REPORTEDBY + ", " + DamageIncident.COLUMN_STATUS + ", " 
                    + DamageIncident.COLUMN_DEPLOYEDID + ") "
                    + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setInt(1, damageIncident.getApprovedBy());
            ps.setString(2, damageIncident.getCropStage());
            ps.setInt(3, damageIncident.getDamagedDay());
            ps.setInt(4, damageIncident.getDamagedMonth());
            ps.setInt(5, damageIncident.getDamagedYear());
            ps.setInt(6, damageIncident.getDamageIncidentID());
            ps.setString(7, damageIncident.getEscalationLevel());
            ps.setInt(8, damageIncident.getPlantingReportID());
            ps.setInt(9, damageIncident.getProblemReported());
            ps.setString(10, damageIncident.getRemarks());
            ps.setInt(11, damageIncident.getReportedBy());
            ps.setString(12, damageIncident.getStatus());
            ps.setInt(13, damageIncident.getDeployedID());

            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(DamageIncidentDAO.class.getName()).log(Level.SEVERE, null, x);
            return false;
        }
        return true;
    }

    public boolean updateDamageIncident(DamageIncident damageIncident) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("UPDATE " + DamageIncident.TABLE_NAME
                    + " SET " + DamageIncident.COLUMN_APPROVEDBY + " = ?, " + DamageIncident.COLUMN_CROPSTAGE + " = ?, " + DamageIncident.COLUMN_DAMAGEDDAY + " = ?, "
                    + DamageIncident.COLUMN_DAMAGEDMONTH + " = ?, " + DamageIncident.COLUMN_DAMAGEDYEAR + " = ?, " + DamageIncident.COLUMN_DAMAGEINCIDENTID + " = ?, "
                    + DamageIncident.COLUMN_ESCALATIONLEVEL + " = ?, " + DamageIncident.COLUMN_PLANTINGREPORTID + " = ?, " + DamageIncident.COLUMN_PROBLEMREPORTED + " = ?, "
                    + DamageIncident.COLUMN_REMARKS + " = ?, " + DamageIncident.COLUMN_REPORTEDBY + " = ?, " + DamageIncident.COLUMN_DEPLOYEDID + " = ?, " 
                    + DamageIncident.COLUMN_STATUS + " = ? "
                    + "WHERE " + DamageIncident.COLUMN_DAMAGEINCIDENTID + " = ?");
            ps.setInt(1, damageIncident.getApprovedBy());
            ps.setString(2, damageIncident.getCropStage());
            ps.setInt(3, damageIncident.getDamagedDay());
            ps.setInt(4, damageIncident.getDamagedMonth());
            ps.setInt(5, damageIncident.getDamagedYear());
            ps.setInt(6, damageIncident.getDamageIncidentID());
            ps.setString(7, damageIncident.getEscalationLevel());
            ps.setInt(8, damageIncident.getPlantingReportID());
            ps.setInt(9, damageIncident.getProblemReported());
            ps.setString(10, damageIncident.getRemarks());
            ps.setInt(11, damageIncident.getReportedBy());
            ps.setInt(12, damageIncident.getDeployedID());
            ps.setString(13, damageIncident.getStatus());
            ps.setInt(14, damageIncident.getDamageIncidentID());

            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DamageIncidentDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public ArrayList<DamageIncident> getListOfDamages() {
        ArrayList<DamageIncident> damages = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + DamageIncident.TABLE_NAME);

            ResultSet rs = ps.executeQuery();
            damages = getDataFromResultSet(rs);

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(DamageIncidentDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return damages;
    }

    public ArrayList<DamageIncident> getListOfPlantingReportsByProblemFarmBarangay(int municipalityID) {
        ArrayList<DamageIncident> damages = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT DI.*, PM.ProblemName, M.MunicipalityName, B.BarangayName, F.FarmName, DR2.AreaDamaged, DR2.AreaAffected\n"
                    + "FROM DamageReport DR2 JOIN DamageIncident DI ON DI.DamageIncidentID = DR2.DamageIncidentID\n"
                    + "                      JOIN PlantingReport PR ON PR.PlantingReportID = DI.PlantingReportID\n"
                    + "                      JOIN Plot P ON PR.PlotID = P.PlotID\n"
                    + "                      JOIN Farm F ON F.FarmID = P.FarmID\n"
                    + "                      JOIN Barangay B ON B.BarangayID = F.BarangayID\n"
                    + "                      JOIN Municipality M ON M.MunicipalityID = B.MunicipalityID\n"
                    + "                      JOIN Problem PM ON PM.ProblemID = DI.ProblemReported\n"
                    + "                      JOIN (SELECT DR1.DamageIncidentID, MAX(STR_TO_DATE(DR1.DateReported, '%m-%d-%Y')) AS 'RecentDate'\n"
                    + "                             FROM DamageReport DR1\n"
                    + "                           GROUP BY DR1.DamageIncidentID) T1 ON DR2.DamageIncidentID = T1.DamageIncidentID AND STR_TO_DATE(DR2.DateReported, '%m-%d-%Y') = T1.RecentDate\n"
                    + "WHERE DI.IncidentStatus = 'Approved' AND M.MunicipalityID = ?\n"
                    + "GROUP BY DI.ProblemReported, PR.PlantingReportID, P.PlotID, F.FarmID, B.BarangayID, M.MunicipalityID");
            ps.setInt(1, municipalityID);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DamageIncident damage = new DamageIncident();
                damage.setApprovedBy(rs.getInt(DamageIncident.COLUMN_APPROVEDBY));
                damage.setCropStage(rs.getString(DamageIncident.COLUMN_CROPSTAGE));
                damage.setDamageIncidentID(rs.getInt(DamageIncident.COLUMN_DAMAGEINCIDENTID));
                damage.setDamagedDay(rs.getInt(DamageIncident.COLUMN_DAMAGEDDAY));
                damage.setDamagedMonth(rs.getInt(DamageIncident.COLUMN_DAMAGEDMONTH));
                damage.setDamagedYear(rs.getInt(DamageIncident.COLUMN_DAMAGEDYEAR));
                damage.setEscalationLevel(rs.getString(DamageIncident.COLUMN_ESCALATIONLEVEL));
                damage.setPlantingReportID(rs.getInt(DamageIncident.COLUMN_PLANTINGREPORTID));
                damage.setProblemReported(rs.getInt(DamageIncident.COLUMN_PROBLEMREPORTED));
                damage.setReportedBy(rs.getInt(DamageIncident.COLUMN_REPORTEDBY));
                damage.setDeployedID(rs.getInt(DamageIncident.COLUMN_DEPLOYEDID));
                damage.setStatus(rs.getString(DamageIncident.COLUMN_STATUS));
                damage.setProblemName(rs.getString("ProblemName"));
                damage.setMunicipalityName(rs.getString("MunicipalityName"));
                damage.setBarangayName(rs.getString("BarangayName"));
                damage.setFarmName(rs.getString("FarmName"));
                damage.setTotalAreaDamaged(rs.getDouble("AreaDamaged"));
                damage.setTotalAreaAffected(rs.getDouble("AreaAffected"));
                damages.add(damage);
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(DamageIncidentDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return damages;
    }

    public ArrayList<DamageIncident> getListOfDamagesByStatus(String status) {
        ArrayList<DamageIncident> damages = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT DI.*, T2.AreaAffected, T2.AreaDamaged, P.ProblemName, F.FarmName\n"
                    + "FROM DamageIncident DI JOIN Problem P ON DI.ProblemReported = P.ProblemID\n"
                    + "                       JOIN PlantingReport PR ON DI.PlantingReportID = PR.PlantingReportID\n"
                    + "                       JOIN Plot PL ON PR.PlotID = PL.PlotID\n"
                    + "                       JOIN Farm F ON PL.FarmID = F.FarmID\n"
                    + "                       JOIN (SELECT DR1.*\n"
                    + "                             FROM DamageReport DR1 JOIN (SELECT DR2.DamageIncidentID, MAX(STR_TO_DATE(DR2.DateReported, '%m-%d-%Y')) AS 'DateReported'\n"
                    + "								FROM DamageReport DR2\n"
                    + "                                                        GROUP BY DR2.DamageIncidentID) T1 ON DR1.DamageIncidentID = T1.DamageIncidentID\n"
                    + "                             WHERE STR_TO_DATE(DR1.DateReported, '%m-%d-%Y') = T1.DateReported) T2 ON DI.DamageIncidentID = T2.DamageIncidentID\n"
                    + "WHERE DI.IncidentStatus = ?");
            ps.setString(1, status);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DamageIncident damage = new DamageIncident();
                damage.setApprovedBy(rs.getInt(DamageIncident.COLUMN_APPROVEDBY));
                damage.setCropStage(rs.getString(DamageIncident.COLUMN_CROPSTAGE));
                damage.setDamageIncidentID(rs.getInt(DamageIncident.COLUMN_DAMAGEINCIDENTID));
                damage.setDamagedDay(rs.getInt(DamageIncident.COLUMN_DAMAGEDDAY));
                damage.setDamagedMonth(rs.getInt(DamageIncident.COLUMN_DAMAGEDMONTH));
                damage.setDamagedYear(rs.getInt(DamageIncident.COLUMN_DAMAGEDYEAR));
                damage.setEscalationLevel(rs.getString(DamageIncident.COLUMN_ESCALATIONLEVEL));
                damage.setPlantingReportID(rs.getInt(DamageIncident.COLUMN_PLANTINGREPORTID));
                damage.setProblemReported(rs.getInt(DamageIncident.COLUMN_PROBLEMREPORTED));
                damage.setReportedBy(rs.getInt(DamageIncident.COLUMN_REPORTEDBY));
                damage.setDeployedID(rs.getInt(DamageIncident.COLUMN_DEPLOYEDID));
                damage.setStatus(rs.getString(DamageIncident.COLUMN_STATUS));
                damage.setTotalAreaDamaged(rs.getDouble("AreaDamaged"));
                damage.setTotalAreaAffected(rs.getDouble("AreaAffected"));
                damage.setProblemName(rs.getString("ProblemName"));
                damage.setFarmName(rs.getString("FarmName"));
                damages.add(damage);
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(DamageIncidentDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return damages;
    }

    public DamageIncident getDamageIncidentWithIncidentID(int incidentID) {
        ArrayList<DamageIncident> damages = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + DamageIncident.TABLE_NAME + " WHERE " + DamageIncident.COLUMN_DAMAGEINCIDENTID + " = ?");
            ps.setInt(1, incidentID);

            ResultSet rs = ps.executeQuery();
            damages = getDataFromResultSet(rs);

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(DamageIncidentDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return damages.get(0);
    }

    public ArrayList<DamageIncident> getApprovedDamageIncidentForPlantingReportBefore(int plantingReportID, String date) {
        ArrayList<DamageIncident> damages = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT DI.*, T2.AreaAffected, T2.AreaDamaged\n"
                    + "FROM DamageIncident DI JOIN (SELECT DR1.*\n"
                    + "                             FROM DamageReport DR1 JOIN (SELECT DR2.DamageIncidentID, MAX(STR_TO_DATE(DR2.DateReported, '%m-%d-%Y')) AS 'DateReported'\n"
                    + "								FROM DamageReport DR2\n"
                    + "                                                         WHERE STR_TO_DATE(DR2.DateReported, '%m-%d-%Y') <= STR_TO_DATE(?, '%m-%d-%Y')\n"
                    + "								GROUP BY DR2.DamageIncidentID) T1 ON DR1.DamageIncidentID = T1.DamageIncidentID\n"
                    + "                             WHERE STR_TO_DATE(DR1.DateReported, '%m-%d-%Y') = T1.DateReported) T2 ON DI.DamageIncidentID = T2.DamageIncidentID\n"
                    + "WHERE DI.PlantingReportID = ? AND DI.IncidentStatus = 'Approved'");
            ps.setString(1, date);
            ps.setInt(2, plantingReportID);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DamageIncident damage = new DamageIncident();
                damage.setApprovedBy(rs.getInt(DamageIncident.COLUMN_APPROVEDBY));
                damage.setCropStage(rs.getString(DamageIncident.COLUMN_CROPSTAGE));
                damage.setDamageIncidentID(rs.getInt(DamageIncident.COLUMN_DAMAGEINCIDENTID));
                damage.setDamagedDay(rs.getInt(DamageIncident.COLUMN_DAMAGEDDAY));
                damage.setDamagedMonth(rs.getInt(DamageIncident.COLUMN_DAMAGEDMONTH));
                damage.setDamagedYear(rs.getInt(DamageIncident.COLUMN_DAMAGEDYEAR));
                damage.setEscalationLevel(rs.getString(DamageIncident.COLUMN_ESCALATIONLEVEL));
                damage.setPlantingReportID(rs.getInt(DamageIncident.COLUMN_PLANTINGREPORTID));
                damage.setProblemReported(rs.getInt(DamageIncident.COLUMN_PROBLEMREPORTED));
                damage.setReportedBy(rs.getInt(DamageIncident.COLUMN_REPORTEDBY));
                damage.setDeployedID(rs.getInt(DamageIncident.COLUMN_DEPLOYEDID));
                damage.setStatus(rs.getString(DamageIncident.COLUMN_STATUS));
                damage.setTotalAreaDamaged(rs.getDouble("AreaDamaged"));
                damage.setTotalAreaAffected(rs.getDouble("AreaAffected"));
                damages.add(damage);
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(DamageIncidentDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return damages;
    }

    public ArrayList<DamageIncident> getDamageIncidentForPlantingReportBefore(int plantingReportID, String date) {
        ArrayList<DamageIncident> damages = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT DI.*, T2.AreaAffected, T2.AreaDamaged\n"
                    + "FROM DamageIncident DI JOIN (SELECT DR1.*\n"
                    + "                             FROM DamageReport DR1 JOIN (SELECT DR2.DamageIncidentID, MAX(STR_TO_DATE(DR2.DateReported, '%m-%d-%Y')) AS 'DateReported'\n"
                    + "								FROM DamageReport DR2\n"
                    + "                                                         WHERE STR_TO_DATE(DR2.DateReported, '%m-%d-%Y') <= STR_TO_DATE(?, '%m-%d-%Y')\n"
                    + "								GROUP BY DR2.DamageIncidentID) T1 ON DR1.DamageIncidentID = T1.DamageIncidentID\n"
                    + "                             WHERE STR_TO_DATE(DR1.DateReported, '%m-%d-%Y') = T1.DateReported) T2 ON DI.DamageIncidentID = T2.DamageIncidentID\n"
                    + "WHERE DI.PlantingReportID = ? AND DI.IncidentStatus != 'Reported' AND DI.IncidentStatus != 'Rejected'");
            ps.setString(1, date);
            ps.setInt(2, plantingReportID);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DamageIncident damage = new DamageIncident();
                damage.setApprovedBy(rs.getInt(DamageIncident.COLUMN_APPROVEDBY));
                damage.setCropStage(rs.getString(DamageIncident.COLUMN_CROPSTAGE));
                damage.setDamageIncidentID(rs.getInt(DamageIncident.COLUMN_DAMAGEINCIDENTID));
                damage.setDamagedDay(rs.getInt(DamageIncident.COLUMN_DAMAGEDDAY));
                damage.setDamagedMonth(rs.getInt(DamageIncident.COLUMN_DAMAGEDMONTH));
                damage.setDamagedYear(rs.getInt(DamageIncident.COLUMN_DAMAGEDYEAR));
                damage.setEscalationLevel(rs.getString(DamageIncident.COLUMN_ESCALATIONLEVEL));
                damage.setPlantingReportID(rs.getInt(DamageIncident.COLUMN_PLANTINGREPORTID));
                damage.setProblemReported(rs.getInt(DamageIncident.COLUMN_PROBLEMREPORTED));
                damage.setReportedBy(rs.getInt(DamageIncident.COLUMN_REPORTEDBY));
                damage.setDeployedID(rs.getInt(DamageIncident.COLUMN_DEPLOYEDID));
                damage.setStatus(rs.getString(DamageIncident.COLUMN_STATUS));
                damage.setTotalAreaDamaged(rs.getDouble("AreaDamaged"));
                damage.setTotalAreaAffected(rs.getDouble("AreaAffected"));
                damages.add(damage);
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(DamageIncidentDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return damages;
    }

    public ArrayList<DamageIncident> getNewlyReportedIncidentsByBarangayInMunicipality(int municipalityID) {
        ArrayList<DamageIncident> damages = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT DI.*, ROUND(SUM(T1.MaxDamaged), 2) AS 'TotalDamages', ROUND(SUM(T1.MaxAffected), 2) AS 'TotalAffected', COUNT(DI.DamageIncidentID) AS 'FarmsAffected', PM.ProblemName, B.BarangayName\n"
                    + "FROM DamageIncident DI JOIN PlantingReport PR ON DI.PlantingReportID = PR.PlantingReportID\n"
                    + "                       JOIN Plot P ON P.PlotID = PR.PlotID\n"
                    + "                       JOIN Farm F ON F.FarmID = P.FarmID\n"
                    + "                       JOIN Barangay B ON B.BarangayID = F.BarangayID\n"
                    + "                       JOIN Municipality M ON M.MunicipalityiD = B.MunicipalityID\n"
                    + "                       JOIN Problem PM ON PM.ProblemID = DI.ProblemReported\n"
                    + "                       JOIN ( SELECT DR.DamageIncidentID AS 'IncidentID', MAX(DR.AreaDamaged) AS 'MaxDamaged', MAX(DR.AreaAffected) AS 'MaxAffected'\n"
                    + "                             FROM DamageReport DR\n"
                    + "                             GROUP BY DR.DamageIncidentID ) T1 ON T1.IncidentID = DI.DamageIncidentID\n"
                    + "WHERE DI.IncidentStatus = 'Reported' AND M.MunicipalityID = ?\n"
                    + "GROUP BY DI.ProblemReported, B.BarangayID");
            ps.setInt(1, municipalityID);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DamageIncident damage = new DamageIncident();
                damage.setApprovedBy(rs.getInt(DamageIncident.COLUMN_APPROVEDBY));
                damage.setCropStage(rs.getString(DamageIncident.COLUMN_CROPSTAGE));
                damage.setDamageIncidentID(rs.getInt(DamageIncident.COLUMN_DAMAGEINCIDENTID));
                damage.setDamagedDay(rs.getInt(DamageIncident.COLUMN_DAMAGEDDAY));
                damage.setDamagedMonth(rs.getInt(DamageIncident.COLUMN_DAMAGEDMONTH));
                damage.setDamagedYear(rs.getInt(DamageIncident.COLUMN_DAMAGEDYEAR));
                damage.setEscalationLevel(rs.getString(DamageIncident.COLUMN_ESCALATIONLEVEL));
                damage.setPlantingReportID(rs.getInt(DamageIncident.COLUMN_PLANTINGREPORTID));
                damage.setProblemReported(rs.getInt(DamageIncident.COLUMN_PROBLEMREPORTED));
                damage.setReportedBy(rs.getInt(DamageIncident.COLUMN_REPORTEDBY));
                damage.setDeployedID(rs.getInt(DamageIncident.COLUMN_DEPLOYEDID));
                damage.setStatus(rs.getString(DamageIncident.COLUMN_STATUS));
                damage.setFarmsAffected(rs.getInt("FarmsAffected"));
                damage.setTotalAreaDamaged(rs.getDouble("TotalDamages"));
                damage.setTotalAreaAffected(rs.getDouble("TotalAffected"));
                damage.setProblemName(rs.getString("ProblemName"));
                damage.setBarangayName(rs.getString("barangayName"));
                damages.add(damage);
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(DamageIncidentDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return damages;
    }

    public double getTotalDamagesFromFarmOnYear(int farmID, int year) {
        double damages = 0;
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            String dateStart = "03-16-" + year;
            String dateEnd = "03-15-" + (year + 1);
            PreparedStatement ps = conn.prepareStatement("SELECT ROUND(SUM(T1.MaxDamaged), 2) AS 'TotalDamages' "
                    + "FROM DamageIncident DI JOIN PlantingReport PR ON DI.PlantingReportID = PR.PlantingReportID "
                    + "                       JOIN Plot P ON P.PlotID = PR.PlotID "
                    + "                       JOIN Farm F ON F.FarmID = P.FarmID "
                    + "                       JOIN ( SELECT DR.DamageIncidentID AS 'IncidentID', MAX(DR.AreaDamaged) AS 'MaxDamaged' "
                    + "						    	FROM DamageReport DR "
                    + "							GROUP BY DR.DamageIncidentID ) T1 ON T1.IncidentID = DI.DamageIncidentID "
                    + "WHERE F.FarmID = ? AND STR_TO_DATE(CONCAT(DI.DamagedMonth, '-', DI.DamagedDay, '-', DI.DamagedYear), '%m-%d-%Y') > STR_TO_DATE(?, '%m-%d-%Y')\n"
                    + "		AND STR_TO_DATE(CONCAT(DI.DamagedMonth, '-', DI.DamagedDay, '-', DI.DamagedYear), '%m-%d-%Y') < STR_TO_DATE(?, '%m-%d-%Y')");
            ps.setInt(1, farmID);
            ps.setString(2, dateStart);
            ps.setString(3, dateEnd);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                damages = rs.getDouble("totalDamages");
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(DamageIncidentDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return damages;
    }

    public ArrayList<DamageIncident> getBarangayProblemListWithApprovedDamagesInMunicipality(int municipalityID) {
        ArrayList<DamageIncident> damages = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT DI.*, PM.ProblemName, B.BarangayName, SUM(DR2.AreaAffected) AS 'AreaAffected', SUM(DR2.AreaDamaged) AS 'AreaDamaged', COUNT(F.FarmID) AS 'FarmsAffected'\n"
                    + "FROM DamageReport DR2 JOIN DamageIncident DI ON DI.DamageIncidentID = DR2.DamageIncidentID\n"
                    + "                      JOIN PlantingReport PR ON PR.PlantingReportID = DI.PlantingReportID\n"
                    + "                      JOIN Plot P ON PR.PlotID = P.PlotID\n"
                    + "                      JOIN Farm F ON F.FarmID = P.FarmID\n"
                    + "                      JOIN Barangay B ON B.BarangayID = F.BarangayID\n"
                    + "                      JOIN Municipality M ON M.MunicipalityID = B.MunicipalityID\n"
                    + "                      JOIN Problem PM ON PM.ProblemID = DI.ProblemReported\n"
                    + "                      JOIN (SELECT DR1.DamageIncidentID, MAX(STR_TO_DATE(DR1.DateReported, '%m-%d-%Y')) AS 'RecentDate'\n"
                    + "                            FROM DamageReport DR1\n"
                    + "                            GROUP BY DR1.DamageIncidentID) T1 ON DR2.DamageIncidentID = T1.DamageIncidentID\n"
                    + "WHERE STR_TO_DATE(DR2.DateReported, '%m-%d-%Y') = T1.RecentDate AND DI.IncidentStatus = 'Approved' AND M.MunicipalityID = ?\n"
                    + "GROUP BY B.BarangayName, DI.ProblemReported");
            ps.setInt(1, municipalityID);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DamageIncident damage = new DamageIncident();
                damage.setApprovedBy(rs.getInt(DamageIncident.COLUMN_APPROVEDBY));
                damage.setCropStage(rs.getString(DamageIncident.COLUMN_CROPSTAGE));
                damage.setDamageIncidentID(rs.getInt(DamageIncident.COLUMN_DAMAGEINCIDENTID));
                damage.setDamagedDay(rs.getInt(DamageIncident.COLUMN_DAMAGEDDAY));
                damage.setDamagedMonth(rs.getInt(DamageIncident.COLUMN_DAMAGEDMONTH));
                damage.setDamagedYear(rs.getInt(DamageIncident.COLUMN_DAMAGEDYEAR));
                damage.setEscalationLevel(rs.getString(DamageIncident.COLUMN_ESCALATIONLEVEL));
                damage.setPlantingReportID(rs.getInt(DamageIncident.COLUMN_PLANTINGREPORTID));
                damage.setProblemReported(rs.getInt(DamageIncident.COLUMN_PROBLEMREPORTED));
                damage.setReportedBy(rs.getInt(DamageIncident.COLUMN_REPORTEDBY));
                damage.setDeployedID(rs.getInt(DamageIncident.COLUMN_DEPLOYEDID));
                damage.setStatus(rs.getString(DamageIncident.COLUMN_STATUS));
                damage.setFarmsAffected(rs.getInt("FarmsAffected"));
                damage.setTotalAreaDamaged(rs.getDouble("AreaDamaged"));
                damage.setTotalAreaAffected(rs.getDouble("AreaAffected"));
                damage.setProblemName(rs.getString("ProblemName"));
                damage.setBarangayName(rs.getString("BarangayName"));
                damages.add(damage);
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(DamageIncidentDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return damages;
    }

    public ArrayList<DamageIncident> getBarangayProblemListWithApprovedDamagesInMunicipalityWithProblem(int municipalityID, int problemID) {
        ArrayList<DamageIncident> damages = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT DI.*, PM.ProblemName, B.BarangayName, SUM(DR2.AreaAffected) AS 'AreaAffected', SUM(DR2.AreaDamaged) AS 'AreaDamaged', COUNT(F.FarmID) AS 'FarmsAffected'\n"
                    + "FROM DamageReport DR2 JOIN DamageIncident DI ON DI.DamageIncidentID = DR2.DamageIncidentID\n"
                    + "                      JOIN PlantingReport PR ON PR.PlantingReportID = DI.PlantingReportID\n"
                    + "                      JOIN Plot P ON PR.PlotID = P.PlotID\n"
                    + "                      JOIN Farm F ON F.FarmID = P.FarmID\n"
                    + "                      JOIN Barangay B ON B.BarangayID = F.BarangayID\n"
                    + "                      JOIN Municipality M ON M.MunicipalityID = B.MunicipalityID\n"
                    + "                      JOIN Problem PM ON PM.ProblemID = DI.ProblemReported\n"
                    + "                      JOIN (SELECT DR1.DamageIncidentID, MAX(STR_TO_DATE(DR1.DateReported, '%m-%d-%Y')) AS 'RecentDate'\n"
                    + "                            FROM DamageReport DR1\n"
                    + "                            GROUP BY DR1.DamageIncidentID) T1 ON DR2.DamageIncidentID = T1.DamageIncidentID\n"
                    + "WHERE STR_TO_DATE(DR2.DateReported, '%m-%d-%Y') = T1.RecentDate AND DI.IncidentStatus = 'Approved' AND M.MunicipalityID = ?\n"
                    + "GROUP BY B.BarangayName, DI.ProblemReported\n"
                    + "HAVING DI.ProblemReported = ?");
            ps.setInt(1, municipalityID);
            ps.setInt(2, problemID);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DamageIncident damage = new DamageIncident();
                damage.setApprovedBy(rs.getInt(DamageIncident.COLUMN_APPROVEDBY));
                damage.setCropStage(rs.getString(DamageIncident.COLUMN_CROPSTAGE));
                damage.setDamageIncidentID(rs.getInt(DamageIncident.COLUMN_DAMAGEINCIDENTID));
                damage.setDamagedDay(rs.getInt(DamageIncident.COLUMN_DAMAGEDDAY));
                damage.setDamagedMonth(rs.getInt(DamageIncident.COLUMN_DAMAGEDMONTH));
                damage.setDamagedYear(rs.getInt(DamageIncident.COLUMN_DAMAGEDYEAR));
                damage.setEscalationLevel(rs.getString(DamageIncident.COLUMN_ESCALATIONLEVEL));
                damage.setPlantingReportID(rs.getInt(DamageIncident.COLUMN_PLANTINGREPORTID));
                damage.setProblemReported(rs.getInt(DamageIncident.COLUMN_PROBLEMREPORTED));
                damage.setReportedBy(rs.getInt(DamageIncident.COLUMN_REPORTEDBY));
                damage.setDeployedID(rs.getInt(DamageIncident.COLUMN_DEPLOYEDID));
                damage.setStatus(rs.getString(DamageIncident.COLUMN_STATUS));
                damage.setFarmsAffected(rs.getInt("FarmsAffected"));
                damage.setTotalAreaDamaged(rs.getDouble("AreaDamaged"));
                damage.setTotalAreaAffected(rs.getDouble("AreaAffected"));
                damage.setProblemName(rs.getString("ProblemName"));
                damage.setBarangayName(rs.getString("BarangayName"));
                damages.add(damage);
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(DamageIncidentDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return damages;
    }

    public ArrayList<DamageIncident> getApprovedDamageListForProvincial() {
        ArrayList<DamageIncident> damages = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT DI.*, PM.ProblemName, M.MunicipalityName, SUM(DR2.AreaAffected) AS 'AreaAffected', SUM(DR2.AreaDamaged) AS 'AreaDamaged', COUNT(F.FarmID) AS 'FarmsAffected'\n"
                    + "FROM DamageReport DR2 JOIN DamageIncident DI ON DI.DamageIncidentID = DR2.DamageIncidentID\n"
                    + "                      JOIN PlantingReport PR ON PR.PlantingReportID = DI.PlantingReportID\n"
                    + "                      JOIN Plot P ON PR.PlotID = P.PlotID\n"
                    + "                      JOIN Farm F ON F.FarmID = P.FarmID\n"
                    + "                      JOIN Barangay B ON B.BarangayID = F.BarangayID\n"
                    + "                      JOIN Municipality M ON M.MunicipalityID = B.MunicipalityID\n"
                    + "                      JOIN Problem PM ON PM.ProblemID = DI.ProblemReported\n"
                    + "                      JOIN (SELECT DR1.DamageIncidentID, MAX(STR_TO_DATE(DR1.DateReported, '%m-%d-%Y')) AS 'RecentDate'\n"
                    + "                            FROM DamageReport DR1\n"
                    + "                            GROUP BY DR1.DamageIncidentID) T1 ON DR2.DamageIncidentID = T1.DamageIncidentID\n"
                    + "WHERE STR_TO_DATE(DR2.DateReported, '%m-%d-%Y') = T1.RecentDate AND DI.IncidentStatus = 'Approved'\n"
                    + "GROUP BY M.MunicipalityName, DI.ProblemReported");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DamageIncident damage = new DamageIncident();
                damage.setApprovedBy(rs.getInt(DamageIncident.COLUMN_APPROVEDBY));
                damage.setCropStage(rs.getString(DamageIncident.COLUMN_CROPSTAGE));
                damage.setDamageIncidentID(rs.getInt(DamageIncident.COLUMN_DAMAGEINCIDENTID));
                damage.setDamagedDay(rs.getInt(DamageIncident.COLUMN_DAMAGEDDAY));
                damage.setDamagedMonth(rs.getInt(DamageIncident.COLUMN_DAMAGEDMONTH));
                damage.setDamagedYear(rs.getInt(DamageIncident.COLUMN_DAMAGEDYEAR));
                damage.setEscalationLevel(rs.getString(DamageIncident.COLUMN_ESCALATIONLEVEL));
                damage.setPlantingReportID(rs.getInt(DamageIncident.COLUMN_PLANTINGREPORTID));
                damage.setProblemReported(rs.getInt(DamageIncident.COLUMN_PROBLEMREPORTED));
                damage.setReportedBy(rs.getInt(DamageIncident.COLUMN_REPORTEDBY));
                damage.setDeployedID(rs.getInt(DamageIncident.COLUMN_DEPLOYEDID));
                damage.setStatus(rs.getString(DamageIncident.COLUMN_STATUS));
                damage.setFarmsAffected(rs.getInt("FarmsAffected"));
                damage.setTotalAreaDamaged(rs.getDouble("AreaDamaged"));
                damage.setTotalAreaAffected(rs.getDouble("AreaAffected"));
                damage.setProblemName(rs.getString("ProblemName"));
                damage.setMunicipalityName(rs.getString("MunicipalityName"));
                damages.add(damage);
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(DamageIncidentDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return damages;
    }

    public ArrayList<DamageIncident> getProgramDamageListForProvincial() {
        ArrayList<DamageIncident> damages = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT DI.*, PM.ProblemName, M.MunicipalityName, SUM(DR2.AreaAffected) AS 'AreaAffected', SUM(DR2.AreaDamaged) AS 'AreaDamaged', COUNT(F.FarmID) AS 'FarmsAffected'\n"
                    + "FROM DamageReport DR2 JOIN DamageIncident DI ON DI.DamageIncidentID = DR2.DamageIncidentID\n"
                    + "                      JOIN PlantingReport PR ON PR.PlantingReportID = DI.PlantingReportID\n"
                    + "                      JOIN Plot P ON PR.PlotID = P.PlotID\n"
                    + "                      JOIN Farm F ON F.FarmID = P.FarmID\n"
                    + "                      JOIN Barangay B ON B.BarangayID = F.BarangayID\n"
                    + "                      JOIN Municipality M ON M.MunicipalityID = B.MunicipalityID\n"
                    + "                      JOIN Problem PM ON PM.ProblemID = DI.ProblemReported\n"
                    + "                      JOIN (SELECT DR1.DamageIncidentID, MAX(STR_TO_DATE(DR1.DateReported, '%m-%d-%Y')) AS 'RecentDate'\n"
                    + "                            FROM DamageReport DR1\n"
                    + "                            GROUP BY DR1.DamageIncidentID) T1 ON DR2.DamageIncidentID = T1.DamageIncidentID\n"
                    + "WHERE STR_TO_DATE(DR2.DateReported, '%m-%d-%Y') = T1.RecentDate AND DI.IncidentStatus = 'Program'\n"
                    + "GROUP BY M.MunicipalityName, DI.ProblemReported");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DamageIncident damage = new DamageIncident();
                damage.setApprovedBy(rs.getInt(DamageIncident.COLUMN_APPROVEDBY));
                damage.setCropStage(rs.getString(DamageIncident.COLUMN_CROPSTAGE));
                damage.setDamageIncidentID(rs.getInt(DamageIncident.COLUMN_DAMAGEINCIDENTID));
                damage.setDamagedDay(rs.getInt(DamageIncident.COLUMN_DAMAGEDDAY));
                damage.setDamagedMonth(rs.getInt(DamageIncident.COLUMN_DAMAGEDMONTH));
                damage.setDamagedYear(rs.getInt(DamageIncident.COLUMN_DAMAGEDYEAR));
                damage.setEscalationLevel(rs.getString(DamageIncident.COLUMN_ESCALATIONLEVEL));
                damage.setPlantingReportID(rs.getInt(DamageIncident.COLUMN_PLANTINGREPORTID));
                damage.setProblemReported(rs.getInt(DamageIncident.COLUMN_PROBLEMREPORTED));
                damage.setReportedBy(rs.getInt(DamageIncident.COLUMN_REPORTEDBY));
                damage.setDeployedID(rs.getInt(DamageIncident.COLUMN_DEPLOYEDID));
                damage.setStatus(rs.getString(DamageIncident.COLUMN_STATUS));
                damage.setFarmsAffected(rs.getInt("FarmsAffected"));
                damage.setTotalAreaDamaged(rs.getDouble("AreaDamaged"));
                damage.setTotalAreaAffected(rs.getDouble("AreaAffected"));
                damage.setProblemName(rs.getString("ProblemName"));
                damage.setMunicipalityName(rs.getString("MunicipalityName"));
                damages.add(damage);
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(DamageIncidentDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return damages;
    }

    public ArrayList<DamageIncident> getBarangayProblemListWithProgramDamagesInMunicipality(int municipalityID) {
        ArrayList<DamageIncident> damages = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT DI.*, PM.ProblemName, B.BarangayName, SUM(DR2.AreaAffected) AS 'AreaAffected', SUM(DR2.AreaDamaged) AS 'AreaDamaged', COUNT(F.FarmID) AS 'FarmsAffected'\n"
                    + "FROM DamageReport DR2 JOIN DamageIncident DI ON DI.DamageIncidentID = DR2.DamageIncidentID\n"
                    + "                      JOIN PlantingReport PR ON PR.PlantingReportID = DI.PlantingReportID\n"
                    + "                      JOIN Plot P ON PR.PlotID = P.PlotID\n"
                    + "                      JOIN Farm F ON F.FarmID = P.FarmID\n"
                    + "                      JOIN Barangay B ON B.BarangayID = F.BarangayID\n"
                    + "                      JOIN Municipality M ON M.MunicipalityID = B.MunicipalityID\n"
                    + "                      JOIN Problem PM ON PM.ProblemID = DI.ProblemReported\n"
                    + "                      JOIN (SELECT DR1.DamageIncidentID, MAX(STR_TO_DATE(DR1.DateReported, '%m-%d-%Y')) AS 'RecentDate'\n"
                    + "                            FROM DamageReport DR1\n"
                    + "                            GROUP BY DR1.DamageIncidentID) T1 ON DR2.DamageIncidentID = T1.DamageIncidentID\n"
                    + "WHERE STR_TO_DATE(DR2.DateReported, '%m-%d-%Y') = T1.RecentDate AND DI.IncidentStatus = 'Program' AND M.MunicipalityID = ?\n"
                    + "GROUP BY B.BarangayName, DI.ProblemReported, DI.DeployedID");
            ps.setInt(1, municipalityID);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DamageIncident damage = new DamageIncident();
                damage.setApprovedBy(rs.getInt(DamageIncident.COLUMN_APPROVEDBY));
                damage.setCropStage(rs.getString(DamageIncident.COLUMN_CROPSTAGE));
                damage.setDamageIncidentID(rs.getInt(DamageIncident.COLUMN_DAMAGEINCIDENTID));
                damage.setDamagedDay(rs.getInt(DamageIncident.COLUMN_DAMAGEDDAY));
                damage.setDamagedMonth(rs.getInt(DamageIncident.COLUMN_DAMAGEDMONTH));
                damage.setDamagedYear(rs.getInt(DamageIncident.COLUMN_DAMAGEDYEAR));
                damage.setEscalationLevel(rs.getString(DamageIncident.COLUMN_ESCALATIONLEVEL));
                damage.setPlantingReportID(rs.getInt(DamageIncident.COLUMN_PLANTINGREPORTID));
                damage.setProblemReported(rs.getInt(DamageIncident.COLUMN_PROBLEMREPORTED));
                damage.setReportedBy(rs.getInt(DamageIncident.COLUMN_REPORTEDBY));
                damage.setDeployedID(rs.getInt(DamageIncident.COLUMN_DEPLOYEDID));
                damage.setStatus(rs.getString(DamageIncident.COLUMN_STATUS));
                damage.setFarmsAffected(rs.getInt("FarmsAffected"));
                damage.setTotalAreaDamaged(rs.getDouble("AreaDamaged"));
                damage.setTotalAreaAffected(rs.getDouble("AreaAffected"));
                damage.setProblemName(rs.getString("ProblemName"));
                damage.setBarangayName(rs.getString("BarangayName"));
                damages.add(damage);
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(DamageIncidentDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return damages;
    }

    public ArrayList<DamageIncident> getBarangayProblemListWithProgramDamagesInMunicipalityWithProblem(int municipalityID, int problemID) {
        ArrayList<DamageIncident> damages = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT DI.*, PM.ProblemName, B.BarangayName, SUM(DR2.AreaAffected) AS 'AreaAffected', SUM(DR2.AreaDamaged) AS 'AreaDamaged', COUNT(F.FarmID) AS 'FarmsAffected'\n"
                    + "FROM DamageReport DR2 JOIN DamageIncident DI ON DI.DamageIncidentID = DR2.DamageIncidentID\n"
                    + "                      JOIN PlantingReport PR ON PR.PlantingReportID = DI.PlantingReportID\n"
                    + "                      JOIN Plot P ON PR.PlotID = P.PlotID\n"
                    + "                      JOIN Farm F ON F.FarmID = P.FarmID\n"
                    + "                      JOIN Barangay B ON B.BarangayID = F.BarangayID\n"
                    + "                      JOIN Municipality M ON M.MunicipalityID = B.MunicipalityID\n"
                    + "                      JOIN Problem PM ON PM.ProblemID = DI.ProblemReported\n"
                    + "                      JOIN (SELECT DR1.DamageIncidentID, MAX(STR_TO_DATE(DR1.DateReported, '%m-%d-%Y')) AS 'RecentDate'\n"
                    + "                            FROM DamageReport DR1\n"
                    + "                            GROUP BY DR1.DamageIncidentID) T1 ON DR2.DamageIncidentID = T1.DamageIncidentID\n"
                    + "WHERE STR_TO_DATE(DR2.DateReported, '%m-%d-%Y') = T1.RecentDate AND DI.IncidentStatus = 'Program' AND M.MunicipalityID = ?\n"
                    + "GROUP BY B.BarangayName, DI.ProblemReported\n"
                    + "HAVING DI.ProblemReported = ?");
            ps.setInt(1, municipalityID);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DamageIncident damage = new DamageIncident();
                damage.setApprovedBy(rs.getInt(DamageIncident.COLUMN_APPROVEDBY));
                damage.setCropStage(rs.getString(DamageIncident.COLUMN_CROPSTAGE));
                damage.setDamageIncidentID(rs.getInt(DamageIncident.COLUMN_DAMAGEINCIDENTID));
                damage.setDamagedDay(rs.getInt(DamageIncident.COLUMN_DAMAGEDDAY));
                damage.setDamagedMonth(rs.getInt(DamageIncident.COLUMN_DAMAGEDMONTH));
                damage.setDamagedYear(rs.getInt(DamageIncident.COLUMN_DAMAGEDYEAR));
                damage.setEscalationLevel(rs.getString(DamageIncident.COLUMN_ESCALATIONLEVEL));
                damage.setPlantingReportID(rs.getInt(DamageIncident.COLUMN_PLANTINGREPORTID));
                damage.setProblemReported(rs.getInt(DamageIncident.COLUMN_PROBLEMREPORTED));
                damage.setReportedBy(rs.getInt(DamageIncident.COLUMN_REPORTEDBY));
                damage.setDeployedID(rs.getInt(DamageIncident.COLUMN_DEPLOYEDID));
                damage.setStatus(rs.getString(DamageIncident.COLUMN_STATUS));
                damage.setFarmsAffected(rs.getInt("FarmsAffected"));
                damage.setTotalAreaDamaged(rs.getDouble("AreaDamaged"));
                damage.setTotalAreaAffected(rs.getDouble("AreaAffected"));
                damage.setProblemName(rs.getString("ProblemName"));
                damage.setBarangayName(rs.getString("BarangayName"));
                damages.add(damage);
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(DamageIncidentDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return damages;
    }

    public ArrayList<DamageIncident> getFarmListWithApprovedProblemInBarangay(int problemID, String barangay) {
        ArrayList<DamageIncident> damages = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT DI.*, F.FarmName, SUM(DR2.AreaAffected) AS 'AreaAffected', SUM(DR2.AreaDamaged) AS 'AreaDamaged', PR.SeedVariety, PF.FertilizerName\n"
                    + "FROM DamageReport DR2 JOIN DamageIncident DI ON DI.DamageIncidentID = DR2.DamageIncidentID\n"
                    + "                      JOIN PlantingReport PR ON PR.PlantingReportID = DI.PlantingReportID\n"
                    + "                      JOIN Plot P ON PR.PlotID = P.PlotID\n"
                    + "                      JOIN Farm F ON F.FarmID = P.FarmID\n"
                    + "                      JOIN PlotFertilizer PF ON P.PlotID = PF.PlotID\n"
                    + "                      JOIN Barangay B ON B.BarangayID = F.BarangayID\n"
                    + "                      JOIN (SELECT DR1.DamageIncidentID, MAX(STR_TO_DATE(DR1.DateReported, '%m-%d-%Y')) AS 'RecentDate'\n"
                    + "                            FROM DamageReport DR1\n"
                    + "                            GROUP BY DR1.DamageIncidentID) T1 ON DR2.DamageIncidentID = T1.DamageIncidentID\n"
                    + "WHERE STR_TO_DATE(DR2.DateReported, '%m-%d-%Y') = T1.RecentDate AND DI.IncidentStatus = 'Approved' AND B.BarangayName = ?\n"
                    + "GROUP BY F.FarmID, DI.ProblemReported\n"
                    + "HAVING DI.ProblemReported = ?");
            ps.setString(1, barangay);
            ps.setInt(2, problemID);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DamageIncident damage = new DamageIncident();
                damage.setApprovedBy(rs.getInt(DamageIncident.COLUMN_APPROVEDBY));
                damage.setCropStage(rs.getString(DamageIncident.COLUMN_CROPSTAGE));
                damage.setDamageIncidentID(rs.getInt(DamageIncident.COLUMN_DAMAGEINCIDENTID));
                damage.setDamagedDay(rs.getInt(DamageIncident.COLUMN_DAMAGEDDAY));
                damage.setDamagedMonth(rs.getInt(DamageIncident.COLUMN_DAMAGEDMONTH));
                damage.setDamagedYear(rs.getInt(DamageIncident.COLUMN_DAMAGEDYEAR));
                damage.setEscalationLevel(rs.getString(DamageIncident.COLUMN_ESCALATIONLEVEL));
                damage.setPlantingReportID(rs.getInt(DamageIncident.COLUMN_PLANTINGREPORTID));
                damage.setProblemReported(rs.getInt(DamageIncident.COLUMN_PROBLEMREPORTED));
                damage.setReportedBy(rs.getInt(DamageIncident.COLUMN_REPORTEDBY));
                damage.setDeployedID(rs.getInt(DamageIncident.COLUMN_DEPLOYEDID));
                damage.setStatus(rs.getString(DamageIncident.COLUMN_STATUS));
                damage.setTotalAreaDamaged(rs.getDouble("AreaDamaged"));
                damage.setTotalAreaAffected(rs.getDouble("AreaAffected"));
                damage.setFarmName(rs.getString("FarmName"));
                damage.setSeedVarietyName(rs.getString("SeedVariety"));
                damage.setFertilizerName(rs.getString("FertilizerName"));
                damages.add(damage);
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(DamageIncidentDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return damages;
    }

    public ArrayList<DamageIncident> getFarmListWithProgramProblemInBarangay(int problemID, String barangay) {
        ArrayList<DamageIncident> damages = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT DI.*, F.FarmName, SUM(DR2.AreaAffected) AS 'AreaAffected', SUM(DR2.AreaDamaged) AS 'AreaDamaged'\n"
                    + "FROM DamageReport DR2 JOIN DamageIncident DI ON DI.DamageIncidentID = DR2.DamageIncidentID\n"
                    + "                      JOIN PlantingReport PR ON PR.PlantingReportID = DI.PlantingReportID\n"
                    + "                      JOIN Plot P ON PR.PlotID = P.PlotID\n"
                    + "                      JOIN Farm F ON F.FarmID = P.FarmID\n"
                    + "                      JOIN Barangay B ON B.BarangayID = F.BarangayID\n"
                    + "                      JOIN (SELECT DR1.DamageIncidentID, MAX(STR_TO_DATE(DR1.DateReported, '%m-%d-%Y')) AS 'RecentDate'\n"
                    + "                            FROM DamageReport DR1\n"
                    + "                            GROUP BY DR1.DamageIncidentID) T1 ON DR2.DamageIncidentID = T1.DamageIncidentID\n"
                    + "WHERE STR_TO_DATE(DR2.DateReported, '%m-%d-%Y') = T1.RecentDate AND DI.IncidentStatus = 'Program' AND B.BarangayName = ?\n"
                    + "GROUP BY F.FarmID, DI.ProblemReported\n"
                    + "HAVING DI.ProblemReported = ?");
            ps.setString(1, barangay);
            ps.setInt(2, problemID);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DamageIncident damage = new DamageIncident();
                damage.setApprovedBy(rs.getInt(DamageIncident.COLUMN_APPROVEDBY));
                damage.setCropStage(rs.getString(DamageIncident.COLUMN_CROPSTAGE));
                damage.setDamageIncidentID(rs.getInt(DamageIncident.COLUMN_DAMAGEINCIDENTID));
                damage.setDamagedDay(rs.getInt(DamageIncident.COLUMN_DAMAGEDDAY));
                damage.setDamagedMonth(rs.getInt(DamageIncident.COLUMN_DAMAGEDMONTH));
                damage.setDamagedYear(rs.getInt(DamageIncident.COLUMN_DAMAGEDYEAR));
                damage.setEscalationLevel(rs.getString(DamageIncident.COLUMN_ESCALATIONLEVEL));
                damage.setPlantingReportID(rs.getInt(DamageIncident.COLUMN_PLANTINGREPORTID));
                damage.setProblemReported(rs.getInt(DamageIncident.COLUMN_PROBLEMREPORTED));
                damage.setReportedBy(rs.getInt(DamageIncident.COLUMN_REPORTEDBY));
                damage.setDeployedID(rs.getInt(DamageIncident.COLUMN_DEPLOYEDID));
                damage.setStatus(rs.getString(DamageIncident.COLUMN_STATUS));
                damage.setTotalAreaDamaged(rs.getDouble("AreaDamaged"));
                damage.setTotalAreaAffected(rs.getDouble("AreaAffected"));
                damage.setFarmName(rs.getString("FarmName"));
                damages.add(damage);
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(DamageIncidentDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return damages;
    }

     public ArrayList<DamageIncident> getFarmListWithDeployedID(int incidentID) {
        ArrayList<DamageIncident> damages = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT DI.*, F.FarmName, SUM(DR2.AreaAffected) AS 'AreaAffected', SUM(DR2.AreaDamaged) AS 'AreaDamaged'\n"
                    + "FROM DamageReport DR2 JOIN DamageIncident DI ON DI.DamageIncidentID = DR2.DamageIncidentID\n"
                    + "                      JOIN PlantingReport PR ON PR.PlantingReportID = DI.PlantingReportID\n"
                    + "                      JOIN Plot P ON PR.PlotID = P.PlotID\n"
                    + "                      JOIN Farm F ON F.FarmID = P.FarmID\n"
                    + "                      JOIN Barangay B ON B.BarangayID = F.BarangayID\n"
                    + "                      JOIN (SELECT DR1.DamageIncidentID, MAX(STR_TO_DATE(DR1.DateReported, '%m-%d-%Y')) AS 'RecentDate'\n"
                    + "                            FROM DamageReport DR1\n"
                    + "                            GROUP BY DR1.DamageIncidentID) T1 ON DR2.DamageIncidentID = T1.DamageIncidentID\n"
                    + "WHERE STR_TO_DATE(DR2.DateReported, '%m-%d-%Y') = T1.RecentDate AND (DI.IncidentStatus = 'Program' OR DI.IncidentStatus = 'Solved') AND DI.DeployedID = ?");
            ps.setInt(1, incidentID);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DamageIncident damage = new DamageIncident();
                damage.setApprovedBy(rs.getInt(DamageIncident.COLUMN_APPROVEDBY));
                damage.setCropStage(rs.getString(DamageIncident.COLUMN_CROPSTAGE));
                damage.setDamageIncidentID(rs.getInt(DamageIncident.COLUMN_DAMAGEINCIDENTID));
                damage.setDamagedDay(rs.getInt(DamageIncident.COLUMN_DAMAGEDDAY));
                damage.setDamagedMonth(rs.getInt(DamageIncident.COLUMN_DAMAGEDMONTH));
                damage.setDamagedYear(rs.getInt(DamageIncident.COLUMN_DAMAGEDYEAR));
                damage.setEscalationLevel(rs.getString(DamageIncident.COLUMN_ESCALATIONLEVEL));
                damage.setPlantingReportID(rs.getInt(DamageIncident.COLUMN_PLANTINGREPORTID));
                damage.setProblemReported(rs.getInt(DamageIncident.COLUMN_PROBLEMREPORTED));
                damage.setReportedBy(rs.getInt(DamageIncident.COLUMN_REPORTEDBY));
                damage.setDeployedID(rs.getInt(DamageIncident.COLUMN_DEPLOYEDID));
                damage.setStatus(rs.getString(DamageIncident.COLUMN_STATUS));
                damage.setTotalAreaDamaged(rs.getDouble("AreaDamaged"));
                damage.setTotalAreaAffected(rs.getDouble("AreaAffected"));
                damage.setFarmName(rs.getString("FarmName"));
                damages.add(damage);
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(DamageIncidentDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return damages;
    }
    
    public DamageIncident getApprovedIncidentWithProblemIDAndBarangayName(int municipalID, int problemID, String barangay) {
        ArrayList<DamageIncident> damages = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT DI.*, PM.*, B.BarangayName, SUM(DR2.AreaAffected) AS 'AreaAffected', SUM(DR2.AreaDamaged) AS 'AreaDamaged', COUNT(F.FarmID) AS 'FarmsAffected'\n"
                    + "FROM DamageReport DR2 JOIN DamageIncident DI ON DI.DamageIncidentID = DR2.DamageIncidentID\n"
                    + "                      JOIN PlantingReport PR ON PR.PlantingReportID = DI.PlantingReportID\n"
                    + "                      JOIN Plot P ON PR.PlotID = P.PlotID\n"
                    + "                      JOIN Farm F ON F.FarmID = P.FarmID\n"
                    + "                      JOIN Barangay B ON B.BarangayID = F.BarangayID\n"
                    + "                      JOIN Municipality M ON M.MunicipalityID = B.MunicipalityID\n"
                    + "                      JOIN Problem PM ON PM.ProblemID = DI.ProblemReported\n"
                    + "                      JOIN (SELECT DR1.DamageIncidentID, MAX(DR1.DateReported) AS 'RecentDate'\n"
                    + "                            FROM DamageReport DR1\n"
                    + "                            GROUP BY DR1.DamageIncidentID) T1 ON DR2.DamageIncidentID = T1.DamageIncidentID\n"
                    + "WHERE DR2.DateReported = T1.RecentDate AND DI.IncidentStatus = 'Approved' AND M.MunicipalityID = ?\n"
                    + "GROUP BY B.BarangayName, DI.ProblemReported\n"
                    + "HAVING B.BarangayName = ? AND DI.ProblemReported = ?");
            ps.setInt(1, municipalID);
            ps.setString(2, barangay);
            ps.setInt(3, problemID);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DamageIncident damage = new DamageIncident();
                damage.setApprovedBy(rs.getInt(DamageIncident.COLUMN_APPROVEDBY));
                damage.setCropStage(rs.getString(DamageIncident.COLUMN_CROPSTAGE));
                damage.setDamageIncidentID(rs.getInt(DamageIncident.COLUMN_DAMAGEINCIDENTID));
                damage.setDamagedDay(rs.getInt(DamageIncident.COLUMN_DAMAGEDDAY));
                damage.setDamagedMonth(rs.getInt(DamageIncident.COLUMN_DAMAGEDMONTH));
                damage.setDamagedYear(rs.getInt(DamageIncident.COLUMN_DAMAGEDYEAR));
                damage.setEscalationLevel(rs.getString(DamageIncident.COLUMN_ESCALATIONLEVEL));
                damage.setPlantingReportID(rs.getInt(DamageIncident.COLUMN_PLANTINGREPORTID));
                damage.setProblemReported(rs.getInt(DamageIncident.COLUMN_PROBLEMREPORTED));
                damage.setReportedBy(rs.getInt(DamageIncident.COLUMN_REPORTEDBY));
                damage.setDeployedID(rs.getInt(DamageIncident.COLUMN_DEPLOYEDID));
                damage.setStatus(rs.getString(DamageIncident.COLUMN_STATUS));
                damage.setFarmsAffected(rs.getInt("FarmsAffected"));
                damage.setProblemName(rs.getString("ProblemName"));
                damage.setProblemDescription(rs.getString("Description"));
                damage.setTotalAreaDamaged(rs.getDouble("AreaDamaged"));
                damage.setTotalAreaAffected(rs.getDouble("AreaAffected"));
                damages.add(damage);
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(DamageIncidentDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return damages.get(0);
    }

    public DamageIncident getIncidentDetailForFullInfoViewing(int incidentID) {
        ArrayList<DamageIncident> damages = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT DI.*, F.FarmName, DR2.AreaAffected, DR2.AreaDamaged, PR.SeedVariety, PR.AmountPlanted, PM.ProblemName\n"
                    + "FROM DamageReport DR2 JOIN DamageIncident DI ON DI.DamageIncidentID = DR2.DamageIncidentID\n"
                    + "                      JOIN PlantingReport PR ON PR.PlantingReportID = DI.PlantingReportID\n"
                    + "                      JOIN Plot P ON PR.PlotID = P.PlotID\n"
                    + "                      JOIN Farm F ON F.FarmID = P.FarmID\n"
                    + "                      JOIN Problem PM ON PM.ProblemID = DI.ProblemReported\n"
                    + "                      JOIN (SELECT DR1.DamageIncidentID, MAX(STR_TO_DATE(DR1.DateReported, '%m-%d-%Y')) AS 'RecentDate'\n"
                    + "                            FROM DamageReport DR1\n"
                    + "                            GROUP BY DR1.DamageIncidentID) T1 ON DR2.DamageIncidentID = T1.DamageIncidentID\n"
                    + "WHERE STR_TO_DATE(DR2.DateReported, '%m-%d-%Y') = T1.RecentDate AND DI.DamageIncidentID = ?");
            ps.setInt(1, incidentID);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DamageIncident damage = new DamageIncident();
                damage.setApprovedBy(rs.getInt(DamageIncident.COLUMN_APPROVEDBY));
                damage.setCropStage(rs.getString(DamageIncident.COLUMN_CROPSTAGE));
                damage.setDamageIncidentID(rs.getInt(DamageIncident.COLUMN_DAMAGEINCIDENTID));
                damage.setDamagedDay(rs.getInt(DamageIncident.COLUMN_DAMAGEDDAY));
                damage.setDamagedMonth(rs.getInt(DamageIncident.COLUMN_DAMAGEDMONTH));
                damage.setDamagedYear(rs.getInt(DamageIncident.COLUMN_DAMAGEDYEAR));
                damage.setEscalationLevel(rs.getString(DamageIncident.COLUMN_ESCALATIONLEVEL));
                damage.setPlantingReportID(rs.getInt(DamageIncident.COLUMN_PLANTINGREPORTID));
                damage.setProblemReported(rs.getInt(DamageIncident.COLUMN_PROBLEMREPORTED));
                damage.setReportedBy(rs.getInt(DamageIncident.COLUMN_REPORTEDBY));
                damage.setDeployedID(rs.getInt(DamageIncident.COLUMN_DEPLOYEDID));
                damage.setStatus(rs.getString(DamageIncident.COLUMN_STATUS));
                damage.setTotalAreaDamaged(rs.getDouble("AreaDamaged"));
                damage.setTotalAreaAffected(rs.getDouble("AreaAffected"));
                damage.setFarmName(rs.getString("FarmName"));
                damage.setSeedVarietyName(rs.getString("SeedVariety"));
                damage.setSeedsPlanted(rs.getDouble("AmountPlanted"));
                damage.setProblemName(rs.getString("ProblemName"));
                damages.add(damage);
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(DamageIncidentDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return damages.get(0);
    }

    public ArrayList<DamageIncident> getListOfIncidentsWithInfoInMunicipal(int municipalityID) {
        ArrayList<DamageIncident> damages = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT DI.*, P.ProblemName, F.FarmName, DR.AreaAffected, DR.AreaDamaged\n"
                    + "FROM DamageIncident DI JOIN DamageReport DR ON DI.DamageIncidentID = DR.DamageIncidentID\n"
                    + "                        JOIN Problem P ON DI.ProblemReported = P.ProblemID\n"
                    + "                        JOIN PlantingReport PR ON PR.PlantingReportID = DI.PlantingReportID\n"
                    + "                        JOIN Plot PL ON PL.PlotID = PR.PlotID\n"
                    + "                        JOIN Farm F ON F.FarmID = PL.FarmID\n"
                    + "                        JOIN Barangay B ON B.BarangayID = F.BarangayID\n"
                    + "                        JOIN (SELECT DR1.DamageIncidentID, MAX(STR_TO_DATE(DR1.DateReported, '%m-%d-%Y')) AS 'DateReported'\n"
                    + "							FROM DamageReport DR1\n"
                    + "                            GROUP BY DR1.DamageIncidentID) T1 ON STR_TO_DATE(DR.DateReported, '%m-%d-%Y') = T1.Datereported AND DR.DamageIncidentID = T1.DamageIncidentID\n"
                    + "WHERE B.MunicipalityID = ?");
            ps.setInt(1, municipalityID);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DamageIncident damage = new DamageIncident();
                damage.setApprovedBy(rs.getInt(DamageIncident.COLUMN_APPROVEDBY));
                damage.setCropStage(rs.getString(DamageIncident.COLUMN_CROPSTAGE));
                damage.setDamageIncidentID(rs.getInt(DamageIncident.COLUMN_DAMAGEINCIDENTID));
                damage.setDamagedDay(rs.getInt(DamageIncident.COLUMN_DAMAGEDDAY));
                damage.setDamagedMonth(rs.getInt(DamageIncident.COLUMN_DAMAGEDMONTH));
                damage.setDamagedYear(rs.getInt(DamageIncident.COLUMN_DAMAGEDYEAR));
                damage.setEscalationLevel(rs.getString(DamageIncident.COLUMN_ESCALATIONLEVEL));
                damage.setPlantingReportID(rs.getInt(DamageIncident.COLUMN_PLANTINGREPORTID));
                damage.setProblemReported(rs.getInt(DamageIncident.COLUMN_PROBLEMREPORTED));
                damage.setReportedBy(rs.getInt(DamageIncident.COLUMN_REPORTEDBY));
                damage.setDeployedID(rs.getInt(DamageIncident.COLUMN_DEPLOYEDID));
                damage.setStatus(rs.getString(DamageIncident.COLUMN_STATUS));
                damage.setTotalAreaDamaged(rs.getDouble("AreaDamaged"));
                damage.setTotalAreaAffected(rs.getDouble("AreaAffected"));
                damage.setFarmName(rs.getString("FarmName"));
                damage.setProblemName(rs.getString("ProblemName"));
                damages.add(damage);
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(DamageIncidentDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return damages;
    }

    private ArrayList<DamageIncident> getDataFromResultSet(ResultSet rs) throws SQLException {
        ArrayList<DamageIncident> damages = new ArrayList<>();
        while (rs.next()) {
            DamageIncident damage = new DamageIncident();
            damage.setApprovedBy(rs.getInt(DamageIncident.COLUMN_APPROVEDBY));
            damage.setCropStage(rs.getString(DamageIncident.COLUMN_CROPSTAGE));
            damage.setDamageIncidentID(rs.getInt(DamageIncident.COLUMN_DAMAGEINCIDENTID));
            damage.setDamagedDay(rs.getInt(DamageIncident.COLUMN_DAMAGEDDAY));
            damage.setDamagedMonth(rs.getInt(DamageIncident.COLUMN_DAMAGEDMONTH));
            damage.setDamagedYear(rs.getInt(DamageIncident.COLUMN_DAMAGEDYEAR));
            damage.setEscalationLevel(rs.getString(DamageIncident.COLUMN_ESCALATIONLEVEL));
            damage.setPlantingReportID(rs.getInt(DamageIncident.COLUMN_PLANTINGREPORTID));
            damage.setProblemReported(rs.getInt(DamageIncident.COLUMN_PROBLEMREPORTED));
            damage.setReportedBy(rs.getInt(DamageIncident.COLUMN_REPORTEDBY));
            damage.setDeployedID(rs.getInt(DamageIncident.COLUMN_DEPLOYEDID));
            damage.setStatus(rs.getString(DamageIncident.COLUMN_STATUS));
            damage.setRemarks(rs.getString(DamageIncident.COLUMN_REMARKS));
            damages.add(damage);
        }
        return damages;
    }
}
