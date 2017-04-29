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
import object.NutrientRecommendation;

/**
 *
 * @author RubySenpaii
 */
public class NutrientRecommendationDAO {
    
    public boolean addNutrientRecommendation(NutrientRecommendation fertilizerRecommendation) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("INSERT INTO " + NutrientRecommendation.TABLE_NAME + " "
                    + "(" + NutrientRecommendation.COLUMN_NITROGEN + ", " + NutrientRecommendation.COLUMN_PHOSPHOROUS + ", " 
                    + NutrientRecommendation.COLUMN_POTASSIUM + ", " + NutrientRecommendation.COLUMN_SEASON + ", " 
                    + NutrientRecommendation.COLUMN_SOILANALYSISNUMBER + ") "
                    + "VALUES(?, ?, ?, ?, ?)");
            ps.setDouble(1, fertilizerRecommendation.getNitrogen());
            ps.setDouble(2, fertilizerRecommendation.getPhosphorous());
            ps.setDouble(3, fertilizerRecommendation.getPotassium());
            ps.setString(4, fertilizerRecommendation.getSeason());
            ps.setInt(5, fertilizerRecommendation.getSoilAnalysisNumber());

            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(NutrientRecommendationDAO.class.getName()).log(Level.SEVERE, null, x);
            return false;
        }
        return true;
    }

    public ArrayList<NutrientRecommendation> getListOfNutrientRecommendations() {
        ArrayList<NutrientRecommendation> nutrientRecommendations = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + NutrientRecommendation.TABLE_NAME);

            ResultSet rs = ps.executeQuery();
            nutrientRecommendations = getDataFromResultSet(rs);

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(NutrientRecommendationDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return nutrientRecommendations;
    }

    private ArrayList<NutrientRecommendation> getDataFromResultSet(ResultSet rs) throws SQLException {
        ArrayList<NutrientRecommendation> nutrientRecommendations = new ArrayList<>();
        while (rs.next()) {
            NutrientRecommendation nutrientRecommendation = new NutrientRecommendation();
            nutrientRecommendation.setNitrogen(rs.getDouble(NutrientRecommendation.COLUMN_NITROGEN));
            nutrientRecommendation.setPhosphorous(rs.getDouble(NutrientRecommendation.COLUMN_PHOSPHOROUS));
            nutrientRecommendation.setPotassium(rs.getDouble(NutrientRecommendation.COLUMN_POTASSIUM));
            nutrientRecommendation.setSeason(rs.getString(NutrientRecommendation.COLUMN_SEASON));
            nutrientRecommendation.setSoilAnalysisNumber(rs.getInt(NutrientRecommendation.COLUMN_SOILANALYSISNUMBER));
            nutrientRecommendations.add(nutrientRecommendation);
        }
        return nutrientRecommendations;
    }
}
