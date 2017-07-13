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
import object.Farm;
import object.Municipality;
import object.Plot;

/**
 *
 * @author RubySenpaii
 */
public class FarmDAO {

    public boolean addFarm(Farm farm) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("INSERT INTO " + Farm.TABLE_NAME + " "
                    + "(" + Farm.COLUMN_ADDRESS + ", " + Farm.COLUMN_BARANGAYID + ", " + Farm.COLUMN_DATEVISITED + ", " + Farm.COLUMN_FARMABLEAREA + ", "
                    + Farm.COLUMN_FARMID + ", " + Farm.COLUMN_FARMNAME + ", " + Farm.COLUMN_FLAG + ", " + Farm.COLUMN_IRRIGATIONMETHOD + ", "
                    + Farm.COLUMN_LANDELEVATION + ", " + Farm.COLUMN_LATITUDE + ", " + Farm.COLUMN_LONGITUDE + ", " + Farm.COLUMN_ASSIGNEDTECHNICIAN + ") "
                    + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, farm.getAddress());
            ps.setInt(2, farm.getBarangayID());
            ps.setString(3, farm.getDateVisited());
            ps.setDouble(4, farm.getFarmableArea());
            ps.setInt(5, farm.getFarmID());
            ps.setString(6, farm.getFarmName());
            ps.setInt(7, farm.getFlag());
            ps.setString(8, farm.getIrrigationMethod());
            ps.setString(9, farm.getLandElevation());
            ps.setDouble(10, farm.getLatitude());
            ps.setDouble(11, farm.getLongitude());
            ps.setInt(12, farm.getAssignedTechnician());

            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(FarmDAO.class.getName()).log(Level.SEVERE, null, x);
            return false;
        }
        return true;
    }

    public boolean assignFarmAssign(Farm farm) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("UPDATE " + Farm.TABLE_NAME
                    + " SET " + Farm.COLUMN_ASSIGNEDTECHNICIAN + " = ?, " + Farm.COLUMN_FARMID + " = ? "
                    + "WHERE " + Farm.COLUMN_FARMID + " = ?");
            ps.setInt(1, farm.getAssignedTechnician());
            ps.setInt(2, farm.getFarmID());
            ps.setInt(3, farm.getFarmID());

            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(FarmDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public boolean updateFarm(Farm farm) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("UPDATE " + Farm.TABLE_NAME
                    + " SET " + Farm.COLUMN_ADDRESS + " = ?, " + Farm.COLUMN_ASSIGNEDTECHNICIAN + " = ?, " + Farm.COLUMN_BARANGAYID + " = ?, "
                    + Farm.COLUMN_DATEVISITED + " = ?, " + Farm.COLUMN_FARMABLEAREA + " = ?, " + Farm.COLUMN_FARMID + " = ?, "
                    + Farm.COLUMN_FARMNAME + " = ?, " + Farm.COLUMN_FLAG + " = ?, " + Farm.COLUMN_IRRIGATIONMETHOD + " = ?, "
                    + Farm.COLUMN_LANDELEVATION + " = ?, " + Farm.COLUMN_LATITUDE + " = ?, " + Farm.COLUMN_LONGITUDE + " = ? "
                    + "WHERE " + Farm.COLUMN_FARMID + " = ?");
            ps.setString(1, farm.getAddress());
            ps.setInt(2, farm.getAssignedTechnician());
            ps.setInt(3, farm.getBarangayID());
            ps.setString(4, farm.getDateVisited());
            ps.setDouble(5, farm.getFarmableArea());
            ps.setInt(6, farm.getFarmID());
            ps.setString(7, farm.getFarmName());
            ps.setInt(8, farm.getFlag());
            ps.setString(9, farm.getIrrigationMethod());
            ps.setString(10, farm.getLandElevation());
            ps.setDouble(11, farm.getLatitude());
            ps.setDouble(12, farm.getLongitude());
            ps.setInt(13, farm.getFarmID());

            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(FarmDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public Farm getFarmDetailOnFarmID(int farmID) {
        ArrayList<Farm> farms = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + Farm.TABLE_NAME + " WHERE " + Farm.COLUMN_FARMID + " = ?");
            ps.setInt(1, farmID);

            ResultSet rs = ps.executeQuery();
            farms = getDataFromResultSet(rs);

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(FarmDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return farms.get(0);
    }

    public Farm getFarmDetailOnFarmName(String farmName) {
        ArrayList<Farm> farms = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT F.*, E.LastName, E.FirstName, E.MiddleName "
                    + "FROM Farm F JOIN Employee E ON F.AssignedTechnician = E.EmployeeID "
                    + "WHERE F.FarmName = ?");
            ps.setString(1, farmName);

            ResultSet rs = ps.executeQuery();
            farms = getDataFromResultSet(rs);

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(FarmDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return farms.get(0);
    }

    public ArrayList<Farm> getFarmListWithApprovedProblemNameOnBarangay(String barangayName, String problemName) {
        ArrayList<Farm> farms = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT F.*, DI.*, PM.ProblemName, SUM(DR2.AreaAffected) AS 'AreaAffected', SUM(DR2.AreaDamaged) AS 'AreaDamaged'\n"
                    + "FROM DamageReport DR2 JOIN DamageIncident DI ON DI.DamageIncidentID = DR2.DamageIncidentID\n"
                    + "                      JOIN PlantingReport PR ON PR.PlantingReportID = DI.PlantingReportID\n"
                    + "                      JOIN Plot P ON PR.PlotID = P.PlotID\n"
                    + "                      JOIN Farm F ON F.FarmID = P.FarmID\n"
                    + "                      JOIN Problem PM ON PM.ProblemID = DI.ProblemReported\n"
                    + "                      JOIN Barangay B ON B.BarangayID = B.BarangayID\n"
                    + "                      JOIN (SELECT DR1.DamageIncidentID, MAX(DR1.DateReported) AS 'RecentDate'\n"
                    + "				   FROM DamageReport DR1\n"
                    + "				   GROUP BY DR1.DamageIncidentID) T1 ON DR2.DamageIncidentID = T1.DamageIncidentID\n"
                    + "WHERE DR2.DateReported = T1.RecentDate AND DI.Status = 'Approved' AND B.BarangayName = ?\n"
                    + "GROUP BY F.FarmID, DI.ProblemReported\n"
                    + "HAVING ProblemName = ?");
            ps.setString(1, barangayName);
            ps.setString(2, problemName);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Farm farm = new Farm();
                farm.setAddress(rs.getString(Farm.COLUMN_ADDRESS));
                farm.setBarangayID(rs.getInt(Farm.COLUMN_BARANGAYID));
                farm.setDateVisited(rs.getString(Farm.COLUMN_DATEVISITED));
                farm.setFarmID(rs.getInt(Farm.COLUMN_FARMID));
                farm.setFarmName(rs.getString(Farm.COLUMN_FARMNAME));
                farm.setFarmableArea(rs.getDouble(Farm.COLUMN_FARMABLEAREA));
                farm.setFlag(rs.getInt(Farm.COLUMN_FLAG));
                farm.setIrrigationMethod(rs.getString(Farm.COLUMN_IRRIGATIONMETHOD));
                farm.setLandElevation(rs.getString(Farm.COLUMN_LANDELEVATION));
                farm.setLatitude(rs.getDouble(Farm.COLUMN_LATITUDE));
                farm.setLongitude(rs.getDouble(Farm.COLUMN_LONGITUDE));
                farm.setAssignedTechnician(rs.getInt(Farm.COLUMN_ASSIGNEDTECHNICIAN));
                farm.setProblemName(rs.getString("problemName"));
                farm.setAreaAffected(rs.getDouble("AreaAffected"));
                farm.setAreaDamaged(rs.getDouble("AreaDamaged"));
                farms.add(farm);
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(FarmDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return farms;
    }

    public ArrayList<Farm> getFarmListWithOngoingDamageIncidentsOnBarangay(int barangayID) {
        ArrayList<Farm> farms = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT F.*, DI.DamageIncidentID, PM.ProblemName, SUM(DR2.AreaAffected) AS 'AreaAffected', SUM(DR2.AreaDamaged) AS 'AreaDamaged'\n"
                    + "FROM DamageReport DR2 JOIN DamageIncident DI ON DI.DamageIncidentID = DR2.DamageIncidentID\n"
                    + "                      JOIN PlantingReport PR ON PR.PlantingReportID = DI.PlantingReportID\n"
                    + "                      JOIN Plot P ON PR.PlotID = P.PlotID\n"
                    + "                      JOIN Farm F ON F.FarmID = P.FarmID\n"
                    + "                      JOIN Problem PM ON PM.ProblemID = DI.ProblemReported\n"
                    + "                      JOIN Barangay B ON B.BarangayID = B.BarangayID\n"
                    + "                      JOIN (SELECT DR1.DamageIncidentID, MAX(DR1.DateReported) AS 'RecentDate'\n"
                    + "				   FROM DamageReport DR1\n"
                    + "				   GROUP BY DR1.DamageIncidentID) T1 ON DR2.DamageIncidentID = T1.DamageIncidentID\n"
                    + "WHERE DR2.DateReported = T1.RecentDate AND (DI.Status = 'Approved' OR DI.Status = 'Program') AND B.BarangayID = ?\n"
                    + "GROUP BY F.FarmID, DI.ProblemReported");
            ps.setInt(1, barangayID);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Farm farm = new Farm();
                farm.setAddress(rs.getString(Farm.COLUMN_ADDRESS));
                farm.setBarangayID(rs.getInt(Farm.COLUMN_BARANGAYID));
                farm.setDateVisited(rs.getString(Farm.COLUMN_DATEVISITED));
                farm.setFarmID(rs.getInt(Farm.COLUMN_FARMID));
                farm.setFarmName(rs.getString(Farm.COLUMN_FARMNAME));
                farm.setFarmableArea(rs.getDouble(Farm.COLUMN_FARMABLEAREA));
                farm.setFlag(rs.getInt(Farm.COLUMN_FLAG));
                farm.setIrrigationMethod(rs.getString(Farm.COLUMN_IRRIGATIONMETHOD));
                farm.setLandElevation(rs.getString(Farm.COLUMN_LANDELEVATION));
                farm.setLatitude(rs.getDouble(Farm.COLUMN_LATITUDE));
                farm.setLongitude(rs.getDouble(Farm.COLUMN_LONGITUDE));
                farm.setAssignedTechnician(rs.getInt(Farm.COLUMN_ASSIGNEDTECHNICIAN));
                farm.setDamageIncidentID(rs.getInt("DamageIncidentID"));
                farm.setProblemName(rs.getString("problemName"));
                farm.setAreaAffected(rs.getDouble("AreaAffected"));
                farm.setAreaDamaged(rs.getDouble("AreaDamaged"));
                farms.add(farm);
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(FarmDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return farms;
    }

    public ArrayList<Farm> getOngoingDamageIncidentsOnFarm(int farmID) {
        ArrayList<Farm> farms = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT F.*, DI.DamageIncidentID, PM.ProblemName, SUM(DR2.AreaAffected) AS 'AreaAffected', SUM(DR2.AreaDamaged) AS 'AreaDamaged'\n"
                    + "FROM DamageReport DR2 JOIN DamageIncident DI ON DI.DamageIncidentID = DR2.DamageIncidentID\n"
                    + "                      JOIN PlantingReport PR ON PR.PlantingReportID = DI.PlantingReportID\n"
                    + "                      JOIN Plot P ON PR.PlotID = P.PlotID\n"
                    + "                      JOIN Farm F ON F.FarmID = P.FarmID\n"
                    + "                      JOIN Problem PM ON PM.ProblemID = DI.ProblemReported\n"
                    + "                      JOIN (SELECT DR1.DamageIncidentID, MAX(DR1.DateReported) AS 'RecentDate'\n"
                    + "				   FROM DamageReport DR1\n"
                    + "				   GROUP BY DR1.DamageIncidentID) T1 ON DR2.DamageIncidentID = T1.DamageIncidentID\n"
                    + "WHERE DR2.DateReported = T1.RecentDate AND (DI.Status = 'Approved' OR DI.Status = 'Program')\n"
                    + "GROUP BY F.FarmID, DI.ProblemReported\n"
                    + "HAVING F.FarmID = ?");
            ps.setInt(1, farmID);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Farm farm = new Farm();
                farm.setAddress(rs.getString(Farm.COLUMN_ADDRESS));
                farm.setBarangayID(rs.getInt(Farm.COLUMN_BARANGAYID));
                farm.setDateVisited(rs.getString(Farm.COLUMN_DATEVISITED));
                farm.setFarmID(rs.getInt(Farm.COLUMN_FARMID));
                farm.setFarmName(rs.getString(Farm.COLUMN_FARMNAME));
                farm.setFarmableArea(rs.getDouble(Farm.COLUMN_FARMABLEAREA));
                farm.setFlag(rs.getInt(Farm.COLUMN_FLAG));
                farm.setIrrigationMethod(rs.getString(Farm.COLUMN_IRRIGATIONMETHOD));
                farm.setLandElevation(rs.getString(Farm.COLUMN_LANDELEVATION));
                farm.setLatitude(rs.getDouble(Farm.COLUMN_LATITUDE));
                farm.setLongitude(rs.getDouble(Farm.COLUMN_LONGITUDE));
                farm.setAssignedTechnician(rs.getInt(Farm.COLUMN_ASSIGNEDTECHNICIAN));
                farm.setDamageIncidentID(rs.getInt("DamageIncidentID"));
                farm.setProblemName(rs.getString("ProblemName"));
                farm.setAreaAffected(rs.getDouble("AreaAffected"));
                farm.setAreaDamaged(rs.getDouble("AreaDamaged"));
                farms.add(farm);
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(FarmDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return farms;
    }

    public ArrayList<Farm> getListOfFarms() {
        ArrayList<Farm> farms = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + Farm.TABLE_NAME);

            ResultSet rs = ps.executeQuery();
            farms = getDataFromResultSet(rs);

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(FarmDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return farms;
    }

    public ArrayList<Farm> getListOfFarmsAssignedTo(int employeeID) {
        ArrayList<Farm> farms = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + Farm.TABLE_NAME + " WHERE " + Farm.COLUMN_ASSIGNEDTECHNICIAN + " = ?");
            ps.setInt(1, employeeID);

            ResultSet rs = ps.executeQuery();
            farms = getDataFromResultSet(rs);

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(FarmDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return farms;
    }

    public ArrayList<Farm> getListOfUnassignedFarmsInMunicipality(int municipalityID) {
        ArrayList<Farm> farms = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT F.* "
                    + "FROM " + Farm.TABLE_NAME + " F JOIN " + Barangay.TABLE_NAME + " B ON F." + Farm.COLUMN_BARANGAYID + " = B." + Barangay.COLUMN_BARANGAYID
                    + " JOIN " + Municipality.TABLE_NAME + " M ON M." + Municipality.COLUMN_MUNICIPALITYID + " = B." + Barangay.COLUMN_MUNICIPALITYID
                    + " WHERE M." + Municipality.COLUMN_MUNICIPALITYID + " = ? AND F." + Farm.COLUMN_ASSIGNEDTECHNICIAN + " = -1");
            ps.setInt(1, municipalityID);

            ResultSet rs = ps.executeQuery();
            farms = getDataFromResultSet(rs);

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(FarmDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return farms;
    }

    public ArrayList<Farm> getListOfFarmsInMunicipality(String municipalityName) {
        ArrayList<Farm> farms = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT F.* "
                    + "FROM " + Farm.TABLE_NAME + " F JOIN " + Barangay.TABLE_NAME + " B ON F." + Farm.COLUMN_BARANGAYID + " = B." + Barangay.COLUMN_BARANGAYID
                    + " JOIN " + Municipality.TABLE_NAME + " M ON M." + Municipality.COLUMN_MUNICIPALITYID + " = B." + Barangay.COLUMN_MUNICIPALITYID
                    + " WHERE M." + Municipality.COLUMN_MUNICIPALITYNAME + " = ?");
            ps.setString(1, municipalityName);

            ResultSet rs = ps.executeQuery();
            farms = getDataFromResultSet(rs);

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(FarmDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return farms;
    }

    public ArrayList<Farm> getListOfFarmsInBarangay(String barangayName) {
        ArrayList<Farm> farms = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT * "
                    + "FROM " + Farm.TABLE_NAME + " F JOIN " + Barangay.TABLE_NAME + " B ON F." + Farm.COLUMN_BARANGAYID + " = B." + Barangay.COLUMN_BARANGAYID
                    + " WHERE B." + Barangay.COLUMN_BARANGAYNAME + " = ?");
            ps.setString(1, barangayName);

            ResultSet rs = ps.executeQuery();
            farms = getDataFromResultSet(rs);

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(FarmDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return farms;
    }

    public ArrayList<Farm> getListOfFarmsProductionInBarangay(String barangayName, int year) {
        ArrayList<Farm> farms = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            String dateStart = "03-16-" + year;
            String dateEnd = "03-15-" + (year + 1);
            PreparedStatement ps = conn.prepareStatement("SELECT F.*, SUM(PR.AmountHarvested) AS 'TotalHarvested' \n"
                    + "FROM Barangay B JOIN Farm F ON B.BarangayID = F.BarangayID\n"
                    + "                JOIN Plot P ON F.FarmID = P.FarmID\n"
                    + "                JOIN PlantingReport PR ON PR.PlotID = P.PlotID\n"
                    + "WHERE STR_TO_DATE(CONCAT(PR.HarvestMonth, '-', PR.HarvestDay, '-', PR.HarvestYear), '%m-%d-%Y') > STR_TO_DATE(?, '%m-%d-%Y')\n"
                    + "		AND STR_TO_DATE(CONCAT(PR.HarvestMonth, '-', PR.HarvestDay, '-', PR.HarvestYear), '%m-%d-%Y') < STR_TO_DATE(?, '%m-%d-%Y')\n"
                    + "        AND B.BarangayName = ?\n"
                    + "GROUP BY F.FarmID");
            ps.setString(1, dateStart);
            ps.setString(2, dateEnd);
            ps.setString(3, barangayName);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Farm farm = new Farm();
                farm.setAddress(rs.getString(Farm.COLUMN_ADDRESS));
                farm.setBarangayID(rs.getInt(Farm.COLUMN_BARANGAYID));
                farm.setDateVisited(rs.getString(Farm.COLUMN_DATEVISITED));
                farm.setFarmID(rs.getInt(Farm.COLUMN_FARMID));
                farm.setFarmName(rs.getString(Farm.COLUMN_FARMNAME));
                farm.setFarmableArea(rs.getDouble(Farm.COLUMN_FARMABLEAREA));
                farm.setFlag(rs.getInt(Farm.COLUMN_FLAG));
                farm.setIrrigationMethod(rs.getString(Farm.COLUMN_IRRIGATIONMETHOD));
                farm.setLandElevation(rs.getString(Farm.COLUMN_LANDELEVATION));
                farm.setLatitude(rs.getDouble(Farm.COLUMN_LATITUDE));
                farm.setLongitude(rs.getDouble(Farm.COLUMN_LONGITUDE));
                farm.setAssignedTechnician(rs.getInt(Farm.COLUMN_ASSIGNEDTECHNICIAN));
                farm.setHarvest(rs.getDouble("TotalHarvested"));
                farms.add(farm);
            }

            rs.close();
            ps.close();
            conn.close();

            if (farms.isEmpty()) {
                ArrayList<Farm> farmList = getListOfFarmsInBarangay(barangayName);
                farms.addAll(farmList);
            }
        } catch (SQLException x) {
            Logger.getLogger(FarmDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return farms;
    }

    private ArrayList<Farm> getDataFromResultSet(ResultSet rs) throws SQLException {
        ArrayList<Farm> farms = new ArrayList<>();
        while (rs.next()) {
            Farm farm = new Farm();
            farm.setAddress(rs.getString(Farm.COLUMN_ADDRESS));
            farm.setBarangayID(rs.getInt(Farm.COLUMN_BARANGAYID));
            farm.setDateVisited(rs.getString(Farm.COLUMN_DATEVISITED));
            farm.setFarmID(rs.getInt(Farm.COLUMN_FARMID));
            farm.setFarmName(rs.getString(Farm.COLUMN_FARMNAME));
            farm.setFarmableArea(rs.getDouble(Farm.COLUMN_FARMABLEAREA));
            farm.setFlag(rs.getInt(Farm.COLUMN_FLAG));
            farm.setIrrigationMethod(rs.getString(Farm.COLUMN_IRRIGATIONMETHOD));
            farm.setLandElevation(rs.getString(Farm.COLUMN_LANDELEVATION));
            farm.setLatitude(rs.getDouble(Farm.COLUMN_LATITUDE));
            farm.setLongitude(rs.getDouble(Farm.COLUMN_LONGITUDE));
            farm.setAssignedTechnician(rs.getInt(Farm.COLUMN_ASSIGNEDTECHNICIAN));
            farms.add(farm);
        }
        return farms;
    }
}
