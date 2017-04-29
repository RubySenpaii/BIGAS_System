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
import object.Farm;
import object.Plot;

/**
 *
 * @author RubySenpaii
 */
public class PlotDAO {
    
    public boolean addPlot(Plot plot) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("INSERT INTO " + Plot.TABLE_NAME + " "
                    + "(" + Plot.COLUMN_FARMID + ", " + Plot.COLUMN_PLOTID + ", " + Plot.COLUMN_PLOTNUMBER + ", " 
                    + Plot.COLUMN_PLOTPLANTED + ", " + Plot.COLUMN_PLOTSIZE + ") "
                    + "VALUES(?, ?, ?, ?, ?)");
            ps.setInt(1, plot.getFarmID());
            ps.setInt(2, plot.getPlotID());
            ps.setInt(3, plot.getPlotNumber());
            ps.setInt(4, plot.getPlotPlanted());
            ps.setDouble(5, plot.getPlotSize());

            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(PlotDAO.class.getName()).log(Level.SEVERE, null, x);
            return false;
        }
        return true;
    }
    
    public Plot getPlotDetails(int plotID) {
        ArrayList<Plot> plots = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + Plot.TABLE_NAME + " WHERE " + Plot.COLUMN_PLOTID + " = ?");
            ps.setInt(1, plotID);

            ResultSet rs = ps.executeQuery();
            plots = getDataFromResultSet(rs);

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(PlotDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return plots.get(0);
    }

    public ArrayList<Plot> getListOfPlots() {
        ArrayList<Plot> plots = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + Plot.TABLE_NAME);

            ResultSet rs = ps.executeQuery();
            plots = getDataFromResultSet(rs);

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(PlotDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return plots;
    }
    
    public ArrayList<Plot> getListOfPlotsInFarm(Farm farm) {
        ArrayList<Plot> plots = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + Plot.TABLE_NAME + " WHERE " + Plot.COLUMN_FARMID + " = ?");
            ps.setInt(1, farm.getFarmID());

            ResultSet rs = ps.executeQuery();
            plots = getDataFromResultSet(rs);

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(PlotDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return plots;
    }
    
    public boolean updatePlot(Plot plot) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            
            PreparedStatement ps = conn.prepareStatement("UPDATE " + Plot.TABLE_NAME + 
                    " SET " + Plot.COLUMN_FARMID + " = ?, " + Plot.COLUMN_PLOTID + " = ?, " + Plot.COLUMN_PLOTNUMBER + " = ?, "
                    + Plot.COLUMN_PLOTPLANTED + " = ?, " + Plot.COLUMN_PLOTSIZE + " = ? "
                    + "WHERE " + Plot.COLUMN_PLOTID + " = ?");
            ps.setInt(1, plot.getFarmID());
            ps.setInt(2, plot.getPlotID());
            ps.setInt(3, plot.getPlotNumber());
            ps.setInt(4, plot.getPlotPlanted());
            ps.setDouble(5, plot.getPlotSize());
            ps.setInt(6, plot.getPlotID());
            
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(PlotDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } return true;
    }
    
    public ArrayList<Plot> getListOfPlotsInBarangay(int barangayID) {
        ArrayList<Plot> plots = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT P.* "
                    + "FROM Plot P JOIN Farm F ON P.FarmID = F.FarmID"
                    + "             JOIN Barangay B ON F.BarangayID = B.BarangayID "
                    + "WHERE B.BarangayID = ?");
            ps.setInt(1, barangayID);

            ResultSet rs = ps.executeQuery();
            plots = getDataFromResultSet(rs);

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(PlotDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return plots;
    }

    private ArrayList<Plot> getDataFromResultSet(ResultSet rs) throws SQLException {
        ArrayList<Plot> plots = new ArrayList<>();
        while (rs.next()) {
            Plot plot = new Plot();
            plot.setFarmID(rs.getInt(Plot.COLUMN_FARMID));
            plot.setPlotID(rs.getInt(Plot.COLUMN_PLOTID));
            plot.setPlotNumber(rs.getInt(Plot.COLUMN_PLOTNUMBER));
            plot.setPlotPlanted(rs.getInt(Plot.COLUMN_PLOTPLANTED));
            plot.setPlotSize(rs.getDouble(Plot.COLUMN_PLOTSIZE));
            plots.add(plot);
        }
        return plots;
    }
}
