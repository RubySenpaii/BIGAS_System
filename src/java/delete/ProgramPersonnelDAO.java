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
public class ProgramPersonnelDAO {
    
    public boolean addProgramPersonnel(ProgramPersonnel programPersonnel) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("INSERT INTO " + ProgramPersonnel.TABLE_NAME + " "
                    + "(" + ProgramPersonnel.COLUMN_DEPLOYEDID + ", " + ProgramPersonnel.COLUMN_EMPLOYEEID + ", "
                    + ProgramPersonnel.COLUMN_ROLE + ") "
                    + "VALUES(?, ?, ?)");
            ps.setInt(1, programPersonnel.getDeployedID());
            ps.setInt(2, programPersonnel.getEmployeeID());
            ps.setString(3, programPersonnel.getRole());

            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(ProgramPersonnelDAO.class.getName()).log(Level.SEVERE, null, x);
            return false;
        }
        return true;
    }

    public ArrayList<ProgramPersonnel> getListOfProgramPersonnels() {
        ArrayList<ProgramPersonnel> programPersonnels = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + ProgramPersonnel.TABLE_NAME);

            ResultSet rs = ps.executeQuery();
            programPersonnels = getDataFromResultSet(rs);

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(ProgramPersonnelDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return programPersonnels;
    }

    private ArrayList<ProgramPersonnel> getDataFromResultSet(ResultSet rs) throws SQLException {
        ArrayList<ProgramPersonnel> programPersonnels = new ArrayList<>();
        while (rs.next()) {
            ProgramPersonnel programPersonnel = new ProgramPersonnel();
            programPersonnel.setDeployedID(rs.getInt(ProgramPersonnel.COLUMN_DEPLOYEDID));
            programPersonnel.setEmployeeID(rs.getInt(ProgramPersonnel.COLUMN_EMPLOYEEID));
            programPersonnel.setRole(rs.getString(ProgramPersonnel.COLUMN_ROLE));
            programPersonnels.add(programPersonnel);
        }
        return programPersonnels;
    }
}
