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
import object.ProgramProcedure;

/**
 *
 * @author RubySenpaii
 */
public class ProgramProcedureDAO {
    
    public boolean addProgramProcedure(ProgramProcedure programProcedure) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("INSERT INTO " + ProgramProcedure.TABLE_NAME + " "
                    + "(" + ProgramProcedure.COLUMN_ACTIVITY + ", " + ProgramProcedure.COLUMN_PHASE + ", "
                    + ProgramProcedure.COLUMN_PROCEDURENO + ", " + ProgramProcedure.COLUMN_PROGRAMPLANID + ") "
                    + "VALUES(?, ?, ?, ?)");
            ps.setString(1, programProcedure.getActivity());
            ps.setString(2, programProcedure.getPhase());
            ps.setInt(3, programProcedure.getProcedureNo());
            ps.setInt(4, programProcedure.getProgramPlanID());

            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(ProgramProcedureDAO.class.getName()).log(Level.SEVERE, null, x);
            return false;
        }
        return true;
    }

    public ArrayList<ProgramProcedure> getListOfProgramProcedures() {
        ArrayList<ProgramProcedure> programProcedures = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + ProgramProcedure.TABLE_NAME);

            ResultSet rs = ps.executeQuery();
            programProcedures = getDataFromResultSet(rs);

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(ProgramProcedureDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return programProcedures;
    }
    
    public ArrayList<ProgramProcedure> getListOfProgramProceduresForProgramID(int programID) {
        ArrayList<ProgramProcedure> programProcedures = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + ProgramProcedure.TABLE_NAME + " WHERE " + ProgramProcedure.COLUMN_PROGRAMPLANID + " = ?");
            ps.setInt(1, programID);

            ResultSet rs = ps.executeQuery();
            programProcedures = getDataFromResultSet(rs);

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(ProgramProcedureDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return programProcedures;
    }

    private ArrayList<ProgramProcedure> getDataFromResultSet(ResultSet rs) throws SQLException {
        ArrayList<ProgramProcedure> programProcedures = new ArrayList<>();
        while (rs.next()) {
            ProgramProcedure programProcedure = new ProgramProcedure();
            programProcedure.setActivity(rs.getString(ProgramProcedure.COLUMN_ACTIVITY));
            programProcedure.setPhase(rs.getString(ProgramProcedure.COLUMN_PHASE));
            programProcedure.setProcedureNo(rs.getInt(ProgramProcedure.COLUMN_PROCEDURENO));
            programProcedure.setProgramPlanID(rs.getInt(ProgramProcedure.COLUMN_PROGRAMPLANID));
            programProcedures.add(programProcedure);
        }
        return programProcedures;
    }
}
