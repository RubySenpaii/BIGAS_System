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
import object.ProgramProgress;

/**
 *
 * @author RubySenpaii
 */
public class ProgramProgressDAO {

    public boolean addProgramProgress(ProgramProgress programProgress) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("INSERT INTO " + ProgramProgress.TABLE_NAME + " "
                    + "(" + ProgramProgress.COLUMN_DATECOMPLETED + ", " + ProgramProgress.COLUMN_DEPLOYEDID + ", "
                    + ProgramProgress.COLUMN_IMAGE + ", " + ProgramProgress.COLUMN_PROCEDURENUMBER + ", " + ProgramProgress.COLUMN_UPDATEDBY + ") "
                    + "VALUES(?, ?, ?, ?, ?)");
            ps.setString(1, programProgress.getDateCompleted());
            ps.setInt(2, programProgress.getDeployedID());
            ps.setString(3, programProgress.getImage());
            ps.setInt(4, programProgress.getProcedureNumber());
            ps.setInt(5, programProgress.getUpdatedBy());

            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(ProgramProgressDAO.class.getName()).log(Level.SEVERE, null, x);
            return false;
        }
        return true;
    }
    
    public boolean updateProgramProgress(ProgramProgress programProgres) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            
            PreparedStatement ps = conn.prepareStatement("UPDATE " + ProgramProgress.TABLE_NAME + 
                    " SET " + ProgramProgress.COLUMN_DATECOMPLETED + " = ?, " + ProgramProgress.COLUMN_DEPLOYEDID + " = ?, " + ProgramProgress.COLUMN_IMAGE + " = ?, "
                    + ProgramProgress.COLUMN_PROCEDURENUMBER + " = ?, " + ProgramProgress.COLUMN_UPDATEDBY + " = ? "
                    + "WHERE " + ProgramProgress.COLUMN_DEPLOYEDID + " = ? AND " + ProgramProgress.COLUMN_PROCEDURENUMBER + " = ?");
            ps.setString(1, programProgres.getDateCompleted());
            ps.setInt(2, programProgres.getDeployedID());
            ps.setString(3, programProgres.getImage());
            ps.setInt(4, programProgres.getProcedureNumber());
            ps.setInt(5, programProgres.getUpdatedBy());
            ps.setInt(6, programProgres.getDeployedID());
            ps.setInt(6, programProgres.getProcedureNumber());
            
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProgramProgressDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } return true;
    }

    public ArrayList<ProgramProgress> getListOfProgramProgresses() {
        ArrayList<ProgramProgress> programProgresses = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + ProgramProgress.TABLE_NAME);

            ResultSet rs = ps.executeQuery();
            programProgresses = getDataFromResultSet(rs);

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(ProgramProgressDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return programProgresses;
    }

    public ArrayList<ProgramProgress> getListOfProgramProgressForDeployedID(int deployedID) {
        ArrayList<ProgramProgress> programProgresses = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT PPR.*, E.LastName, E.FirstName "
                    + "FROM ProgramProgress PPR JOIN Employee E ON PPR.UpdatedBy = E.EmployeeID "
                    + "WHERE PPR.DeployedID = ?");
            ps.setInt(1, deployedID);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProgramProgress programProgress = new ProgramProgress();
                programProgress.setDateCompleted(rs.getString(ProgramProgress.COLUMN_DATECOMPLETED));
                programProgress.setDeployedID(rs.getInt(ProgramProgress.COLUMN_DEPLOYEDID));
                programProgress.setImage(rs.getString(ProgramProgress.COLUMN_IMAGE));
                programProgress.setProcedureNumber(rs.getInt(ProgramProgress.COLUMN_PROCEDURENUMBER));
                programProgress.setUpdatedBy(rs.getInt(ProgramProgress.COLUMN_UPDATEDBY));
                programProgress.setUpdatedByName(rs.getString("LastName") + ", " + rs.getString("FirstName"));
                programProgresses.add(programProgress);
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(ProgramProgressDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return programProgresses;
    }

    private ArrayList<ProgramProgress> getDataFromResultSet(ResultSet rs) throws SQLException {
        ArrayList<ProgramProgress> programProgresses = new ArrayList<>();
        while (rs.next()) {
            ProgramProgress programProgress = new ProgramProgress();
            programProgress.setDateCompleted(rs.getString(ProgramProgress.COLUMN_DATECOMPLETED));
            programProgress.setDeployedID(rs.getInt(ProgramProgress.COLUMN_DEPLOYEDID));
            programProgress.setImage(rs.getString(ProgramProgress.COLUMN_IMAGE));
            programProgress.setProcedureNumber(rs.getInt(ProgramProgress.COLUMN_PROCEDURENUMBER));
            programProgress.setUpdatedBy(rs.getInt(ProgramProgress.COLUMN_UPDATEDBY));
            programProgresses.add(programProgress);
        }
        return programProgresses;
    }
}
