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
import object.SoilAnalysis;

/**
 *
 * @author RubySenpaii
 */
public class SoilAnalysisDAO {
    
    public boolean addSoilAnalysis(SoilAnalysis soilAnalysis) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("INSERT INTO " + SoilAnalysis.TABLE_NAME + " "
                    + "(" + SoilAnalysis.COLUMN_CERTIFIEDBY + ", " + SoilAnalysis.COLUMN_DATEFINISHED + ", " + SoilAnalysis.COLUMN_DATESUBMITTED + ", " 
                    + SoilAnalysis.COLUMN_FARMID + ", " + SoilAnalysis.COLUMN_NITROGEN + ", " + SoilAnalysis.COLUMN_PHLEVEL + ", " 
                    + SoilAnalysis.COLUMN_PHOSPHOROUS + ", " + SoilAnalysis.COLUMN_POTASSIUM + ", " + SoilAnalysis.COLUMN_SOILANALYSISNUMBER + ", "
                    + SoilAnalysis.COLUMN_TEXTURE + ", " + SoilAnalysis.COLUMN_VALIDUNTIL + ", " + SoilAnalysis.COLUMN_ZINC + ") "
                    + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, soilAnalysis.getCertifiedBy());
            ps.setString(2, soilAnalysis.getDateFinished());
            ps.setString(3, soilAnalysis.getDateSubmitted());
            ps.setInt(4, soilAnalysis.getFarmID());
            ps.setDouble(5, soilAnalysis.getNitrogen());
            ps.setDouble(6, soilAnalysis.getpHLevel());
            ps.setDouble(7, soilAnalysis.getPhosphorous());
            ps.setDouble(8, soilAnalysis.getPotassium());
            ps.setInt(9, soilAnalysis.getSoilAnalysisNumber());
            ps.setDouble(10, soilAnalysis.getTexture());
            ps.setString(11, soilAnalysis.getValidUntil());
            ps.setDouble(12, soilAnalysis.getZinc());

            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(SoilAnalysisDAO.class.getName()).log(Level.SEVERE, null, x);
            return false;
        }
        return true;
    }

    public ArrayList<SoilAnalysis> getListOfSoilAnalyses() {
        ArrayList<SoilAnalysis> soilAnalyses = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + SoilAnalysis.TABLE_NAME);

            ResultSet rs = ps.executeQuery();
            soilAnalyses = getDataFromResultSet(rs);

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(SoilAnalysisDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return soilAnalyses;
    }

    private ArrayList<SoilAnalysis> getDataFromResultSet(ResultSet rs) throws SQLException {
        ArrayList<SoilAnalysis> soilAnalyses = new ArrayList<>();
        while (rs.next()) {
            SoilAnalysis soilAnalysis = new SoilAnalysis();
            soilAnalysis.setCertifiedBy(rs.getString(SoilAnalysis.COLUMN_CERTIFIEDBY));
            soilAnalysis.setDateFinished(rs.getString(SoilAnalysis.COLUMN_DATEFINISHED));
            soilAnalysis.setDateSubmitted(rs.getString(SoilAnalysis.COLUMN_DATESUBMITTED));
            soilAnalysis.setFarmID(rs.getInt(SoilAnalysis.COLUMN_FARMID));
            soilAnalysis.setNitrogen(rs.getDouble(SoilAnalysis.COLUMN_NITROGEN));
            soilAnalysis.setPhosphorous(rs.getDouble(SoilAnalysis.COLUMN_PHOSPHOROUS));
            soilAnalysis.setPotassium(rs.getDouble(SoilAnalysis.COLUMN_POTASSIUM));
            soilAnalysis.setSoilAnalysisNumber(rs.getInt(SoilAnalysis.COLUMN_SOILANALYSISNUMBER));
            soilAnalysis.setTexture(rs.getDouble(SoilAnalysis.COLUMN_TEXTURE));
            soilAnalysis.setValidUntil(rs.getString(SoilAnalysis.COLUMN_VALIDUNTIL));
            soilAnalysis.setZinc(rs.getDouble(SoilAnalysis.COLUMN_ZINC));
            soilAnalysis.setpHLevel(rs.getDouble(SoilAnalysis.COLUMN_PHLEVEL));
            soilAnalyses.add(soilAnalysis);
        }
        return soilAnalyses;
    }
}
