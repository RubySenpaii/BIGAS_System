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
import object.ProgramRequest;

/**
 *
 * @author RubySenpaii
 */
public class ProgramRequestDAO {
    
    public boolean addProgramRequest(ProgramRequest programRequest) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("INSERT INTO " + ProgramRequest.TABLE_NAME + " "
                    + "(" + ProgramRequest.COLUMN_DATEREQUESTED + ", " + ProgramRequest.COLUMN_PROGRAMPLANID + ", " + ProgramRequest.COLUMN_REQUESTBY + ", " 
                    + ProgramRequest.COLUMN_REQUESTDETAIL + ", " + ProgramRequest.COLUMN_REQUESTID + ", " + ProgramRequest.COLUMN_MUNICIPALITYID + ", " 
                    + ProgramRequest.COLUMN_REQUESTSTATUS + ") "
                    + "VALUES(?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, programRequest.getDateRequested());
            ps.setInt(2, programRequest.getProgramPlanID());
            ps.setString(3, programRequest.getRequestBy());
            ps.setString(4, programRequest.getRequestDetail());
            ps.setInt(5, programRequest.getRequestID());
            ps.setInt(6, programRequest.getMunicipalityID());
            ps.setString(7, programRequest.getRequestStatus());

            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(ProgramRequestDAO.class.getName()).log(Level.SEVERE, null, x);
            return false;
        }
        return true;
    }
    
    public boolean updateProgramRequest(ProgramRequest programRequest) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            
            PreparedStatement ps = conn.prepareStatement("UPDATE " + ProgramRequest.TABLE_NAME + 
                    " SET " + ProgramRequest.COLUMN_DATEREQUESTED + " = ?, " + ProgramRequest.COLUMN_PROGRAMPLANID + " = ?, " + ProgramRequest.COLUMN_REQUESTBY + " = ?, "
                    + ProgramRequest.COLUMN_REQUESTDETAIL + " = ?, " + ProgramRequest.COLUMN_REQUESTID + " = ?, " + ProgramRequest.COLUMN_MUNICIPALITYID + " = ?, "
                    + ProgramRequest.COLUMN_REQUESTSTATUS + " = ? "
                    + "WHERE " + ProgramRequest.COLUMN_REQUESTID + " = ?");
            ps.setString(1, programRequest.getDateRequested());
            ps.setInt(2, programRequest.getProgramPlanID());
            ps.setString(3, programRequest.getRequestBy());
            ps.setString(4, programRequest.getRequestDetail());
            ps.setInt(5, programRequest.getRequestID());
            ps.setInt(6, programRequest.getMunicipalityID());
            ps.setString(7, programRequest.getRequestStatus());
            ps.setInt(8, programRequest.getRequestID());
            
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProgramRequestDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } return true;
    }

    public ArrayList<ProgramRequest> getListOfProgramRequests() {
        ArrayList<ProgramRequest> programRequests = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + ProgramRequest.TABLE_NAME);

            ResultSet rs = ps.executeQuery();
            programRequests = getDataFromResultSet(rs);

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(ProgramRequestDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return programRequests;
    }
    
    public ArrayList<ProgramRequest> getListOfProgramRequestsInMunicipality(int municipalityID) {
        ArrayList<ProgramRequest> programRequests = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + ProgramRequest.TABLE_NAME + " WHERE " + ProgramRequest.COLUMN_MUNICIPALITYID + " = ?");
            ps.setInt(1, municipalityID);

            ResultSet rs = ps.executeQuery();
            programRequests = getDataFromResultSet(rs);

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(ProgramRequestDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return programRequests;
    }

    private ArrayList<ProgramRequest> getDataFromResultSet(ResultSet rs) throws SQLException {
        ArrayList<ProgramRequest> programRequests = new ArrayList<>();
        while (rs.next()) {
            ProgramRequest programRequest = new ProgramRequest();
            programRequest.setDateRequested(rs.getString(ProgramRequest.COLUMN_DATEREQUESTED));
            programRequest.setProgramPlanID(rs.getInt(ProgramRequest.COLUMN_PROGRAMPLANID));
            programRequest.setRequestBy(rs.getString(ProgramRequest.COLUMN_REQUESTBY));
            programRequest.setRequestDetail(rs.getString(ProgramRequest.COLUMN_REQUESTDETAIL));
            programRequest.setRequestID(rs.getInt(ProgramRequest.COLUMN_REQUESTID));
            programRequest.setRequestStatus(rs.getString(ProgramRequest.COLUMN_REQUESTSTATUS));
            programRequest.setMunicipalityID(rs.getInt(ProgramRequest.COLUMN_MUNICIPALITYID));
            programRequests.add(programRequest);
        }
        return programRequests;
    }
}
