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
import object.ProgramTrigger;

/**
 *
 * @author RubySenpaii
 */
public class ProgramTriggerDAO {
    
    public boolean addProgramTrigger(ProgramTrigger programTrigger) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("INSERT INTO " + ProgramTrigger.TABLE_NAME + " "
                    + "(" + ProgramTrigger.COLUMN_PROGRAMID + ", " + ProgramTrigger.COLUMN_TRIGGERNAME + ", "
                    + ProgramTrigger.COLUMN_TRIGGERVALUE + ") "
                    + "VALUES(?, ?, ?, ?)");
            ps.setInt(1, programTrigger.getProgramID());
            ps.setString(2, programTrigger.getTriggerName());
            ps.setDouble(3, programTrigger.getTriggerValue());

            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(ProgramTriggerDAO.class.getName()).log(Level.SEVERE, null, x);
            return false;
        }
        return true;
    }
    
    public ArrayList<ProgramTrigger> getListOfProgramTriggers() {
        ArrayList<ProgramTrigger> programTriggers = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + ProgramTrigger.TABLE_NAME);

            ResultSet rs = ps.executeQuery();
            programTriggers = getDataFromResultSet(rs);

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(ProgramTriggerDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return programTriggers;
    }

    public ArrayList<ProgramTrigger> getListOfProgramTriggersForProgramID(int programID) {
        ArrayList<ProgramTrigger> programTriggers = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + ProgramTrigger.TABLE_NAME + " WHERE " + ProgramTrigger.COLUMN_PROGRAMID + " = ?");
            ps.setInt(1, programID);

            ResultSet rs = ps.executeQuery();
            programTriggers = getDataFromResultSet(rs);

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(ProgramTriggerDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return programTriggers;
    }

    private ArrayList<ProgramTrigger> getDataFromResultSet(ResultSet rs) throws SQLException {
        ArrayList<ProgramTrigger> programTriggers = new ArrayList<>();
        while (rs.next()) {
            ProgramTrigger programTrigger = new ProgramTrigger();
            programTrigger.setProgramID(rs.getInt(ProgramTrigger.COLUMN_PROGRAMID));
            programTrigger.setTriggerName(rs.getString(ProgramTrigger.COLUMN_TRIGGERNAME));
            programTrigger.setTriggerValue(rs.getDouble(ProgramTrigger.COLUMN_TRIGGERVALUE));
            programTriggers.add(programTrigger);
        }
        return programTriggers;
    }
}
