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
import object.FertilizerRecommendation;

/**
 *
 * @author RubySenpaii
 */
public class FertilizerRecommendationDAO {

    public boolean addFertilizerRecommendation(FertilizerRecommendation fertilizerRecommendation) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("INSERT INTO " + FertilizerRecommendation.TABLE_NAME + " "
                    + "(" + FertilizerRecommendation.COLUMN_APPLICATIONMETHOD + ", " + FertilizerRecommendation.COLUMN_FERTILIZERID + ", "
                    + FertilizerRecommendation.COLUMN_SEASON + ", " + FertilizerRecommendation.COLUMN_SOILANALYSISNUMBER + ") "
                    + "VALUES(?, ?, ?, ?)");
            ps.setString(1, fertilizerRecommendation.getApplicationMethod());
            ps.setString(2, fertilizerRecommendation.getFertilizerName());
            ps.setString(3, fertilizerRecommendation.getSeason());
            ps.setInt(4, fertilizerRecommendation.getSoilAnalysisNumber());

            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(FertilizerRecommendationDAO.class.getName()).log(Level.SEVERE, null, x);
            return false;
        }
        return true;
    }

    public ArrayList<FertilizerRecommendation> getListOfFertilizerRecommendations() {
        ArrayList<FertilizerRecommendation> fertilizerRecommendations = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + FertilizerRecommendation.TABLE_NAME);

            ResultSet rs = ps.executeQuery();
            fertilizerRecommendations = getDataFromResultSet(rs);

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(FertilizerRecommendationDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return fertilizerRecommendations;
    }

    private ArrayList<FertilizerRecommendation> getDataFromResultSet(ResultSet rs) throws SQLException {
        ArrayList<FertilizerRecommendation> fertilizerRecommendations = new ArrayList<>();
        while (rs.next()) {
            FertilizerRecommendation fertilizerRecommendation = new FertilizerRecommendation();
            fertilizerRecommendation.setApplicationMethod(rs.getString(FertilizerRecommendation.COLUMN_APPLICATIONMETHOD));
            fertilizerRecommendation.setFertilizerName(rs.getString(FertilizerRecommendation.COLUMN_FERTILIZERID));
            fertilizerRecommendation.setSeason(rs.getString(FertilizerRecommendation.COLUMN_SEASON));
            fertilizerRecommendation.setSoilAnalysisNumber(rs.getInt(FertilizerRecommendation.COLUMN_SOILANALYSISNUMBER));
            fertilizerRecommendations.add(fertilizerRecommendation);
        }
        return fertilizerRecommendations;
    }
}
