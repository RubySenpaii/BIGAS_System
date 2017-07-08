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
import object.ProgramSurvey;

/**
 *
 * @author RubySenpaii
 */
public class ProgramSurveyDAO {
    
    public boolean addProgramSurvey(ArrayList<ProgramSurvey> surveys){
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("INSERT INTO " + ProgramSurvey.TABLE_NAME + " "
                    + "(" + ProgramSurvey.COLUMN_QUESTION + ", " + ProgramSurvey.COLUMN_QUESTIONNO + ", " + ProgramSurvey.COLUMN_SURVEYID + ", " 
                    + ProgramSurvey.COLUMN_TYPE + ") "
                    + "VALUES(?, ?, ?, ?)");
            
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(ProgramSurveyDAO.class.getName()).log(Level.SEVERE, null, x);
            return false;
        }
        return true;
    }

    public ArrayList<ProgramSurvey> getListOfProgramSurveys() {
        ArrayList<ProgramSurvey> surveys = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + ProgramSurvey.TABLE_NAME);

            ResultSet rs = ps.executeQuery();
            surveys = getDataFromResultSet(rs);

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(FarmerDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return surveys;
    }
    
    public ArrayList<ProgramSurvey> getListOfProgramSurveyName() {
        ArrayList<ProgramSurvey> surveys = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM ProgramSurvey PS WHERE PS.SurveyID != -1 GROUP BY PS.Type");

            ResultSet rs = ps.executeQuery();
            surveys = getDataFromResultSet(rs);

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(FarmerDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return surveys;
    }
    
    public ArrayList<ProgramSurvey> getProgramSurveyQuestions(String type) {
        ArrayList<ProgramSurvey> surveys = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM ProgramSurvey PS WHERE PS.Type = ?");
            ps.setString(1, type);

            ResultSet rs = ps.executeQuery();
            surveys = getDataFromResultSet(rs);

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(ProgramSurveyDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return surveys;
    }
    
    public boolean doesSurveyExist(String type) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM ProgramSurvey PS WHERE PS.Type = ?");
            ps.setString(1, type);

            ResultSet rs = ps.executeQuery();
            ArrayList<ProgramSurvey> surveys = getDataFromResultSet(rs);

            rs.close();
            ps.close();
            conn.close();
            
            if (surveys.size() > 0) {
                return true;
            }
        } catch (SQLException x) {
            Logger.getLogger(ProgramSurveyDAO.class.getName()).log(Level.SEVERE, null, x);
        } catch (NullPointerException nullEx) {
            System.out.println("null pointer in survey exist");
            return false;
        }
        return false;
    }

    private ArrayList<ProgramSurvey> getDataFromResultSet(ResultSet rs) throws SQLException {
        ArrayList<ProgramSurvey> surveys = new ArrayList<>();
        while (rs.next()) {
            ProgramSurvey survey = new ProgramSurvey();
            survey.setQuestion(rs.getString(ProgramSurvey.COLUMN_QUESTION));
            survey.setQuestionNo(rs.getInt(ProgramSurvey.COLUMN_QUESTIONNO));
            survey.setSurveyID(rs.getInt(ProgramSurvey.COLUMN_SURVEYID));
            survey.setType(rs.getString(ProgramSurvey.COLUMN_TYPE));
            surveys.add(survey);
        }
        return surveys;
    }
}
