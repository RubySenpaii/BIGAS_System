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
import object.PlotFertilizer;

/**
 *
 * @author RubySenpaii
 */
public class PlotFertilizerDAO {
    
    public boolean addPlotFertilizer(PlotFertilizer plotFertilizer) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("INSERT INTO " + PlotFertilizer.TABLE_NAME + " "
                    + "(" + PlotFertilizer.COLUMN_DATEAPPLIED + ", " + PlotFertilizer.COLUMN_FERTILIZERNAME + ", " + PlotFertilizer.COLUMN_PLOTID + ") "
                    + "VALUES(?, ?, ?)");
            ps.setString(1, plotFertilizer.getDateApplied());
            ps.setString(2, plotFertilizer.getFertilizerName());
            ps.setInt(3, plotFertilizer.getPlotID());

            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(PlotFertilizerDAO.class.getName()).log(Level.SEVERE, null, x);
            return false;
        }
        return true;
    }
    
    public boolean updatePlotFertilizer(PlotFertilizer plotFertilizer) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            
            PreparedStatement ps = conn.prepareStatement("UPDATE " + PlotFertilizer.TABLE_NAME + 
                    " SET " + PlotFertilizer.COLUMN_DATEAPPLIED + " = ?, " + PlotFertilizer.COLUMN_FERTILIZERNAME + " = ?, " + PlotFertilizer.COLUMN_PLOTID + " = ? "
                    + "WHERE " + PlotFertilizer.COLUMN_PLOTID + " = ? AND " + PlotFertilizer.COLUMN_DATEAPPLIED + " = ?");
            ps.setString(1, plotFertilizer.getDateApplied());
            ps.setString(2, plotFertilizer.getFertilizerName());
            ps.setInt(3, plotFertilizer.getPlotID());
            ps.setInt(4, plotFertilizer.getPlotID());
            ps.setString(5, plotFertilizer.getDateApplied());
            
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(PlotFertilizerDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } return true;
    }

    public ArrayList<PlotFertilizer> getListOfPlotFertilizers() {
        ArrayList<PlotFertilizer> plotFertilizers = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + PlotFertilizer.TABLE_NAME);

            ResultSet rs = ps.executeQuery();
            plotFertilizers = getDataFromResultSet(rs);

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(PlotFertilizerDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return plotFertilizers;
    }

    private ArrayList<PlotFertilizer> getDataFromResultSet(ResultSet rs) throws SQLException {
        ArrayList<PlotFertilizer> plotFertilizers = new ArrayList<>();
        while (rs.next()) {
            PlotFertilizer plotFertilizer = new PlotFertilizer();
            plotFertilizer.setDateApplied(rs.getString(PlotFertilizer.COLUMN_DATEAPPLIED));
            plotFertilizer.setFertilizerName(rs.getString(PlotFertilizer.COLUMN_FERTILIZERNAME));
            plotFertilizer.setPlotID(rs.getInt(PlotFertilizer.COLUMN_PLOTID));
            plotFertilizers.add(plotFertilizer);
        }
        return plotFertilizers;
    }
}
