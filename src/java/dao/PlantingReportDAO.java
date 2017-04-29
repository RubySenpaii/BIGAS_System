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
import object.PlantingReport;
import object.Plot;

/**
 *
 * @author RubySenpaii
 */
public class PlantingReportDAO {

    public boolean addPlantingReport(PlantingReport plantingReport) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("INSERT INTO " + PlantingReport.TABLE_NAME + " "
                    + "(" + PlantingReport.COLUMN_AMOUNTHARVESTED + ", " + PlantingReport.COLUMN_AMOUNTPLANTED + ", " + PlantingReport.COLUMN_HARVESTDAY + ", "
                    + PlantingReport.COLUMN_HARVESTMONTH + ", " + PlantingReport.COLUMN_HARVESTYEAR + ", " + PlantingReport.COLUMN_PLANTEDDAY + ", "
                    + PlantingReport.COLUMN_PLANTEDMONTH + ", " + PlantingReport.COLUMN_PLANTEDYEAR + ", " + PlantingReport.COLUMN_PLANTINGMETHOD + ", "
                    + PlantingReport.COLUMN_PLANTINGREPORTID + ", " + PlantingReport.COLUMN_PLOTID + ", " + PlantingReport.COLUMN_REPORTEDBY + ", "
                    + PlantingReport.COLUMN_SEASON + ", " + PlantingReport.COLUMN_SEEDACQUISITION + ", " + PlantingReport.COLUMN_SEEDTYPE + ", "
                    + PlantingReport.COLUMN_SEEDVARIETY + ") "
                    + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setDouble(1, plantingReport.getAmountHarvested());
            ps.setDouble(2, plantingReport.getAmountPlanted());
            ps.setInt(3, plantingReport.getHarvestDay());
            ps.setInt(4, plantingReport.getHarvestMonth());
            ps.setInt(5, plantingReport.getHarvestYear());
            ps.setInt(6, plantingReport.getPlantedDay());
            ps.setInt(7, plantingReport.getPlantedMonth());
            ps.setInt(8, plantingReport.getPlantedYear());
            ps.setString(9, plantingReport.getPlantingMethod());
            ps.setInt(10, plantingReport.getPlantingReportID());
            ps.setInt(11, plantingReport.getPlotID());
            ps.setInt(12, plantingReport.getReportedBy());
            ps.setString(13, plantingReport.getSeason());
            ps.setString(14, plantingReport.getSeedAcquisition());
            ps.setString(15, plantingReport.getSeedType());
            ps.setString(16, plantingReport.getSeedVariety());

            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(PlantingReportDAO.class.getName()).log(Level.SEVERE, null, x);
            return false;
        }
        return true;
    }

    public ArrayList<PlantingReport> getListOfPlantingReports() {
        ArrayList<PlantingReport> plantingReports = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + PlantingReport.TABLE_NAME);

            ResultSet rs = ps.executeQuery();
            plantingReports = getDataFromResultSet(rs);

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(PlantingReportDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return plantingReports;
    }

    public boolean updatePlantingReport(PlantingReport plantingReport) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("UPDATE " + PlantingReport.TABLE_NAME
                    + " SET " + PlantingReport.COLUMN_AMOUNTHARVESTED + " = ?, " + PlantingReport.COLUMN_AMOUNTPLANTED + " = ?, " + PlantingReport.COLUMN_HARVESTDAY + " = ?, "
                    + PlantingReport.COLUMN_HARVESTMONTH + " = ?, " + PlantingReport.COLUMN_HARVESTYEAR + " = ?, " + PlantingReport.COLUMN_PLANTEDDAY + " = ?, "
                    + PlantingReport.COLUMN_PLANTEDMONTH + " = ?, " + PlantingReport.COLUMN_PLANTEDYEAR + " = ?, " + PlantingReport.COLUMN_PLANTINGMETHOD + " = ?, "
                    + PlantingReport.COLUMN_PLANTINGREPORTID + " = ?, " + PlantingReport.COLUMN_PLOTID + " = ?, " + PlantingReport.COLUMN_REPORTEDBY + " = ?, "
                    + PlantingReport.COLUMN_SEASON + " = ?, " + PlantingReport.COLUMN_SEEDACQUISITION + " = ?, " + PlantingReport.COLUMN_SEEDTYPE + " = ?, "
                    + PlantingReport.COLUMN_SEEDVARIETY + " = ? "
                    + "WHERE " + PlantingReport.COLUMN_PLANTINGREPORTID + " = ?");
            ps.setDouble(1, plantingReport.getAmountHarvested());
            ps.setDouble(2, plantingReport.getAmountPlanted());
            ps.setInt(3, plantingReport.getHarvestDay());
            ps.setInt(4, plantingReport.getHarvestMonth());
            ps.setInt(5, plantingReport.getHarvestYear());
            ps.setInt(6, plantingReport.getPlantedDay());
            ps.setInt(7, plantingReport.getPlantedMonth());
            ps.setInt(8, plantingReport.getPlantedYear());
            ps.setString(9, plantingReport.getPlantingMethod());
            ps.setInt(10, plantingReport.getPlantingReportID());
            ps.setInt(11, plantingReport.getPlotID());
            ps.setInt(12, plantingReport.getReportedBy());
            ps.setString(13, plantingReport.getSeason());
            ps.setString(14, plantingReport.getSeedAcquisition());
            ps.setString(15, plantingReport.getSeedType());
            ps.setString(16, plantingReport.getSeedVariety());
            ps.setInt(17, plantingReport.getPlantingReportID());

            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(PlotDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public ArrayList<PlantingReport> getListOfPlantingReportsInFarm(int farmID) {
        ArrayList<PlantingReport> plantingReports = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT PR.*\n"
                    + "FROM PlantingReport PR JOIN Plot P ON PR.PlotID = P.PlotID\n"
                    + "				JOIN Farm F ON P.FarmID = F.FarmID\n"
                    + "WHERE F.FarmID = ?");
            ps.setInt(1, farmID);

            ResultSet rs = ps.executeQuery();
            plantingReports = getDataFromResultSet(rs);

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(PlantingReportDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return plantingReports;
    }

    public ArrayList<PlantingReport> getListOfPlantingReportsInMunicipality(int municipalityID) {
        ArrayList<PlantingReport> plantingReports = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT PR.*\n"
                    + "FROM PlantingReport PR JOIN Plot P ON PR.PlotID = P.PlotID\n"
                    + "				JOIN Farm F ON P.FarmID = F.FarmID\n"
                    + "				JOIN Barangay B ON B.BarangayID = F.BarangayID\n"
                    + "				JOIN Municipality M ON M.MunicipalityID = B.MunicipalityID\n"
                    + "WHERE M.Municipality = ?");
            ps.setInt(1, municipalityID);

            ResultSet rs = ps.executeQuery();
            plantingReports = getDataFromResultSet(rs);

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(PlantingReportDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return plantingReports;
    }

    public ArrayList<PlantingReport> getListOfPlantingReportsInFarmAndSeasonAndYear(int farmID, String season, int year) {
        ArrayList<PlantingReport> plantingReports = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            String statement = "";
            if (season.equals("Wet")) {
                int nextYear = year + 1;
                statement = "SELECT PR.* "
                        + "  FROM PlantingReport PR JOIN Plot P ON PR.PlotID = P.PlotID"
                        + "                         JOIN Farm F ON P.FarmID = F.FarmID"
                        + "  WHERE STR_TO_DATE(CONCAT(PR.HarvestMonth, '-', PR.HarvestDay, '-', PR.HarvestYear), '%m-%d-%Y') > STR_TO_DATE('03-16-' + year + , '%m-%d-%Y') AND "
                        + "         STR_TO_DATE(CONCAT(PR.HarvestMonth, '-', PR.HarvestDay, '-', PR.HarvestYear), '%m-%d-%Y') > STR_TO_DATE('03-16-'" + nextYear + ", '%m-%d-%Y') AND "
                        + "         F.FarmID = ?";
            } else {
                statement = "SELECT PR.* "
                        + "  FROM PlantingReport PR JOIN Plot P ON PR.PlotID = P.PlotID"
                        + "                         JOIN Farm F ON P.FarmID = F.FarmID"
                        + "  WHERE STR_TO_DATE(CONCAT(PR.HarvestMonth, '-', PR.HarvestDay, '-', PR.HarvestYear), '%m-%d-%Y') > STR_TO_DATE('03-16-2016', '%m-%d-%Y') AND "
                        + "         STR_TO_DATE(CONCAT(PR.HarvestMonth, '-', PR.HarvestDay, '-', PR.HarvestYear), '%m-%d-%Y') > STR_TO_DATE('09-15-2016', '%m-%d-%Y') AND "
                        + "         F.FarmID = ?";
            }

            PreparedStatement ps = conn.prepareStatement(statement);
            ps.setInt(1, farmID);

            ResultSet rs = ps.executeQuery();
            plantingReports = getDataFromResultSet(rs);

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(PlantingReportDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return plantingReports;
    }

    public ArrayList<PlantingReport> getListOfOngoingPlantingReportsInBarangay(int barangayID) {
        ArrayList<PlantingReport> plantingReports = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT PR.*\n"
                    + "FROM PlantingReport PR JOIN Plot P ON PR.PlotID = P.PlotID\n"
                    + "				JOIN Farm F ON P.FarmID = F.FarmID\n"
                    + "                        JOIN Barangay B ON F.BarangayID = B.BarangayID\n"
                    + "WHERE PR.HarvestYear = 9999 AND PR.HarvestDay = 31 AND PR.HarvestMonth = 12 AND B.BarangayID = ?");
            ps.setInt(1, barangayID);

            ResultSet rs = ps.executeQuery();
            plantingReports = getDataFromResultSet(rs);

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(PlantingReportDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return plantingReports;
    }

    public ArrayList<PlantingReport> getListOfOngoingPlantingReportsInBarangayBefore(int barangayID, String date) {
        ArrayList<PlantingReport> plantingReports = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT PR.*, MAX(STR_TO_DATE(CONCAT(PR.HarvestMonth, '-', PR.HarvestDay, '-', PR.HarvestYear), '%m-%d-%Y'))\n"
                    + "FROM PlantingReport PR JOIN Plot P ON PR.PlotID = P.PlotID\n"
                    + "				JOIN Farm F ON P.FarmID = F.FarmID\n"
                    + "                        JOIN Barangay B ON F.BarangayID = B.BarangayID\n"
                    + "WHERE STR_TO_DATE(CONCAT(PR.HarvestMonth, '-', PR.HarvestDay, '-', PR.HarvestYear), '%m-%d-%Y') > STR_TO_DATE(?, '%m-%d-%Y') AND B.BarangayID = ?"
                    + "      AND STR_TO_DATE(CONCAT(PR.PlantedMonth, '-', PR.PlantedDay, '-', PR.PlantedYear), '%m-%d-%Y') <= STR_TO_DATE(?, '%m-%d-%Y')\n"
                    + "GROUP BY PR.PlotID");
            ps.setString(1, date);
            ps.setInt(2, barangayID);
            ps.setString(3, date);

            ResultSet rs = ps.executeQuery();
            plantingReports = getDataFromResultSet(rs);

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(PlantingReportDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return plantingReports;
    }

    public double getTotalHarvestedFromFarmOnYear(int farmID, int year) {
        double plantingTotal = 0;
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT SUM(PR.AmountHarvested) AS 'TotalHarvested' "
                    + "FROM PlantingReport PR JOIN Plot P ON PR.PlotID = P.PlotID "
                    + "                       JOIN Farm F ON P.FarmID = F.FarmID "
                    + "WHERE F.FarmID = ? AND PR.PlantedYear = ?");
            ps.setInt(1, farmID);
            ps.setInt(2, year);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                plantingTotal = rs.getDouble("TotalHarvested");
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(PlantingReportDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return plantingTotal;
    }

    public ArrayList<PlantingReport> getTotalHarvestForMunicipalAndYearBySeasonSeedTypeAndAcquisition(int municipalityID, int year) {
        ArrayList<PlantingReport> plantingReports = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            String startDate = "03-16-" + year;
            String endDate = "03-15-" + (year + 1);
            PreparedStatement ps = conn.prepareStatement("SELECT PR.SeedType, PR.SeedAcquisition, PR.Season, SUM(PR.AmountHarvested) AS 'AmountHarvested'\n"
                    + "FROM PlantingReport PR JOIN Plot P ON PR.PlotID = P.PlotID \n"
                    + "						JOIN Farm F ON P.FarmID = F.FarmID\n"
                    + "                        JOIN Barangay B ON F.BarangayID = B.BarangayID\n"
                    + "                        JOIN Municipality M ON B.MunicipalityID = M.MunicipalityID\n"
                    + "WHERE STR_TO_DATE(CONCAT(PR.HarvestMonth, '-', PR.HarvestDay, '-', PR.HarvestYear), '%m-%d-%Y') > STR_TO_DATE(?, '%m-%d-%Y') \n"
                    + "		AND STR_TO_DATE(CONCAT(PR.HarvestMonth, '-', PR.HarvestDay, '-', PR.HarvestYear), '%m-%d-%Y') < STR_TO_DATE(?, '%m-%d-%Y') \n"
                    + "        AND M.MunicipalityID = ?\n"
                    + "GROUP BY PR.SeedType, PR.Season, PR.SeedAcquisition");
            ps.setString(1, startDate);
            ps.setString(2, endDate);
            ps.setInt(3, municipalityID);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PlantingReport plantingReport = new PlantingReport();
                plantingReport.setAmountHarvested(rs.getDouble(PlantingReport.COLUMN_AMOUNTHARVESTED));
                plantingReport.setSeason(rs.getString(PlantingReport.COLUMN_SEASON));
                plantingReport.setSeedAcquisition(rs.getString(PlantingReport.COLUMN_SEEDACQUISITION));
                plantingReport.setSeedType(rs.getString(PlantingReport.COLUMN_SEEDTYPE));
                plantingReports.add(plantingReport);
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(PlantingReportDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return plantingReports;
    }

    public ArrayList<PlantingReport> getProvincialData(int year, String municipality) {
        ArrayList<PlantingReport> plantingReports = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            String startDate = "03-16-" + year;
            String endDate = "03-15-" + (year + 1);
            PreparedStatement ps = conn.prepareStatement("SELECT PR.SeedType, PR.SeedAcquisition, PR.Season, F.IrrigationMethod, F.LandElevation, F.FarmName, B.BarangayName, M.MunicipalityName, SUM(PR.AmountHarvested) AS 'AmountHarvested', SUM(DR.AreaDamaged) AS 'AreaDamaged', SUM(P.PlotSize) AS 'PlotSize'\n"
                    + "FROM PlantingReport PR JOIN Plot P ON PR.PlotID = P.PlotID \n"
                    + "                        JOIN Farm F ON P.FarmID = F.FarmID\n"
                    + "                        JOIN Barangay B ON F.BarangayID = B.BarangayID\n"
                    + "                        JOIN Municipality M ON B.MunicipalityID = M.MunicipalityID\n"
                    + "                        JOIN DamageIncident DI ON DI.PlantingReportID = PR.PlantingReportID\n"
                    + "                        JOIN DamageReport DR ON DI.DamageIncidentID = DR.DamageIncidentID\n"
                    + "                        JOIN (SELECT DR1.DamageIncidentID, MAX(STR_TO_DATE(DR1.DateReported, '%m-%d-%Y')) AS 'DateReported'\n"
                    + "                             FROM DamageReport DR1\n"
                    + "                            GROUP BY DR1.DamageIncidentID) T1 ON STR_TO_DATE(DR.DateReported, '%m-%d-%Y') = T1.Datereported AND DR.DamageIncidentID = T1.DamageIncidentID\n"
                    + "WHERE STR_TO_DATE(CONCAT(PR.HarvestMonth, '-', PR.HarvestDay, '-', PR.HarvestYear), '%m-%d-%Y') > STR_TO_DATE(?, '%m-%d-%Y') \n"
                    + "		AND STR_TO_DATE(CONCAT(PR.HarvestMonth, '-', PR.HarvestDay, '-', PR.HarvestYear), '%m-%d-%Y') < STR_TO_DATE(?, '%m-%d-%Y') \n"
                    + "GROUP BY PR.SeedType, PR.SeedAcquisition, PR.Season, F.IrrigationMethod, F.LandElevation, F.FarmName, B.BarangayName, M.MunicipalityName \n"
                    + "HAVING M.MunicipalityName = ?");
            ps.setString(1, startDate);
            ps.setString(2, endDate);
            ps.setString(3, municipality);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PlantingReport plantingReport = new PlantingReport();
                plantingReport.setMunicipalityName(rs.getString("MunicipalityName"));
                plantingReport.setBarangayName(rs.getString("BarangayName"));
                plantingReport.setFarmName(rs.getString("FarmName"));
                plantingReport.setAreaDamaged(rs.getDouble("AreaDamaged"));
                plantingReport.setPlantedArea(rs.getDouble("PlotSize"));
                plantingReport.setLandElevation(rs.getString("LandElevation"));
                plantingReport.setIrrigationMethod(rs.getString("IrrigationMethod"));
                plantingReport.setAmountHarvested(rs.getDouble(PlantingReport.COLUMN_AMOUNTHARVESTED));
                plantingReport.setSeason(rs.getString(PlantingReport.COLUMN_SEASON));
                plantingReport.setSeedAcquisition(rs.getString(PlantingReport.COLUMN_SEEDACQUISITION));
                plantingReport.setSeedType(rs.getString(PlantingReport.COLUMN_SEEDTYPE));
                plantingReports.add(plantingReport);
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(PlantingReportDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return plantingReports;
    }
    
    public ArrayList<PlantingReport> getMunicipalReportData(int year, String barangay) {
        ArrayList<PlantingReport> plantingReports = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            String startDate = "03-16-" + year;
            String endDate = "03-15-" + (year + 1);
            PreparedStatement ps = conn.prepareStatement("SELECT PR.SeedType, PR.SeedAcquisition, PR.Season, F.IrrigationMethod, F.LandElevation, F.FarmName, B.BarangayName, M.MunicipalityName, SUM(PR.AmountHarvested) AS 'AmountHarvested', SUM(DR.AreaDamaged) AS 'AreaDamaged', SUM(P.PlotSize) AS 'PlotSize'\n"
                    + "FROM PlantingReport PR JOIN Plot P ON PR.PlotID = P.PlotID \n"
                    + "                        JOIN Farm F ON P.FarmID = F.FarmID\n"
                    + "                        JOIN Barangay B ON F.BarangayID = B.BarangayID\n"
                    + "                        JOIN Municipality M ON B.MunicipalityID = M.MunicipalityID\n"
                    + "                        JOIN DamageIncident DI ON DI.PlantingReportID = PR.PlantingReportID\n"
                    + "                        JOIN DamageReport DR ON DI.DamageIncidentID = DR.DamageIncidentID\n"
                    + "                        JOIN (SELECT DR1.DamageIncidentID, MAX(STR_TO_DATE(DR1.DateReported, '%m-%d-%Y')) AS 'DateReported'\n"
                    + "                             FROM DamageReport DR1\n"
                    + "                            GROUP BY DR1.DamageIncidentID) T1 ON STR_TO_DATE(DR.DateReported, '%m-%d-%Y') = T1.Datereported AND DR.DamageIncidentID = T1.DamageIncidentID\n"
                    + "WHERE STR_TO_DATE(CONCAT(PR.HarvestMonth, '-', PR.HarvestDay, '-', PR.HarvestYear), '%m-%d-%Y') > STR_TO_DATE(?, '%m-%d-%Y') \n"
                    + "		AND STR_TO_DATE(CONCAT(PR.HarvestMonth, '-', PR.HarvestDay, '-', PR.HarvestYear), '%m-%d-%Y') < STR_TO_DATE(?, '%m-%d-%Y') \n"
                    + "GROUP BY PR.SeedType, PR.SeedAcquisition, PR.Season, F.IrrigationMethod, F.LandElevation, F.FarmName, B.BarangayName, M.MunicipalityName \n"
                    + "HAVING B.BarangayName = ?");
            ps.setString(1, startDate);
            ps.setString(2, endDate);
            ps.setString(3, barangay);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PlantingReport plantingReport = new PlantingReport();
                plantingReport.setMunicipalityName(rs.getString("MunicipalityName"));
                plantingReport.setBarangayName(rs.getString("BarangayName"));
                plantingReport.setFarmName(rs.getString("FarmName"));
                plantingReport.setAreaDamaged(rs.getDouble("AreaDamaged"));
                plantingReport.setPlantedArea(rs.getDouble("PlotSize"));
                plantingReport.setLandElevation(rs.getString("LandElevation"));
                plantingReport.setIrrigationMethod(rs.getString("IrrigationMethod"));
                plantingReport.setAmountHarvested(rs.getDouble(PlantingReport.COLUMN_AMOUNTHARVESTED));
                plantingReport.setSeason(rs.getString(PlantingReport.COLUMN_SEASON));
                plantingReport.setSeedAcquisition(rs.getString(PlantingReport.COLUMN_SEEDACQUISITION));
                plantingReport.setSeedType(rs.getString(PlantingReport.COLUMN_SEEDTYPE));
                plantingReports.add(plantingReport);
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(PlantingReportDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return plantingReports;
    }

    public double getTotalPlantedAreaFromFarmOnYear(int farmID, int year) {
        double plantedArea = 0;
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT SUM(P.PlotSize) AS 'TotalPlotSize' "
                    + "FROM PlantingReport PR JOIN Plot P ON PR.PlotID = P.PlotID "
                    + "                       JOIN Farm F ON P.FarmID = F.FarmID "
                    + "WHERE F.FarmID = ? AND PR.PlantedYear = ?");
            ps.setInt(1, farmID);
            ps.setInt(2, year);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                plantedArea = rs.getDouble("TotalPlotSize");
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(PlantingReportDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return plantedArea;
    }

    private ArrayList<PlantingReport> getDataFromResultSet(ResultSet rs) throws SQLException {
        ArrayList<PlantingReport> plantingReports = new ArrayList<>();
        while (rs.next()) {
            PlantingReport plantingReport = new PlantingReport();
            plantingReport.setAmountHarvested(rs.getDouble(PlantingReport.COLUMN_AMOUNTHARVESTED));
            plantingReport.setAmountPlanted(rs.getDouble(PlantingReport.COLUMN_AMOUNTPLANTED));
            plantingReport.setHarvestDay(rs.getInt(PlantingReport.COLUMN_HARVESTDAY));
            plantingReport.setHarvestMonth(rs.getInt(PlantingReport.COLUMN_HARVESTMONTH));
            plantingReport.setHarvestYear(rs.getInt(PlantingReport.COLUMN_HARVESTYEAR));
            plantingReport.setPlantedDay(rs.getInt(PlantingReport.COLUMN_PLANTEDDAY));
            plantingReport.setPlantedMonth(rs.getInt(PlantingReport.COLUMN_PLANTEDMONTH));
            plantingReport.setPlantedYear(rs.getInt(PlantingReport.COLUMN_PLANTEDYEAR));
            plantingReport.setPlantingMethod(rs.getString(PlantingReport.COLUMN_PLANTINGMETHOD));
            plantingReport.setPlantingReportID(rs.getInt(PlantingReport.COLUMN_PLANTINGREPORTID));
            plantingReport.setPlotID(rs.getInt(PlantingReport.COLUMN_PLOTID));
            plantingReport.setReportedBy(rs.getInt(PlantingReport.COLUMN_REPORTEDBY));
            plantingReport.setSeason(rs.getString(PlantingReport.COLUMN_SEASON));
            plantingReport.setSeedAcquisition(rs.getString(PlantingReport.COLUMN_SEEDACQUISITION));
            plantingReport.setSeedType(rs.getString(PlantingReport.COLUMN_SEEDTYPE));
            plantingReport.setSeedVariety(rs.getString(PlantingReport.COLUMN_SEEDVARIETY));
            plantingReports.add(plantingReport);
        }
        return plantingReports;
    }
}
