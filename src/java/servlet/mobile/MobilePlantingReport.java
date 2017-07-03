/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.mobile;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dao.PlantingReportDAO;
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
import object.PlantingReport;

/**
 *
 * @author RubySenpaii
 */
public class MobilePlantingReport extends HttpServlet {

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
        ArrayList<PlantingReport> plantingReports = new Gson().fromJson(request.getParameter("plantingReports"), new TypeToken<List<PlantingReport>>() {
        }.getType());
        System.out.println(plantingReports.size() + " PlantingReport inputs form mobile upload");

        int originalSize = new PlantingReportDAO().getListOfPlantingReports().size();
        int count = 0;
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            conn.setAutoCommit(false);

            PreparedStatement updatePS = conn.prepareStatement("UPDATE " + PlantingReport.TABLE_NAME
                    + " SET " + PlantingReport.COLUMN_AMOUNTHARVESTED + " = ?, " + PlantingReport.COLUMN_AMOUNTPLANTED + " = ?, " + PlantingReport.COLUMN_HARVESTDAY + " = ?, "
                    + PlantingReport.COLUMN_HARVESTMONTH + " = ?, " + PlantingReport.COLUMN_HARVESTYEAR + " = ?, " + PlantingReport.COLUMN_PLANTEDDAY + " = ?, "
                    + PlantingReport.COLUMN_PLANTEDMONTH + " = ?, " + PlantingReport.COLUMN_PLANTEDYEAR + " = ?, " + PlantingReport.COLUMN_PLANTINGMETHOD + " = ?, "
                    + PlantingReport.COLUMN_PLANTINGREPORTID + " = ?, " + PlantingReport.COLUMN_PLOTID + " = ?, " + PlantingReport.COLUMN_REPORTEDBY + " = ?, "
                    + PlantingReport.COLUMN_SEASON + " = ?, " + PlantingReport.COLUMN_SEEDACQUISITION + " = ?, " + PlantingReport.COLUMN_SEEDTYPE + " = ?, "
                    + PlantingReport.COLUMN_SEEDVARIETY + " = ? "
                    + "WHERE " + PlantingReport.COLUMN_PLANTINGREPORTID + " = ?");
            PreparedStatement addPS = conn.prepareStatement("INSERT INTO " + PlantingReport.TABLE_NAME + " "
                    + "(" + PlantingReport.COLUMN_AMOUNTHARVESTED + ", " + PlantingReport.COLUMN_AMOUNTPLANTED + ", " + PlantingReport.COLUMN_HARVESTDAY + ", "
                    + PlantingReport.COLUMN_HARVESTMONTH + ", " + PlantingReport.COLUMN_HARVESTYEAR + ", " + PlantingReport.COLUMN_PLANTEDDAY + ", "
                    + PlantingReport.COLUMN_PLANTEDMONTH + ", " + PlantingReport.COLUMN_PLANTEDYEAR + ", " + PlantingReport.COLUMN_PLANTINGMETHOD + ", "
                    + PlantingReport.COLUMN_PLANTINGREPORTID + ", " + PlantingReport.COLUMN_PLOTID + ", " + PlantingReport.COLUMN_REPORTEDBY + ", "
                    + PlantingReport.COLUMN_SEASON + ", " + PlantingReport.COLUMN_SEEDACQUISITION + ", " + PlantingReport.COLUMN_SEEDTYPE + ", "
                    + PlantingReport.COLUMN_SEEDVARIETY + ") "
                    + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            for (int a = 0; a < plantingReports.size(); a++) {
                if (a < originalSize) {
                    updatePS.setDouble(1, plantingReports.get(a).getAmountHarvested());
                    updatePS.setDouble(2, plantingReports.get(a).getAmountPlanted());
                    updatePS.setInt(3, plantingReports.get(a).getHarvestDay());
                    updatePS.setInt(4, plantingReports.get(a).getHarvestMonth());
                    updatePS.setInt(5, plantingReports.get(a).getHarvestYear());
                    updatePS.setInt(6, plantingReports.get(a).getPlantedDay());
                    updatePS.setInt(7, plantingReports.get(a).getPlantedMonth());
                    updatePS.setInt(8, plantingReports.get(a).getPlantedYear());
                    updatePS.setString(9, plantingReports.get(a).getPlantingMethod());
                    updatePS.setInt(10, plantingReports.get(a).getPlantingReportID());
                    updatePS.setInt(11, plantingReports.get(a).getPlotID());
                    updatePS.setInt(12, plantingReports.get(a).getReportedBy());
                    updatePS.setString(13, plantingReports.get(a).getSeason());
                    updatePS.setString(14, plantingReports.get(a).getSeedAcquisition());
                    updatePS.setString(15, plantingReports.get(a).getSeedType());
                    updatePS.setString(16, plantingReports.get(a).getSeedVariety());
                    updatePS.setInt(17, plantingReports.get(a).getPlantingReportID());
                    updatePS.addBatch();
                } else {
                    System.out.println(plantingReports.get(a).getPlantingReportID());
                    addPS.setDouble(1, plantingReports.get(a).getAmountHarvested());
                    addPS.setDouble(2, plantingReports.get(a).getAmountPlanted());
                    addPS.setInt(3, plantingReports.get(a).getHarvestDay());
                    addPS.setInt(4, plantingReports.get(a).getHarvestMonth());
                    addPS.setInt(5, plantingReports.get(a).getHarvestYear());
                    addPS.setInt(6, plantingReports.get(a).getPlantedDay());
                    addPS.setInt(7, plantingReports.get(a).getPlantedMonth());
                    addPS.setInt(8, plantingReports.get(a).getPlantedYear());
                    addPS.setString(9, plantingReports.get(a).getPlantingMethod());
                    addPS.setInt(10, plantingReports.get(a).getPlantingReportID());
                    addPS.setInt(11, plantingReports.get(a).getPlotID());
                    addPS.setInt(12, plantingReports.get(a).getReportedBy());
                    addPS.setString(13, plantingReports.get(a).getSeason());
                    addPS.setString(14, plantingReports.get(a).getSeedAcquisition());
                    addPS.setString(15, plantingReports.get(a).getSeedType());
                    addPS.setString(16, plantingReports.get(a).getSeedVariety());
                    addPS.addBatch();
                }
            }

            int[] adds = addPS.executeBatch();
            int[] updates = updatePS.executeBatch();
            conn.commit();
            count = adds.length + updates.length;
            System.out.println("added plantingReport rows: " + adds.length);
            System.out.println("updated plantingReport rows: " + updates.length);
            addPS.close();
            updatePS.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(MobilePlantingReport.class.getName()).log(Level.SEVERE, null, x);
        }
        System.out.println("plantingReport rows affected: " + count);

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
