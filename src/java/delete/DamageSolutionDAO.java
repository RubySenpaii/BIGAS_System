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
import object.Plot;

/**
 *
 * @author RubySenpaii
 */
public class DamageSolutionDAO {
    
    public boolean reportDamageSolution(DamageSolution damageSolution){
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("INSERT INTO " + DamageSolution.TABLE_NAME + " "
                    + "(" + DamageSolution.COLUMN_DAMAGEINCIDENTID + ", " + DamageSolution.COLUMN_DEPLOYEDID +  ") "
                    + "VALUES(?, ?)");
            ps.setInt(1, damageSolution.getDamageIncidentID());
            ps.setInt(2, damageSolution.getDeployedID());

            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(DamageSolutionDAO.class.getName()).log(Level.SEVERE, null, x);
            return false;
        }
        return true;
    }

    public ArrayList<DamageSolution> getListOfDamageSolutions() {
        ArrayList<DamageSolution> damageSolutions = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + DamageSolution.TABLE_NAME);

            ResultSet rs = ps.executeQuery();
            damageSolutions = getDataFromResultSet(rs);

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(DamageSolutionDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return damageSolutions;
    }
    
    public ArrayList<DamageSolution> getListOfDamageSolutionsWithIncidentID(int incidentID) {
        ArrayList<DamageSolution> damageSolutions = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + DamageSolution.TABLE_NAME + " WHERE " + DamageSolution.COLUMN_DAMAGEINCIDENTID + " = ?");
            ps.setInt(1, incidentID);

            ResultSet rs = ps.executeQuery();
            damageSolutions = getDataFromResultSet(rs);

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(DamageSolutionDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return damageSolutions;
    }
    
    public ArrayList<DamageSolution> getListOfDamageSolutionsWithDeployedID(int deployedID) {
        ArrayList<DamageSolution> damageSolutions = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + DamageSolution.TABLE_NAME + " WHERE " + DamageSolution.COLUMN_DEPLOYEDID + " = ?");
            ps.setInt(1, deployedID);

            ResultSet rs = ps.executeQuery();
            damageSolutions = getDataFromResultSet(rs);

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(DamageSolutionDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return damageSolutions;
    }

    private ArrayList<DamageSolution> getDataFromResultSet(ResultSet rs) throws SQLException {
        ArrayList<DamageSolution> damageSolutions = new ArrayList<>();
        while (rs.next()) {
            DamageSolution damageSolution = new DamageSolution();
            damageSolution.setDamageIncidentID(rs.getInt(DamageSolution.COLUMN_DAMAGEINCIDENTID));
            damageSolution.setDeployedID(rs.getInt(DamageSolution.COLUMN_DEPLOYEDID));
            damageSolutions.add(damageSolution);
        }
        return damageSolutions;
    }
}
