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
import object.Fertilizer;
import object.Plot;

/**
 *
 * @author RubySenpaii
 */
public class FertilizerDAO {
    
    public boolean addFertilizer(Fertilizer fertilizer ){
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("INSERT INTO " + Fertilizer.TABLE_NAME + " "
                    + "(" + Fertilizer.COLUMN_FERTILZIERNAME + ") "
                    + "VALUES(?)");
            ps.setString(1, fertilizer.getFertilizerName());

            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(FertilizerDAO.class.getName()).log(Level.SEVERE, null, x);
            return false;
        }
        return true;
    }

    public ArrayList<Fertilizer> getListOfFertilizers() {
        ArrayList<Fertilizer> fertilizers = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + Fertilizer.TABLE_NAME);

            ResultSet rs = ps.executeQuery();
            fertilizers = getDataFromResultSet(rs);

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(FertilizerDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return fertilizers;
    }

    private ArrayList<Fertilizer> getDataFromResultSet(ResultSet rs) throws SQLException {
        ArrayList<Fertilizer> fertilizers = new ArrayList<>();
        while (rs.next()) {
            Fertilizer fertilizer = new Fertilizer();
            fertilizer.setFertilizerName(rs.getString(Fertilizer.COLUMN_FERTILZIERNAME));
            fertilizers.add(fertilizer);
        }
        return fertilizers;
    }
}
