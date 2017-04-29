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
import object.ProgramTarget;

/**
 *
 * @author RubySenpaii
 */
public class ProgramTargetDAO {
    
    public boolean addProgramTarget(ProgramTarget programTarget) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("INSERT INTO " + ProgramTarget.TABLE_NAME + " "
                    + "(" + ProgramTarget.COLUMN_ACTUALVALUE + ", " + ProgramTarget.COLUMN_PROGRAMPLANID + ", " + ProgramTarget.COLUMN_TARGETNAME + ", "
                    + ProgramTarget.COLUMN_TARGETVALUE + ", " + ProgramTarget.COLUMN_TARGETYEAR + ") "
                    + "VALUES(?, ?, ?, ?)");
            ps.setDouble(1, programTarget.getActualValue());
            ps.setInt(2, programTarget.getProgramPlanID());
            ps.setString(3, programTarget.getTargetName());
            ps.setDouble(4, programTarget.getTargetValue());
            ps.setInt(5, programTarget.getTargetYear());

            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(ProgramTargetDAO.class.getName()).log(Level.SEVERE, null, x);
            return false;
        }
        return true;
    }
    
    public boolean updateProgramTarget(ProgramTarget programTarget) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            
            PreparedStatement ps = conn.prepareStatement("UPDATE " + ProgramTarget.TABLE_NAME + 
                    " SET " + ProgramTarget.COLUMN_ACTUALVALUE + " = ?, " + ProgramTarget.COLUMN_PROGRAMPLANID + " = ?, " 
                    + ProgramTarget.COLUMN_TARGETNAME + " = ?, " + ProgramTarget.COLUMN_TARGETVALUE + " = ?, " + ProgramTarget.COLUMN_TARGETYEAR + " = ? "
                    + "WHERE " + ProgramTarget.COLUMN_TARGETNAME + " = ? AND " + ProgramTarget.COLUMN_PROGRAMPLANID + " = ?"+ " = ? AND " 
                    + ProgramTarget.COLUMN_TARGETYEAR + " = ?");
            ps.setDouble(1, programTarget.getActualValue());
            ps.setInt(2, programTarget.getProgramPlanID());
            ps.setString(3, programTarget.getTargetName());
            ps.setDouble(4, programTarget.getTargetValue());
            ps.setInt(5, programTarget.getTargetYear());
            ps.setString(6, programTarget.getTargetName());
            ps.setInt(7, programTarget.getProgramPlanID());
            ps.setInt(8, programTarget.getTargetYear());
            
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProgramTargetDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } return true;
    }

    public ArrayList<ProgramTarget> getListOfProgramTargets() {
        ArrayList<ProgramTarget> programTargets = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + ProgramTarget.TABLE_NAME);

            ResultSet rs = ps.executeQuery();
            programTargets = getDataFromResultSet(rs);

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(ProgramTargetDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return programTargets;
    }

    private ArrayList<ProgramTarget> getDataFromResultSet(ResultSet rs) throws SQLException {
        ArrayList<ProgramTarget> programTargets = new ArrayList<>();
        while (rs.next()) {
            ProgramTarget programTarget = new ProgramTarget();
            programTarget.setProgramPlanID(rs.getInt(ProgramTarget.COLUMN_PROGRAMPLANID));
            programTarget.setActualValue(rs.getDouble(ProgramTarget.COLUMN_ACTUALVALUE));
            programTarget.setTargetName(rs.getString(ProgramTarget.COLUMN_TARGETNAME));
            programTarget.setTargetValue(rs.getDouble(ProgramTarget.COLUMN_TARGETVALUE));
            programTarget.setTargetYear(rs.getInt(ProgramTarget.COLUMN_TARGETYEAR));
            programTargets.add(programTarget);
        }
        return programTargets;
    }
}
