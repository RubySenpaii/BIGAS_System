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
import object.Problem;

/**
 *
 * @author RubySenpaii
 */
public class ProblemDAO {
    
    public boolean reportProblem(Problem problem) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("INSERT INTO " + Problem.TABLE_NAME + " "
                    + "(" + Problem.COLUMN_DESCRIPTION + ", " + Problem.COLUMN_IMAGE + ", Type, "
                    + Problem.COLUMN_PROBLEMID + ", " + Problem.COLUMN_PROBLEMNAME + ") "
                    + "VALUES(?, ?, ?, ?)");
            ps.setString(1, problem.getDescription());
            ps.setString(2, problem.getImage());
            ps.setString(2, problem.getType());
            ps.setInt(3, problem.getProblemID());
            ps.setString(4, problem.getProblemName());

            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(ProblemDAO.class.getName()).log(Level.SEVERE, null, x);
            return false;
        }
        return true;
    }
    
    public Problem getProblemWithID(int problemID) {
        ArrayList<Problem> problems = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + Problem.TABLE_NAME + " WHERE " + Problem.COLUMN_PROBLEMID + " = ?");
            ps.setInt(1, problemID);

            ResultSet rs = ps.executeQuery();
            problems = getDataFromResultSet(rs);

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(ProblemDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return problems.get(0);
    }
    
    public Problem getProblemWithName(String problemName) {
        ArrayList<Problem> problems = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + Problem.TABLE_NAME + " WHERE " + Problem.COLUMN_PROBLEMNAME + " = ?");
            ps.setString(1, problemName);

            ResultSet rs = ps.executeQuery();
            problems = getDataFromResultSet(rs);

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(ProblemDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return problems.get(0);
    }

    public ArrayList<Problem> getListOfProblems() {
        ArrayList<Problem> problems = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + Problem.TABLE_NAME);

            ResultSet rs = ps.executeQuery();
            problems = getDataFromResultSet(rs);

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(ProblemDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return problems;
    }
    
    private ArrayList<Problem> getDataFromResultSet(ResultSet rs) throws SQLException {
        ArrayList<Problem> problems = new ArrayList<>();
        while (rs.next()) {
            Problem problem = new Problem();
            problem.setDescription(rs.getString(Problem.COLUMN_DESCRIPTION));
            problem.setImage(rs.getString(Problem.COLUMN_IMAGE));
            problem.setProblemID(rs.getInt(Problem.COLUMN_PROBLEMID));
            problem.setProblemName(rs.getString(Problem.COLUMN_PROBLEMNAME));
            problem.setType(rs.getString("Type"));
            problems.add(problem);
        }
        return problems;
    }
}
