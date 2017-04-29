/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package delete;

import db.DBConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author RubySenpaii
 */
public class FarmAssignmentDAO {

    public boolean addFarmAssignment(FarmAssignment farmAssignment) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("INSERT INTO " + FarmAssignment.TABLE_NAME + " "
                    + "(" + FarmAssignment.COLUMN_ASSIGNEDFARM + ", " + FarmAssignment.COLUMN_FLAG + ", " + FarmAssignment.COLUMN_TECHNICIAN + ") "
                    + "VALUES(?, ?, ?)");
            ps.setInt(1, farmAssignment.getAssignedFarm());
            ps.setInt(2, farmAssignment.getFlag());
            ps.setInt(3, farmAssignment.getTechnician());

            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(FarmAssignmentDAO.class.getName()).log(Level.SEVERE, null, x);
            return false;
        }
        return true;
    }

    public ArrayList<FarmAssignment> getListOfFarmAssignedTo(int employeeID) {
        ArrayList<FarmAssignment> farmAssignments = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + FarmAssignment.TABLE_NAME 
                    + " WHERE " + FarmAssignment.COLUMN_TECHNICIAN + " = ? AND " + FarmAssignment.COLUMN_FLAG + " = 1");
            ps.setInt(1, employeeID);

            ResultSet rs = ps.executeQuery();
            farmAssignments = getDataFromResultSet(rs);

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(FarmAssignmentDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return farmAssignments;
    }

    public ArrayList<FarmAssignment> getListOfFarmAssignments() {
        ArrayList<FarmAssignment> farmAssignments = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + FarmAssignment.TABLE_NAME);

            ResultSet rs = ps.executeQuery();
            farmAssignments = getDataFromResultSet(rs);

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(FarmAssignmentDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return farmAssignments;
    }

    private ArrayList<FarmAssignment> getDataFromResultSet(ResultSet rs) throws SQLException {
        ArrayList<FarmAssignment> farmAssignments = new ArrayList<>();
        while (rs.next()) {
            FarmAssignment farmAssignment = new FarmAssignment();
            farmAssignment.setAssignedFarm(rs.getInt(FarmAssignment.COLUMN_ASSIGNEDFARM));
            farmAssignment.setFlag(rs.getInt(FarmAssignment.COLUMN_FLAG));
            farmAssignment.setTechnician(rs.getInt(FarmAssignment.COLUMN_TECHNICIAN));
            farmAssignments.add(farmAssignment);
        }
        return farmAssignments;
    }
}
