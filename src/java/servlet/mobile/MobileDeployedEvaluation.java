/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.mobile;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dao.DeployedEvaluationDAO;
import dao.FarmerDAO;
import db.DBConnectionFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import object.DeployedEvaluation;
import object.Farmer;

/**
 *
 * @author RubySenpaii
 */
public class MobileDeployedEvaluation extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<DeployedEvaluation> deployedEvaluations = new Gson().fromJson(request.getParameter("deployedEvaluations"), new TypeToken<List<DeployedEvaluation>>() {
        }.getType());
        System.out.println(deployedEvaluations.size() + " DeployedEvaluation inputs from mobile upload");
        
        int originalSize = new DeployedEvaluationDAO().getListOfProgramEvaluations().size();
        int count = 0;
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            conn.setAutoCommit(false);

            PreparedStatement addPS = conn.prepareStatement("INSERT INTO " + DeployedEvaluation.TABLE_NAME + " "
                    + "(" + DeployedEvaluation.COLUMN_DEPLOYEDID + ", " + DeployedEvaluation.COLUMN_EVALUATIONVALUES + ", "
                    + DeployedEvaluation.COLUMN_FEEDBACK + ", " + DeployedEvaluation.COLUMN_RESPONDENTNAME + ") "
                    + "VALUES(?, ?, ?, ?)");
            //PreparedStatement updatePS = conn.prepareStatement("");
            for (int a = 0; a < deployedEvaluations.size(); a++) {
                //if (a < originalSize) {
                    //cannot update
                //} else {
                    addPS.setInt(1, deployedEvaluations.get(a).getDeployedID());
                    addPS.setString(2, deployedEvaluations.get(a).getEvaluationValues());
                    addPS.setString(3, deployedEvaluations.get(a).getFeedback().substring(0, 30));
                    addPS.setString(4, deployedEvaluations.get(a).getRespondentName());
                    addPS.addBatch();
                //}
            }

            int[] adds = addPS.executeBatch();
            //int[] updates = updatePS.executeBatch();
            count = adds.length; //+ updates.length;
            System.out.println("added evaluation rows: " + adds.length);
            //System.out.println("updated evaluation rows: " + updates.length);
            conn.commit();
            addPS.close();
            //updatePS.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(MobileFarmer.class.getName()).log(Level.SEVERE, null, x);
        } catch (NullPointerException x) {
            System.err.println("nullpointer");
        }
        System.out.println("eval rows affected: " + count);

        response.getWriter().write(new Gson().toJson("done download"));
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
