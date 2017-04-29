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
import object.ProblemSolution;

/**
 *
 * @author RubySenpaii
 */
public class ProblemSolutionDAO {
    
    public boolean addProblemSolution(ProblemSolution problemSolution) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("INSERT INTO " + ProblemSolution.TABLE_NAME + " "
                    + "(" + ProblemSolution.COLUMN_PROBLEMID + ", " + ProblemSolution.COLUMN_PROGRAMID + ") "
                    + "VALUES(?, ?)");
            ps.setInt(1, problemSolution.getProblemID());
            ps.setInt(2, problemSolution.getProgramID());

            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(ProblemSolutionDAO.class.getName()).log(Level.SEVERE, null, x);
            return false;
        }
        return true;
    }

    public ArrayList<ProblemSolution> getListOfProblemSolutions() {
        ArrayList<ProblemSolution> problemSolutions = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + ProblemSolution.TABLE_NAME);

            ResultSet rs = ps.executeQuery();
            problemSolutions = getDataFromResultSet(rs);

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(ProblemSolutionDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return problemSolutions;
    }

    private ArrayList<ProblemSolution> getDataFromResultSet(ResultSet rs) throws SQLException {
        ArrayList<ProblemSolution> problemSolutions = new ArrayList<>();
        while (rs.next()) {
            ProblemSolution problemSolution = new ProblemSolution();
            problemSolution.setProblemID(rs.getInt(ProblemSolution.COLUMN_PROBLEMID));
            problemSolution.setProgramID(rs.getInt(ProblemSolution.COLUMN_PROGRAMID));
            problemSolutions.add(problemSolution);
        }
        return problemSolutions;
    }
}
