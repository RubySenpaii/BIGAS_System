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
import object.Plot;
import object.ProgramBeneficiary;

/**
 *
 * @author RubySenpaii
 */
public class ProgramBeneficiaryDAO {

    public boolean addProgramBeneficiary(ProgramBeneficiary programBeneficiary) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("INSERT INTO " + ProgramBeneficiary.TABLE_NAME + " "
                    + "(" + ProgramBeneficiary.COLUMN_DATERECEIVED + ", " + ProgramBeneficiary.COLUMN_DEPLOYEDID + ", " + ProgramBeneficiary.COLUMN_FARMID + ", "
                    + ProgramBeneficiary.COLUMN_FERTILIZERRECEIVED + ", " + ProgramBeneficiary.COLUMN_VARIETYRECEIVED + ") "
                    + "VALUES(?, ?, ?, ?, ?)");
            ps.setString(1, programBeneficiary.getDateReceived());
            ps.setInt(2, programBeneficiary.getDeployedID());
            ps.setInt(3, programBeneficiary.getFarmID());
            ps.setDouble(4, programBeneficiary.getFertilizerReceived());
            ps.setDouble(5, programBeneficiary.getVarietyReceived());

            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(ProgramBeneficiaryDAO.class.getName()).log(Level.SEVERE, null, x);
            return false;
        }
        return true;
    }
    
    public boolean updateProgramBeneficiary(ProgramBeneficiary programBeneficiary) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            
            PreparedStatement ps = conn.prepareStatement("UPDATE " + ProgramBeneficiary.TABLE_NAME + 
                    " SET " + ProgramBeneficiary.COLUMN_DATERECEIVED + " = ?, " + ProgramBeneficiary.COLUMN_DEPLOYEDID + " = ?, " + ProgramBeneficiary.COLUMN_FARMID + " = ?, "
                    + ProgramBeneficiary.COLUMN_FERTILIZERRECEIVED + " = ?, " + ProgramBeneficiary.COLUMN_VARIETYRECEIVED + " = ? "
                    + "WHERE " + ProgramBeneficiary.COLUMN_DEPLOYEDID + " = ? AND " + ProgramBeneficiary.COLUMN_FARMID + " = ?");
            ps.setString(1, programBeneficiary.getDateReceived());
            ps.setInt(2, programBeneficiary.getDeployedID());
            ps.setInt(3, programBeneficiary.getFarmID());
            ps.setDouble(4, programBeneficiary.getFertilizerReceived());
            ps.setDouble(5, programBeneficiary.getVarietyReceived());
            ps.setInt(6, programBeneficiary.getDeployedID());
            ps.setInt(6, programBeneficiary.getFarmID());
            
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProgramBeneficiaryDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } return true;
    }

    public ArrayList<ProgramBeneficiary> getListOfProgramBeneficiaries() {
        ArrayList<ProgramBeneficiary> programBeneficiaries = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + ProgramBeneficiary.TABLE_NAME);

            ResultSet rs = ps.executeQuery();
            programBeneficiaries = getDataFromResultSet(rs);

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(ProgramBeneficiaryDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return programBeneficiaries;
    }

    public ArrayList<ProgramBeneficiary> getListOfProgramBeneficiariesForDeployedID(int deployedID) {
        ArrayList<ProgramBeneficiary> programBeneficiaries = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT PB.*, F.FarmName, B.BarangayName "
                    + "FROM ProgramBeneficiary PB JOIN Farm F ON PB.FarmID = F.FarmID "
                    + "                           JOIN DeployedProgram DP ON PB.DeployedID = DP.DeployedID "
                    + "                           JOIN Barangay B ON B.BarangayID = F.BarangayID "
                    + "WHERE DP.DeployedID = ?");
            ps.setInt(1, deployedID);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProgramBeneficiary programBeneficiary = new ProgramBeneficiary();
                programBeneficiary.setDateReceived(rs.getString(ProgramBeneficiary.COLUMN_DATERECEIVED));
                programBeneficiary.setDeployedID(rs.getInt(ProgramBeneficiary.COLUMN_DEPLOYEDID));
                programBeneficiary.setFarmID(rs.getInt(ProgramBeneficiary.COLUMN_FARMID));
                programBeneficiary.setFertilizerReceived(rs.getDouble(ProgramBeneficiary.COLUMN_FERTILIZERRECEIVED));
                programBeneficiary.setVarietyReceived(rs.getDouble(ProgramBeneficiary.COLUMN_VARIETYRECEIVED));
                programBeneficiary.setFarmName(rs.getString("FarmName"));
                programBeneficiary.setBarangay(rs.getString("BarangayName"));
                programBeneficiaries.add(programBeneficiary);
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(ProgramBeneficiaryDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return programBeneficiaries;
    }

    private ArrayList<ProgramBeneficiary> getDataFromResultSet(ResultSet rs) throws SQLException {
        ArrayList<ProgramBeneficiary> programBeneficiaries = new ArrayList<>();
        while (rs.next()) {
            ProgramBeneficiary programBeneficiary = new ProgramBeneficiary();
            programBeneficiary.setDateReceived(rs.getString(ProgramBeneficiary.COLUMN_DATERECEIVED));
            programBeneficiary.setDeployedID(rs.getInt(ProgramBeneficiary.COLUMN_DEPLOYEDID));
            programBeneficiary.setFarmID(rs.getInt(ProgramBeneficiary.COLUMN_FARMID));
            programBeneficiary.setFertilizerReceived(rs.getDouble(ProgramBeneficiary.COLUMN_FERTILIZERRECEIVED));
            programBeneficiary.setVarietyReceived(rs.getDouble(ProgramBeneficiary.COLUMN_VARIETYRECEIVED));
            programBeneficiaries.add(programBeneficiary);
        }
        return programBeneficiaries;
    }
}
