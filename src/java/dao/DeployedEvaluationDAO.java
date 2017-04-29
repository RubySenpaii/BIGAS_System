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
import object.DeployedEvaluation;

/**
 *
 * @author RubySenpaii
 */
public class DeployedEvaluationDAO {
    
    public boolean addDeployedEvaluation(DeployedEvaluation evaluation ){
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("INSERT INTO " + DeployedEvaluation.TABLE_NAME + " "
                    + "(" + DeployedEvaluation.COLUMN_DEPLOYEDID + ", " + DeployedEvaluation.COLUMN_EVALUATIONVALUES + ", " + DeployedEvaluation.COLUMN_RESPONDENTNAME + ") "
                    + "VALUES(?, ?, ?)");
            ps.setInt(1, evaluation.getDeployedID());
            ps.setString(2, evaluation.getEvaluationValues());
            ps.setString(3, evaluation.getRespondentName());

            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(DeployedEvaluationDAO.class.getName()).log(Level.SEVERE, null, x);
            return false;
        }
        return true;
    }

    public ArrayList<DeployedEvaluation> getListOfProgramEvaluations() {
        ArrayList<DeployedEvaluation> programEvaluations = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + DeployedEvaluation.TABLE_NAME);

            ResultSet rs = ps.executeQuery();
            programEvaluations = getDataFromResultSet(rs);

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(DeployedEvaluationDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return programEvaluations;
    }
    
    public ArrayList<DeployedEvaluation> getListOfProgramEvaluationsOnDeployedID(int deployedID) {
        ArrayList<DeployedEvaluation> evaluations = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + DeployedEvaluation.TABLE_NAME + "  WHERE " + DeployedEvaluation.COLUMN_DEPLOYEDID + " = ?");
            ps.setInt(1, deployedID);

            ResultSet rs = ps.executeQuery();
            evaluations = getDataFromResultSet(rs);

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(DeployedEvaluationDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return evaluations;
    }

    private ArrayList<DeployedEvaluation> getDataFromResultSet(ResultSet rs) throws SQLException {
        ArrayList<DeployedEvaluation> evaluations = new ArrayList<>();
        while (rs.next()) {
            DeployedEvaluation evaluation = new DeployedEvaluation();
            evaluation.setDeployedID(rs.getInt(DeployedEvaluation.COLUMN_DEPLOYEDID));
            evaluation.setEvaluationValues(rs.getString(DeployedEvaluation.COLUMN_EVALUATIONVALUES));
            evaluation.setRespondentName(rs.getString(DeployedEvaluation.COLUMN_RESPONDENTNAME));
            evaluations.add(evaluation);
        }
        return evaluations;
    }
}
