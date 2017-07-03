/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.mobile;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dao.WeeklyPlantingReportDAO;
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
import object.WeeklyPlantingReport;

/**
 *
 * @author RubySenpaii
 */
public class MobileWeeklyPlantingReport extends HttpServlet {

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
        ArrayList<WeeklyPlantingReport> weeklyPlantingReports = new Gson().fromJson(request.getParameter("weeklyPlantingReports"), new TypeToken<List<WeeklyPlantingReport>>() {
        }.getType());
        System.out.println(weeklyPlantingReports.size() + " weeklyPlantingReport inputs form mobile upload");

        int originalSize = new WeeklyPlantingReportDAO().getListOfWeeklyPlantingReports().size();
        int count = 0;
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            conn.setAutoCommit(false);

            PreparedStatement updatePS = conn.prepareStatement("");
            PreparedStatement addPS = conn.prepareStatement("INSERT INTO " + WeeklyPlantingReport.TABLE_NAME + " "
                            + "(" + WeeklyPlantingReport.COLUMN_CROPSTAGE + ", " + WeeklyPlantingReport.COLUMN_DATEREPORTED + ", " + WeeklyPlantingReport.COLUMN_HEIGHT + ", "
                            + WeeklyPlantingReport.COLUMN_IMAGE + ", " + WeeklyPlantingReport.COLUMN_PLANTINGREPORTID + ", " + WeeklyPlantingReport.COLUMN_AREAPLANTED + ", "
                            + WeeklyPlantingReport.COLUMN_WATERLEVEL + ") "
                            + "VALUES(?, ?, ?, ?, ?, ?, ?)");
            for (int a = 0; a < weeklyPlantingReports.size(); a++) {
                if (a <= originalSize) {
                    //do nothing
                    updatePS.addBatch();
                } else {
                    addPS.setString(1, weeklyPlantingReports.get(a).getCropStage());
                    addPS.setString(2, weeklyPlantingReports.get(a).getDateReported());
                    addPS.setDouble(3, weeklyPlantingReports.get(a).getHeight());
                    addPS.setString(4, weeklyPlantingReports.get(a).getImage());
                    addPS.setInt(5, weeklyPlantingReports.get(a).getPlantingReportID());
                    addPS.setDouble(6, weeklyPlantingReports.get(a).getAreaPlanted());
                    addPS.setDouble(7, weeklyPlantingReports.get(a).getWaterLevel());
                    addPS.addBatch();
                }
            }

            int[] adds = addPS.executeBatch();
            //int[] updates = updatePS.executeBatch();
            count = adds.length; //+ updates.length;
            conn.commit();
            System.out.println("added weeklyPlantingReport rows: " + adds.length);
            //System.out.println("updated weeklyPlantingReport rows: " + updates.length);
            addPS.close();
            //updatePS.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(MobileWeeklyPlantingReport.class.getName()).log(Level.SEVERE, null, x);
        }
        System.out.println("weeklyPlantingReport rows affected: " + count);

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
