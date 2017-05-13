/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import db.DBConnectionFactory;
import extra.Calculator;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import object.DeployedEvaluation;
import object.DeployedProgram;
import object.Plot;

/**
 *
 * @author RubySenpaii
 */
public class DeployedProgramDAO {

    public boolean addDeployedProgram(DeployedProgram deployedProgram) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("INSERT INTO DeployedProgram "
                    + "(AssignedMunicipality, DateCompleted, DateStarted, DeployedID, FertilizerAmount, FertilizerProvided, ProgramPlanID, DeployedStatus, VarietyAmount, VarietyProvided) "
                    + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setInt(1, deployedProgram.getAssignedMunicipality());
            ps.setString(2, deployedProgram.getDateCompleted());
            ps.setString(3, deployedProgram.getDateStarted());
            ps.setInt(4, deployedProgram.getDeployedID());
            ps.setDouble(5, deployedProgram.getFertilizerAmount());
            ps.setString(6, deployedProgram.getFertilizerProvided());
            ps.setInt(7, deployedProgram.getProgramPlanID());
            ps.setString(8, deployedProgram.getStatus());
            ps.setDouble(9, deployedProgram.getVarietyAmount());
            ps.setString(10, deployedProgram.getVarietyProvided());

            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(DeployedProgramDAO.class.getName()).log(Level.SEVERE, null, x);
            return false;
        }
        return true;
    }

    public boolean updateDeployedProgram(DeployedProgram deployedProgram) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            
            PreparedStatement ps = conn.prepareStatement("UPDATE " + DeployedProgram.TABLE_NAME + 
                    " SET " + DeployedProgram.COLUMN_ASSIGNEDMUNICIPALITY + " = ?, " + DeployedProgram.COLUMN_DATECOMPLETED + " = ?, "
                    + DeployedProgram.COLUMN_DATESTARTED + " = ?, " + DeployedProgram.COLUMN_DEPLOYEDID + " = ?, " + DeployedProgram.COLUMN_FERTILIZERAMOUNT + " = ?, "
                    + DeployedProgram.COLUMN_FERTILIZERPROVIDED + " = ?, " + DeployedProgram.COLUMN_PROGRAMPLANID + " = ?, " + DeployedProgram.COLUMN_STATUS + " = ?, "
                    + DeployedProgram.COLUMN_VARIETYAMOUNT + " = ?, " + DeployedProgram.COLUMN_VARIETYPROVIDED + " = ? "
                    + "WHERE " + DeployedProgram.COLUMN_DEPLOYEDID + " = ?");
            ps.setInt(1, deployedProgram.getAssignedMunicipality());
            ps.setString(2, deployedProgram.getDateCompleted());
            ps.setString(3, deployedProgram.getDateStarted());
            ps.setInt(4, deployedProgram.getDeployedID());
            ps.setDouble(5, deployedProgram.getFertilizerAmount());
            ps.setString(6, deployedProgram.getFertilizerProvided());
            ps.setInt(7, deployedProgram.getProgramPlanID());
            ps.setString(8, deployedProgram.getStatus());
            ps.setDouble(8, deployedProgram.getVarietyAmount());
            ps.setString(10, deployedProgram.getVarietyProvided());
            ps.setInt(11, deployedProgram.getDeployedID());
            
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DeployedProgramDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } return true;
    }
    
    
    public ArrayList<DeployedProgram> getListOfDeployedPrograms() {
        ArrayList<DeployedProgram> deployedPrograms = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + DeployedProgram.TABLE_NAME);

            ResultSet rs = ps.executeQuery();
            deployedPrograms = getDataFromResultSet(rs);

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(DeployedProgramDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return deployedPrograms;
    }
    
    public ArrayList<DeployedProgram> getListOfDeployedProgramsForProgramPlanID(int programPlanID) {
        ArrayList<DeployedProgram> deployedPrograms = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + DeployedProgram.TABLE_NAME + " WHERE " + DeployedProgram.COLUMN_PROGRAMPLANID + " = ?");
            ps.setInt(1, programPlanID);

            ResultSet rs = ps.executeQuery();
            deployedPrograms = getDataFromResultSet(rs);

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(DeployedProgramDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return deployedPrograms;
    }

    public DeployedProgram getDeployedProgramDetails(int deployedID) {
        ArrayList<DeployedProgram> deployedPrograms = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT DP.*, PP.ProgramName, PP.Type "
                    + "FROM DeployedProgram DP JOIN ProgramPlan PP ON DP.ProgramPlanID = PP.ProgramPlanID "
                    + "WHERE DP.DeployedID = ?");
            ps.setInt(1, deployedID);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DeployedProgram deployedProgram = new DeployedProgram();
                deployedProgram.setAssignedMunicipality(rs.getInt(DeployedProgram.COLUMN_ASSIGNEDMUNICIPALITY));
                deployedProgram.setDateCompleted(rs.getString(DeployedProgram.COLUMN_DATECOMPLETED));
                deployedProgram.setDateStarted(rs.getString(DeployedProgram.COLUMN_DATESTARTED));
                deployedProgram.setDeployedID(rs.getInt(DeployedProgram.COLUMN_DEPLOYEDID));
                deployedProgram.setFertilizerAmount(rs.getDouble(DeployedProgram.COLUMN_FERTILIZERAMOUNT));
                deployedProgram.setFertilizerProvided(rs.getString(DeployedProgram.COLUMN_FERTILIZERPROVIDED));
                deployedProgram.setProgramPlanID(rs.getInt(DeployedProgram.COLUMN_PROGRAMPLANID));
                deployedProgram.setStatus(rs.getString(DeployedProgram.COLUMN_STATUS));
                deployedProgram.setVarietyAmount(rs.getDouble(DeployedProgram.COLUMN_VARIETYAMOUNT));
                deployedProgram.setVarietyProvided(rs.getString(DeployedProgram.COLUMN_VARIETYPROVIDED));
                deployedProgram.setProgramName(rs.getString("ProgramName"));
                deployedProgram.setProgramType(rs.getString("Type"));
                deployedPrograms.add(deployedProgram);
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(DeployedProgramDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return deployedPrograms.get(0);
    }

    public ArrayList<DeployedProgram> getListOfOngoingProgramsForMunicipality(int municipalityID) {
        ArrayList<DeployedProgram> deployedPrograms = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT DP.*, PP.*, M.* "
                    + "FROM DeployedProgram DP JOIN ProgramPlan PP ON DP.ProgramPlanID = PP.ProgramPlanID "
                    + "                         JOIN Municipality M ON M.MunicipalityID = DP.AssignedMunicipality "
                    + "WHERE DP.DeployedStatus != 'Completed' AND DP.AssignedMunicipality = ?");
            ps.setInt(1, municipalityID);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DeployedProgram deployedProgram = new DeployedProgram();
                deployedProgram.setAssignedMunicipality(rs.getInt(DeployedProgram.COLUMN_ASSIGNEDMUNICIPALITY));
                deployedProgram.setDateCompleted(rs.getString(DeployedProgram.COLUMN_DATECOMPLETED));
                deployedProgram.setDateStarted(rs.getString(DeployedProgram.COLUMN_DATESTARTED));
                deployedProgram.setDeployedID(rs.getInt(DeployedProgram.COLUMN_DEPLOYEDID));
                deployedProgram.setFertilizerAmount(rs.getDouble(DeployedProgram.COLUMN_FERTILIZERAMOUNT));
                deployedProgram.setFertilizerProvided(rs.getString(DeployedProgram.COLUMN_FERTILIZERPROVIDED));
                deployedProgram.setProgramPlanID(rs.getInt(DeployedProgram.COLUMN_PROGRAMPLANID));
                deployedProgram.setStatus(rs.getString(DeployedProgram.COLUMN_STATUS));
                deployedProgram.setVarietyAmount(rs.getDouble(DeployedProgram.COLUMN_VARIETYAMOUNT));
                deployedProgram.setVarietyProvided(rs.getString(DeployedProgram.COLUMN_VARIETYPROVIDED));
                deployedProgram.setProgramName(rs.getString("ProgramName"));
                deployedProgram.setProgramType(rs.getString("Type"));
                deployedProgram.setMunicipalName(rs.getString("MunicipalityName"));
                deployedPrograms.add(deployedProgram);
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(DeployedProgramDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return deployedPrograms;
    }

    public ArrayList<DeployedProgram> getListOfDeployedProgramsForProgramPlan(int programPlanID) {
        ArrayList<DeployedProgram> deployedPrograms = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT DP.*, M.MunicipalityName, T1.FarmCount\n"
                    + "FROM DeployedProgram DP JOIN ProgramPlan PP ON DP.ProgramPlanID = PP.ProgramPlanID\n"
                    + "                        JOIN Municipality M ON DP.AssignedMunicipality = M.MunicipalityID\n"
                    + "                        JOIN (SELECT PB.DeployedID, COUNT(PB.FarmID) AS 'FarmCount'\n"
                    + "                              FROM ProgramBeneficiary PB\n"
                    + "                              GROUP BY PB.DeployedID) T1 ON T1.DeployedID = DP.DeployedID\n"
                    + "WHERE PP.ProgramPlanID = ?");
            ps.setInt(1, programPlanID);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DeployedProgram deployedProgram = new DeployedProgram();
                deployedProgram.setAssignedMunicipality(rs.getInt(DeployedProgram.COLUMN_ASSIGNEDMUNICIPALITY));
                deployedProgram.setDateCompleted(rs.getString(DeployedProgram.COLUMN_DATECOMPLETED));
                deployedProgram.setDateStarted(rs.getString(DeployedProgram.COLUMN_DATESTARTED));
                deployedProgram.setDeployedID(rs.getInt(DeployedProgram.COLUMN_DEPLOYEDID));
                deployedProgram.setFertilizerAmount(rs.getDouble(DeployedProgram.COLUMN_FERTILIZERAMOUNT));
                deployedProgram.setFertilizerProvided(rs.getString(DeployedProgram.COLUMN_FERTILIZERPROVIDED));
                deployedProgram.setProgramPlanID(rs.getInt(DeployedProgram.COLUMN_PROGRAMPLANID));
                deployedProgram.setStatus(rs.getString(DeployedProgram.COLUMN_STATUS));
                deployedProgram.setVarietyAmount(rs.getDouble(DeployedProgram.COLUMN_VARIETYAMOUNT));
                deployedProgram.setVarietyProvided(rs.getString(DeployedProgram.COLUMN_VARIETYPROVIDED));
                deployedProgram.setMunicipalName(rs.getString("MunicipalityName"));
                deployedProgram.setFarmCount(rs.getInt("FarmCount"));
                
                ArrayList<DeployedEvaluation> evaluations = new DeployedEvaluationDAO().getListOfProgramEvaluationsOnDeployedID(deployedProgram.getDeployedID());
                double total = 0;
                int count = 0;
                for (int a = 0; a < evaluations.size(); a++) {
                    double evaluation = new Calculator().getRespondentResult(evaluations.get(a).getEvaluationValues());
                    total += evaluation;
                    if (evaluation > 0) {
                        count++;
                    }
                }
                String result = new Calculator().getEffectivityResult(total / count);
                if (count == 0) {
                    result = new Calculator().getEffectivityResult(0);
                }
                deployedProgram.setEffectivityResult(result);
                deployedPrograms.add(deployedProgram);
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(DeployedProgramDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return deployedPrograms;
    }

    public ArrayList<DeployedProgram> getListOfDeployedProgramsForFarm(int farmID) {
        ArrayList<DeployedProgram> deployedPrograms = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT DP.*, PB.FarmID, PB.VarietyReceived, PB.FertilizerReceived, PB.DateReceived, PP.ProgramName, PP.Type\n"
                    + "FROM DeployedProgram DP JOIN ProgramBeneficiary PB ON DP.DeployedID = PB.DeployedID\n"
                    + "                        JOIN ProgramPlan PP ON DP.ProgramPlanID = PP.ProgramPlanID\n"
                    + "WHERE PB.FarmID = ?");
            ps.setInt(1, farmID);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DeployedProgram deployedProgram = new DeployedProgram();
                deployedProgram.setAssignedMunicipality(rs.getInt(DeployedProgram.COLUMN_ASSIGNEDMUNICIPALITY));
                deployedProgram.setDateCompleted(rs.getString(DeployedProgram.COLUMN_DATECOMPLETED));
                deployedProgram.setDateStarted(rs.getString(DeployedProgram.COLUMN_DATESTARTED));
                deployedProgram.setDeployedID(rs.getInt(DeployedProgram.COLUMN_DEPLOYEDID));
                deployedProgram.setFertilizerAmount(rs.getDouble(DeployedProgram.COLUMN_FERTILIZERAMOUNT));
                deployedProgram.setFertilizerProvided(rs.getString(DeployedProgram.COLUMN_FERTILIZERPROVIDED));
                deployedProgram.setProgramPlanID(rs.getInt(DeployedProgram.COLUMN_PROGRAMPLANID));
                deployedProgram.setStatus(rs.getString(DeployedProgram.COLUMN_STATUS));
                deployedProgram.setVarietyAmount(rs.getDouble(DeployedProgram.COLUMN_VARIETYAMOUNT));
                deployedProgram.setVarietyProvided(rs.getString(DeployedProgram.COLUMN_VARIETYPROVIDED));
                deployedProgram.setFarmID(rs.getInt("FarmID"));
                deployedProgram.setVarietyReceived(rs.getDouble("VarietyReceived"));
                deployedProgram.setFertilizerReceived(rs.getDouble("FertilizerReceived"));
                deployedProgram.setDateReceived(rs.getString("DateReceived"));
                deployedProgram.setProgramName(rs.getString("ProgramName"));
                deployedProgram.setProgramType(rs.getString("Type"));
                deployedPrograms.add(deployedProgram);
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(DeployedProgramDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return deployedPrograms;
    }

    public String getInitialDateAffectedOfFarmsInDeployedProgram(int deployedID) {
        String date = "N/A";
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT MIN(STR_TO_DATE(CONCAT(DamagedMonth, '-', DamagedDay, '-', DamagedYear), '%m-%d-%Y')) AS 'DamagedDate'\n"
                    + "FROM DamageIncident DI JOIN DeployedProgram DP ON DI.DeployedID = DP.DeployedID\n"
                    + "WHERE DI.DeployedID = ?");
            ps.setInt(1, deployedID);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String damagedDate = rs.getString("DamagedDate");
                date = damagedDate.substring(5) + "-" + damagedDate.substring(0, 4);
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(DeployedProgramDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return date;
    }

    private ArrayList<DeployedProgram> getDataFromResultSet(ResultSet rs) throws SQLException {
        ArrayList<DeployedProgram> deployedPrograms = new ArrayList<>();
        while (rs.next()) {
            DeployedProgram deployedProgram = new DeployedProgram();
            deployedProgram.setAssignedMunicipality(rs.getInt(DeployedProgram.COLUMN_ASSIGNEDMUNICIPALITY));
            deployedProgram.setDateCompleted(rs.getString(DeployedProgram.COLUMN_DATECOMPLETED));
            deployedProgram.setDateStarted(rs.getString(DeployedProgram.COLUMN_DATESTARTED));
            deployedProgram.setDeployedID(rs.getInt(DeployedProgram.COLUMN_DEPLOYEDID));
            deployedProgram.setFertilizerAmount(rs.getDouble(DeployedProgram.COLUMN_FERTILIZERAMOUNT));
            deployedProgram.setFertilizerProvided(rs.getString(DeployedProgram.COLUMN_FERTILIZERPROVIDED));
            deployedProgram.setProgramPlanID(rs.getInt(DeployedProgram.COLUMN_PROGRAMPLANID));
            deployedProgram.setStatus(rs.getString(DeployedProgram.COLUMN_STATUS));
            deployedProgram.setVarietyAmount(rs.getDouble(DeployedProgram.COLUMN_VARIETYAMOUNT));
            deployedProgram.setVarietyProvided(rs.getString(DeployedProgram.COLUMN_VARIETYPROVIDED));
            deployedPrograms.add(deployedProgram);
        }
        return deployedPrograms;
    }
}
