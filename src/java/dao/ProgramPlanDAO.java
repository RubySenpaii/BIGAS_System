/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import db.DBConnectionFactory;
import extra.Calculator;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import object.ProgramPlan;

/**
 *
 * @author RubySenpaii
 */
public class ProgramPlanDAO {

    public boolean addProgramPlan(ProgramPlan programPlan) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("INSERT INTO " + ProgramPlan.TABLE_NAME + " "
                    + "(" + ProgramPlan.COLUMN_DESCRIPTON + ", " + ProgramPlan.COLUMN_PROGRAMNAME + ", " + ProgramPlan.COLUMN_PROBLEMID + ", "
                    + ProgramPlan.COLUMN_PROGRAMPLANID + ", " + ProgramPlan.COLUMN_TYPE + "," + ProgramPlan.COLUMN_FLAG + ") "
                    + "VALUES(?, ?, ?, ?, ?, ?)");
            ps.setString(1, programPlan.getDescription());
            ps.setString(2, programPlan.getProgramName());
            ps.setInt(3, programPlan.getProblemID());
            ps.setInt(4, programPlan.getProgramPlanID());
            ps.setString(5, programPlan.getType());
            ps.setInt(6, programPlan.getFlag());

            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(ProgramPlanDAO.class.getName()).log(Level.SEVERE, null, x);
            return false;
        }
        return true;
    }

    public ArrayList<ProgramPlan> getListOfProgramPlans() {
        ArrayList<ProgramPlan> programPlans = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + ProgramPlan.TABLE_NAME + " WHERE ProgramPlanID != -1");

            ResultSet rs = ps.executeQuery();
            programPlans = getDataFromResultSet(rs);

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(ProgramPlanDAO.class.getName()).log(Level.SEVERE, null, x);
        }

        for (int a = 0; a < programPlans.size(); a++) {
            Calculator calculator = new Calculator();
            double deployedResult = calculator.getProgramRating(programPlans.get(a).getProgramPlanID());
            String effectivity = calculator.getEffectivityResult(deployedResult);
            System.out.println(programPlans.get(a).getProgramName() + "-" + effectivity + "-" + deployedResult);
            programPlans.get(a).setEffectivityStatus(effectivity);
            programPlans.get(a).setEffectivityRating(deployedResult);
        }
        return programPlans;
    }

    public ProgramPlan getProgramPlanDetail(String programName) {
        ArrayList<ProgramPlan> programPlans = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + ProgramPlan.TABLE_NAME + " WHERE " + ProgramPlan.COLUMN_PROGRAMNAME + " = ?");
            ps.setString(1, programName);

            ResultSet rs = ps.executeQuery();
            programPlans = getDataFromResultSet(rs);

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(ProgramPlanDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return programPlans.get(0);
    }

    public ProgramPlan getProgramPlanDetail(int programPlanID) {
        ArrayList<ProgramPlan> programPlans = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + ProgramPlan.TABLE_NAME + " WHERE " + ProgramPlan.COLUMN_PROGRAMPLANID + " = ?");
            ps.setInt(1, programPlanID);

            ResultSet rs = ps.executeQuery();
            programPlans = getDataFromResultSet(rs);

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(ProgramPlanDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return programPlans.get(0);
    }

    public ArrayList<ProgramPlan> getListOfProgramPlansForProblem(int problemID) {
        ArrayList<ProgramPlan> programPlans = new ArrayList<>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM ProgramPlan WHERE ProblemID = ?");
            ps.setInt(1, problemID);

            ResultSet rs = ps.executeQuery();
            programPlans = getDataFromResultSet(rs);

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(ProgramPlanDAO.class.getName()).log(Level.SEVERE, null, x);
        }
        return programPlans;
    }

    private ArrayList<ProgramPlan> getDataFromResultSet(ResultSet rs) throws SQLException {
        ArrayList<ProgramPlan> programPlans = new ArrayList<>();
        while (rs.next()) {
            ProgramPlan programPlan = new ProgramPlan();
            programPlan.setDescription(rs.getString(ProgramPlan.COLUMN_DESCRIPTON));
            programPlan.setProgramName(rs.getString(ProgramPlan.COLUMN_PROGRAMNAME));
            programPlan.setProblemID(rs.getInt(ProgramPlan.COLUMN_PROBLEMID));
            programPlan.setProgramPlanID(rs.getInt(ProgramPlan.COLUMN_PROGRAMPLANID));
            programPlan.setType(rs.getString(ProgramPlan.COLUMN_TYPE));
            programPlan.setFlag(rs.getInt(ProgramPlan.COLUMN_FLAG));
            programPlan.setSurveyForm(rs.getInt("SurveyForm"));
            programPlans.add(programPlan);
        }
        return programPlans;
    }
}
